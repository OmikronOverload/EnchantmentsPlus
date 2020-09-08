Got a question? Need help or want to discuss changes? Then feel free to DM me via discord: tristellar#9022. Issues however should be reported here so forkers can profit from them being public

It also only intends to support 1.16.2 and newer versions when the time comes.

# Enchantments+
## Description
Zenchantments is a custom enchantment plugin that adds 70+ new enchantments (it doesn't feel like much, to be honest) to the game, covering a multitude of different uses. These include target-tracing arrows, lumber axes, block-breaking lasers, and much more. These custom enchantments are obtained through the normal enchantment process and act like regular enchantments, capable of being combined and merged. The plugin requires no client-side mods or resource packs. A comprehensive configuration file enables fine-tuning of individual enchantments to tailor them to every server's gameplay. 
<br> While we currently don't add or remove any Enchantments, we will attempt to rebalance them and make them futureproof. In essence, we attempt to make this Fork as stable as possible to support Production-grade Servers.

## Permissions
enchplus.enchant.get - On player enchant event, allow player to have a chance at the enabled custom enchantments<br>
enchplus.enchant.use - Allow player to use the given custom enchants on an item<br>
enchplus.command.reload - Access to /ench reload, to reload the configuration (try not to use, may lead to memory leaks)<br>
enchplus.command.give - Gives an enchanted item, while similar to enchant, it also gives the underlying material<br>
enchplus.command.list - Lists all availiable enchantments<br>
enchplus.command.info - Returns the info of an enchantments<br>
enchplus.command.onoff - Abillity to turn on / turn off a certain enchantment<br>
enchplus.command.enchant - Enchants a item without giving the underlying material. Also allows to enchant other people's stuff, so be cautious.<br>
enchplus.command.lasercol - Enables the abillity to colo(u)r the laser of your item in hand. Purely cosmetic and a good way to get a few extra "donations".<br>

## Download
See [Releases](https://github.com/Geolykt/EnchantmentsPlus/releases) for downloads

## Compilation
Compile this project like every other project out there. Note that you need to have the latest bindings.

## Compatibility
The current version of this plugin is fully compatible with Spigot version 1.16.1 and 1.16.2. Any versions under 1.16.1, will **not** work without tinkering, versions above, may, although with a few issues.

## Contribute
Anyone is free to contribute to this repository via pull requests, issues or comments, however keep in mind that this repository uses 4 space indentation.

## Changes performed in this fork compared to NMS-less Zenchantments
To view the changes compared to Zenchantments, add the Changelog of [NMS-Less Zenchantments](https://github.com/Geolykt/NMSless-Zenchantments#changes-performed-in-this-fork) onto it
<ul>
 <li>Major changes:
  <ul>
   <li>Most values in the compatibillity adapter can be changed via magic configuration files now</li>
  </ul>
 </li>
 <li>Minor changes:
  <ul>
   <li>Fixed the Reveal enchantment, with it's own twist</li>
   <li>Added an enchantment gatherer denylist - only works with NBT right now</li>
   <li>The color of the laser can now be changed for purely costmetical reasons. Why? Don't ask.</li>
  </ul>
 </li>
 <li>Patches:
  <ul>
   <li>Fire enchantment no longer OOMs when attempting to smelt unsmeltable stuff</li>
   <li>Added toggleable shred cooldown when the laser enchantment is used.</li>
  </ul>
 </li>
 <li>Code changes (doesn't affect behaviour as much):
  <ul>
   <li>Refractored to it's own structure</li>
   <li>Fix compilation issues with later bindings</li>
  </ul>
 </li>
</ul>
