package com.tehbeard.enchancedadv.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;

public class VelocityCommand extends CommandBase{

	@Override
	public String getCommandName() {
		return "velocity";
	}
	
	public int getRequiredPermissionLevel()
    {
        return 2;
    }


	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/velocity player x y z";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length == 4){
			EntityPlayer player = getPlayer(sender,args[0]);
			
			double xMotion = Double.parseDouble(args[1]);
			double yMotion = Double.parseDouble(args[2]);
			double zMotion = Double.parseDouble(args[3]);
			System.out.println("imparting " + args[0] + " with velocity {" + xMotion + ", " + yMotion + ", " + zMotion + "}");
			player.addVelocity(xMotion, yMotion, zMotion);
			player.velocityChanged = true;
			return;
		}
		
		sender.sendChatToPlayer(new ChatMessageComponent().addText(getCommandUsage(sender)));
		
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}
