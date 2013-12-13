package com.tehbeard.enchancedadv.commands;

import com.tehbeard.enchancedadv.EnhancedAdventuronics;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatMessageComponent;

/**
 * Adds the /velocity command
 * @author James
 *
 */
public class CTabCommand extends CommandBase{

	@Override
	public String getCommandName() {
		return "ctab";
	}

	public int getRequiredPermissionLevel()
	{
		return 2;
	}


	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "command.ctab.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		if(args.length == 0){
			sender.sendChatToPlayer(new ChatMessageComponent().addText("Adding item."));
			EnhancedAdventuronics.favouritesTab.addStack(player.inventory.getCurrentItem());
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("remove")){
			sender.sendChatToPlayer(new ChatMessageComponent().addText("Removing item."));
			EnhancedAdventuronics.favouritesTab.removeStack(player.inventory.getCurrentItem());
		}
		return;

		//throw new WrongUsageException(getCommandUsage(sender));

	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}
