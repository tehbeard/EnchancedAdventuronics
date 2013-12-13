package com.tehbeard.enchancedadv;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import com.tehbeard.enchancedadv.tabs.FavouritesTab;

public class ClientProxy extends CommonProxy{

	private static final FavouritesTab favouritesTab = new FavouritesTab();

	public void addFavourite(EntityPlayerMP player, ItemStack itemStack) {
		favouritesTab.addStack(itemStack);
	} 
	
	public void removeFavourite(EntityPlayerMP player, ItemStack itemStack) {
		favouritesTab.removeStack(itemStack);
	} 
}
