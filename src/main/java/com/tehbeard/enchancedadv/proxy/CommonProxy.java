package com.tehbeard.enchancedadv.proxy;

import com.tehbeard.enchancedadv.network.NetworkControl;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class CommonProxy {


	public void addFavourite(EntityPlayerMP player, ItemStack itemStack) {
		NetworkControl.sendFavouriteTab(player, true, itemStack);
	} 
	
	public void removeFavourite(EntityPlayerMP player, ItemStack itemStack) {
		NetworkControl.sendFavouriteTab(player, false, itemStack);
	} 
	
	public void sendNotification(EntityPlayerMP player,ItemStack icon,String title,String subText){
		
	}
}
