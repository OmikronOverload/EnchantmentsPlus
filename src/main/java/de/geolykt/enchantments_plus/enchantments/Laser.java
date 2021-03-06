package de.geolykt.enchantments_plus.enchantments;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import de.geolykt.enchantments_plus.CustomEnchantment;
import de.geolykt.enchantments_plus.EnchantPlayer;
import de.geolykt.enchantments_plus.enums.BaseEnchantments;
import de.geolykt.enchantments_plus.enums.Hand;
import de.geolykt.enchantments_plus.util.Tool;
import de.geolykt.enchantments_plus.util.Utilities;

import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

public class Laser extends CustomEnchantment {

    // Time at which a later enchantment was fired; this is used to prevent double firing when clicking an entity
    // public static final Map<Player, Long> laserTimes = new HashMap<>();
    public static final int ID = 31;
    
    public static NamespacedKey colorKey;

    public static boolean doShredCooldown = true;

    @Override
    public Builder<Laser> defaults() {
        return new Builder<>(Laser::new, ID)
                .maxLevel(3)
                .loreName("Laser")
                .probability(0)
                .enchantable(new Tool[]{Tool.PICKAXE, Tool.AXE})
                .conflicting()
                .description("Breaks blocks and damages mobs using a powerful beam of light")
                .cooldown(0)
                .power(1.0)
                .handUse(Hand.RIGHT)
                .base(BaseEnchantments.LASER);
    }

    public void shoot(Player player, int level, boolean usedHand) {
        EnchantPlayer.matchPlayer(player).setCooldown(Lumber.ID, 5); // Avoid recursing into Lumber enchant
        if (doShredCooldown) {
            EnchantPlayer.matchPlayer(player).setCooldown(Shred.ID, 5); // Avoid firing the shred enchantment when using the right click action
        }
        Block blk = player.getTargetBlock(null, 6
                + (int) Math.round(level * power * 3));
        Location playLoc = player.getLocation();
        Location target = Utilities.getCenter(blk.getLocation());
        target.setY(target.getY() + .5);
        playLoc.setY(playLoc.getY() + 1.1);
        ItemStack itemInHand = usedHand ? player.getInventory().getItemInMainHand() : player.getInventory().getItemInOffHand();
        double d = target.distance(playLoc);
        for (int i = 0; i < (int) d * 5; i++) {
            Location tempLoc = target.clone();
            tempLoc.setX(playLoc.getX() + (i * ((target.getX() - playLoc.getX()) / (d * 5))));
            tempLoc.setY(playLoc.getY() + (i * ((target.getY() - playLoc.getY()) / (d * 5))));
            tempLoc.setZ(playLoc.getZ() + (i * ((target.getZ() - playLoc.getZ()) / (d * 5))));

            player.getWorld().spawnParticle(Particle.REDSTONE, tempLoc, 1, new Particle.DustOptions(getColor(itemInHand), 0.5f));

            for (Entity ent : Bukkit.getWorld(playLoc.getWorld().getName()).getNearbyEntities(tempLoc, .3, .3, .3)) {
                if (ent instanceof LivingEntity && ent != player) {
                    LivingEntity e = (LivingEntity) ent;
                    ADAPTER.attackEntity(e, player, 1 + (level + power * 2));
                    return;
                }
            }
        }
        if (ADAPTER.isBlockSafeToBreak(blk) && !ADAPTER.laserDenylist().contains(blk.getType())) {
            ADAPTER.breakBlockNMS(blk, player);
        }
    }

    @Override
    public boolean onEntityInteract(PlayerInteractEntityEvent evt, int level, boolean usedHand) {
        if (usedHand && !evt.getPlayer().isSneaking()) {
            shoot(evt.getPlayer(), level, usedHand);
            return true;
        }
        return false;
    }

    @Override
    public boolean onBlockInteract(final PlayerInteractEvent evt, int level, boolean usedHand) {
        if (usedHand && !evt.getPlayer().isSneaking()
                && (evt.getAction() == RIGHT_CLICK_AIR || evt.getAction() == RIGHT_CLICK_BLOCK)) {
            shoot(evt.getPlayer(), level, usedHand);
            return true;
        }
        return false;
    }

    public static org.bukkit.Color getColor(ItemStack stack) {
        if (stack.hasItemMeta() && !stack.getItemMeta().getPersistentDataContainer().isEmpty()) {
            return Color.fromRGB(stack.getItemMeta().getPersistentDataContainer().getOrDefault(colorKey, PersistentDataType.INTEGER, Color.RED.asRGB()));
        }
        return Color.RED;
    }

    public static void setColor(ItemStack stack, org.bukkit.Color color) {
        ItemMeta im = stack.getItemMeta();
       im.getPersistentDataContainer().set(colorKey, PersistentDataType.INTEGER, color.asRGB());
       stack.setItemMeta(im);
    }
}
