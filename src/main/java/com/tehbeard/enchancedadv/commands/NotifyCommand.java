package com.tehbeard.enchancedadv.commands;

import com.tehbeard.enchancedadv.EnhancedAdventuronics;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class NotifyCommand extends CommandBase{

	@Override
	public String getCommandName() {
		return "notify";
	}
	
	public int getRequiredPermissionLevel()
    {
        return 2;
    }

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "command.notify.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		EntityPlayerMP player = null;
		String title = null;
		String subText = null;
		ItemStack stack = null;
		if(args.length == 5){
			player = getPlayer(sender,args[0]);
			title = args[1];
			subText = args[2];
			stack = new ItemStack(
					Integer.parseInt(args[3]),
					1,
					Integer.parseInt(args[4])
					);

		}
		
		EnhancedAdventuronics.proxy.sendNotification(player, stack, title, subText);
		
		
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}

