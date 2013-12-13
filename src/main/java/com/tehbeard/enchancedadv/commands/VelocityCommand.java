package com.tehbeard.enchancedadv.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
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
	public void processCommand(ICommandSender icommandsender, String[] args) {
		if(args.length == 3){
			
		}
		icommandsender.sendChatToPlayer(new ChatMessageComponent().addText(getCommandUsage(icommandsender)));
		
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}
