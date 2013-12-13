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
/notify       - Displays the achievement notification to the specified player, scriptable icon, text and description
/specator     - Spectator mode : Flight, no block breaking, no inventory access, True Invisibility (same mechanism as bukkit), no entity collision?
/hasitem      - Checks if player the specified item.
/gamerule pvp on|off / true/false - Disables ability to hurt players
/gamerule teamchat on|off true/false - chat messages only sent to team members
/ctab         - Add/remove item to/from favourites creative tab. - DONE

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