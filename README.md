Enchanced Adventuronics
=======================

ModJam 3 entry

Enchanced Adventuronics adds some items, blocks and mechanics to improve adventure map mechanics.

Blocks
------
* Molecular Pacifier - TE, 
  - Scriptable region management
  - Use Player Selector mechanism (@p)
  - Region definition
  - block whitelist
  - block blacklist
  - redstone controlled
  - uses writable books
* Lock block - TE,
  - Indestructible block, disappears when a key activates it. Color coded?

Items
-----
* Location Encoder - Use to tag a location, then click on a command block to bind to it (replace variables).
* Block Duplicator - Copies the block it is clicked on, left click to capture, right click to replace, shift+right click to place against
* Key - Used to break lock blocks, can have a number of uses.

Commands
--------
/wipeEntities - Command to clear entities from an area, filterable by type?
/velocity     - Command to fire players (Player Selector mechanism) in a specified direction? - DONE
/notify       - Displays the achievement notification to the specified player, scriptable icon, text and description - DONE/NEEDS BETTER STRING HANDLER
/specator     - Spectator mode : Flight, no block breaking, no inventory access, True Invisibility (same mechanism as bukkit), no entity collision?
/hasitem      - Checks if player the specified item.
/gamerule pvp on|off / true/false - Disables ability to hurt players
/gamerule teamchat on|off true/false - chat messages only sent to team members
/ctab         - Add/remove item to/from favourites creative tab. - DONE

Mechanics
---------

Item actions  - Adds the Action NBT tag to items, this allows a map maker to invoke various actions when triggers are met for an item.
Core data model:

action: [
{
	on:{Trigger definition},
	do:[
	{action},
	{action},
	{filter
	  ...
	  do:[{action}]
	  ...
	}
	]
}
]

All actions in a tier execute regardless of failure state of others at that level

triggers:
- on held
- on unheld?
- on pickup
- on drop
- on use (right click)
- on equip (armor)
- on unequip (armor)
- on attack (left clicked)
- on attacked with (apply to other)
- on attacked (called while having when hit)

actions:
- Apply potion effect
- Apply attributes
- velocity
- health
- scoreboard manipulation
- exeucte command (as commandblock / console / special)?
- clear cooldown/warmup
- play sound

filters: Special action type used to further refine target
- Player Selector (stop if not matched)
- Cooldown (prevent use until x seconds pass)
- Warmup (Timer use for x seconds)

How to build
------------
1. Install gradle
2. navigate to source folder
3. run gradle setupDevWorkspace

**Open / run via eclipse**
1. run gradle eclipse
2. Use import existing projects into workspace and select folder.

**Compile from command line**
1. Run gradle build, output is in build/libs
Based on information from : http://www.minecraftforge.net/forum/index.php?topic=14048.0