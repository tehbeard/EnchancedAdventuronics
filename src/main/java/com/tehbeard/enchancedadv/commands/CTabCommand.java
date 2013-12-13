package com.tehbeard.enchancedadv.commands;

import com.tehbeard.enchancedadv.EnhancedAdventuronics;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

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
		return "command.velocity.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		EnhancedAdventuronics.favouritesTab.addStack(player.inventory.getCurrentItem());
		return;
		
		//throw new WrongUsageException(getCommandUsage(sender));
		
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}
