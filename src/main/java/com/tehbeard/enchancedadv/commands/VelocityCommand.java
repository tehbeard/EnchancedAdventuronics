package com.tehbeard.enchancedadv.commands;

import com.tehbeard.enchancedadv.EnhancedAdventuronics;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatMessageComponent;

public class VelocityCommand extends CommandBase{

	@Override
	public String getCommandName() {
		return "velocity";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/velocity player x y z";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length == 4){
			EntityPlayer player = sender.getEntityWorld().getPlayerEntityByName(args[0]);
			double xMotion = Double.parseDouble(args[1]);
			double yMotion = Double.parseDouble(args[2]);
			double zMotion = Double.parseDouble(args[3]);
			
			player.motionX += xMotion;
			player.motionY += yMotion;
			player.motionZ += zMotion;
			return;
		}
		
		sender.sendChatToPlayer(new ChatMessageComponent().addText(getCommandUsage(sender)));
		
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}
