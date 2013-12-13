package com.tehbeard.enchancedadv.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Adds the /velocity command
 * @author James
 *
 */
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
		return "command.velocity.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		EntityPlayer player = null;
		double xMotion = 0;
		double yMotion = 0;
		double zMotion = 0;
		if(args.length == 4){
			player = getPlayer(sender,args[0]);
			xMotion = Double.parseDouble(args[1]);
			yMotion = Double.parseDouble(args[2]);
			zMotion = Double.parseDouble(args[3]);
		}
		
		if(args.length == 3){
			player = getCommandSenderAsPlayer(sender);
			xMotion = Double.parseDouble(args[0]);
			yMotion = Double.parseDouble(args[1]);
			zMotion = Double.parseDouble(args[2]);
		}
		
		if(player!= null){
			player.addVelocity(xMotion, yMotion, zMotion);
			player.velocityChanged = true;
			return;
		}
		
		throw new WrongUsageException("command.velocity.usage");
		
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}
