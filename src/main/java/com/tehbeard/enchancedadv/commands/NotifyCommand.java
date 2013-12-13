package com.tehbeard.enchancedadv.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

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
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}
