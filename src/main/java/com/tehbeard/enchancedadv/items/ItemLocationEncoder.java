package com.tehbeard.enchancedadv.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * Encodes location information to apply to a command block
 * @author James
 *
 */
public class ItemLocationEncoder extends Item{

	public ItemLocationEncoder(int par1) {
		super(par1);
		setUnlocalizedName("locationencoder");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		initTag(par1ItemStack);
		super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		
		initTag(stack);
		par3List.add("x: " + getLocTag(stack).getInteger("x"));
		par3List.add("y: " + getLocTag(stack).getInteger("y"));
		par3List.add("z: " + getLocTag(stack).getInteger("z"));
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		initTag(stack);
		
		TileEntity te = world.getBlockTileEntity(x, y, z);
		//Process teleport commands
		if(te instanceof TileEntityCommandBlock){
			
			
			TileEntityCommandBlock cmdBlock = ((TileEntityCommandBlock) te);
			String command = cmdBlock.getCommand();
			String[] parts = command.split(" ");
			if(parts[0].equalsIgnoreCase("tp") && parts.length==5){
				parts[2] = "" + getLocTag(stack).getInteger("x");
				parts[3] = "" + getLocTag(stack).getInteger("y");
				parts[4] = "" + getLocTag(stack).getInteger("z");
			}
			player.sendChatToPlayer(new ChatMessageComponent().addText("Set teleport command to coordinates" +
					getLocTag(stack).getInteger("x") + ", " +
					getLocTag(stack).getInteger("y") + ", " +
					getLocTag(stack).getInteger("z")
					
			));
			StringBuilder builder = new StringBuilder();
			for(String s : parts) {
				if(builder.length() > 0){builder.append(" ");}
			    builder.append(s);
			}
			cmdBlock.setCommand(builder.toString());
			return true;
		}
		if(player.isSneaking()){
			ForgeDirection direction = ForgeDirection.getOrientation(side);
			getLocTag(stack).setInteger("x",x + direction.offsetX);
			getLocTag(stack).setInteger("y",y + direction.offsetY);
			getLocTag(stack).setInteger("z",z + direction.offsetZ);
		}
		else
		{
			getLocTag(stack).setInteger("x", x);
			getLocTag(stack).setInteger("y", y);
			getLocTag(stack).setInteger("z", z);
		}
		return true;
	}
	
	public NBTTagCompound getLocTag(ItemStack stack){
		return stack.stackTagCompound.getCompoundTag("loc");
	}
	
	public void initTag(ItemStack stack){
		if(stack.stackTagCompound==null){
	        stack.stackTagCompound = new NBTTagCompound();
	        stack.stackTagCompound.setCompoundTag("loc", new NBTTagCompound());
	        stack.stackTagCompound.getCompoundTag("loc").setInteger("x",0);
	        stack.stackTagCompound.getCompoundTag("loc").setInteger("y",0);
	        stack.stackTagCompound.getCompoundTag("loc").setInteger("z",0);
	        }
	}

}
