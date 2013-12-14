package com.tehbeard.enchancedadv.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		initTag(stack);
		if(player.isSneaking()){
			ForgeDirection direction = ForgeDirection.getOrientation(side);
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
