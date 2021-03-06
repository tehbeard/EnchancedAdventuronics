package com.tehbeard.enchancedadv.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;

/**
 * Adds the /velocity command
 * @author James
 *
 */
public class WipeEntitiesCommand extends CommandBase{
	
	public enum ETYPE{
		ENTITY(Entity.class),
		ARROW(EntityArrow.class),
		ITEM(EntityItem.class),
		LIVING(EntityLiving.class),
		ANIMAL(EntityAnimal.class),
		MOB(EntityMob.class);
		
		public final Class<? extends Entity> type;
		
		private ETYPE(Class<? extends Entity> type){
			this.type = type;
		}
	}

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
		try{
		if(v.startsWith("~")){
			
			return Double.parseDouble(v.substring(1)) + defVal;
			
		}
		return Double.parseDouble(v);
		}
		catch(Exception e){
			return defVal;
		}

	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		double xPos = getCoord(args[0],sender.getPlayerCoordinates().posX);
		double yPos = getCoord(args[1],sender.getPlayerCoordinates().posY);
		double zPos = getCoord(args[2],sender.getPlayerCoordinates().posZ);
		double radius  = Double.parseDouble(args[3]);
		
		Class type = ETYPE.ENTITY.type;
		try{
		if(args.length == 5){
			type = ETYPE.valueOf(args[4].toUpperCase()).type;
		}
		}catch(Exception e){
			throw new WrongUsageException(getCommandUsage(sender));
		}

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
		List<Entity> found = sender.getEntityWorld().getEntitiesWithinAABB(type, bb);

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

