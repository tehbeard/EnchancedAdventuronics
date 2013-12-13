package com.tehbeard.enchancedadv.commands;

import com.tehbeard.enchancedadv.utils.FakeAchievement;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
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
	public void processCommand(ICommandSender icommandsender, String[] args) {
		
		new FakeAchievement(args[0], args[1], new ItemStack(Item.netherStar));
		
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}
