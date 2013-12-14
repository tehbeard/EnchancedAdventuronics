package com.tehbeard.enchancedadv.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

/**
 * Adds the /velocity command
 * @author James
 *
 */
public class WipeEntitiesCommand extends CommandBase{

	@Override
	public String getCommandName() {
		return "wipeentities";
	}

	public int getRequiredPermissionLevel()
	{
		return 2;
	}


	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "command.wipeentities.usage";
	}

	public double getCoord(String v,double defVal){
		if(v.startsWith("~")){
			try{
			return Double.parseDouble(v.substring(1)) + defVal;
			}
			catch(Exception e){}
		}
		return Double.parseDouble(v);


	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		double xPos = getCoord(args[0],sender.getPlayerCoordinates().posX);
		double yPos = getCoord(args[1],sender.getPlayerCoordinates().posY);
		double zPos = getCoord(args[2],sender.getPlayerCoordinates().posZ);
		double radius  = Double.parseDouble(args[3]);

		double distSqrd = radius * radius; // Use squared radius to distance check quicker instead of sqrting

		AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(
				xPos,
				yPos,
				zPos,
				xPos,
				yPos,
				zPos
				);
		bb = bb.expand(radius, radius, radius);
		@SuppressWarnings("unchecked")
		List<Entity> found = sender.getEntityWorld().getEntitiesWithinAABB(Entity.class, bb);

		for(Entity e : found){
			System.out.println("Found: " +e);
			if(distanceSqrd(e.posX,e.posY,e.posZ,xPos,yPos,zPos) <=distSqrd){
				//We want this.
				if(e instanceof EntityPlayer){continue;} //ignore players
				System.out.println("within range");
				//TODO - Entity Type selection
				e.worldObj.removeEntity(e);

			}
		}

		//throw new WrongUsageException(getCommandUsage(sender));

	}

	private double distanceSqrd(double x1,double y1,double z1,double x2,double y2,double z2){
		return (x1 - x2) * (x1 - x2) +
				(y1 - y2) * (y1 - y2) +
				(z1 - z2) * (z1 - z2);
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}

