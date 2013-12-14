package com.tehbeard.enchancedadv.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import com.tehbeard.enchancedadv.tabs.FavouritesTab;
import com.tehbeard.enchancedadv.utils.EnhancedGuiAchievement;
import com.tehbeard.enchancedadv.utils.FakeAchievement;

public class ClientProxy extends CommonProxy{

	public static final FavouritesTab favouritesTab = new FavouritesTab();

	public void addFavourite(EntityPlayerMP player, ItemStack itemStack) {
		favouritesTab.addStack(itemStack);
	} 

	public void removeFavourite(EntityPlayerMP player, ItemStack itemStack) {
		favouritesTab.removeStack(itemStack);
	}

	public void sendNotification(EntityPlayerMP player,ItemStack icon,String title,String subText){
		((EnhancedGuiAchievement)Minecraft.getMinecraft().guiAchievement).queueFakeAchievement(new FakeAchievement(title, subText, icon));
	}

	public void init(){
		//Ugly ass kludge to our own type of achievement type things.
		Minecraft.getMinecraft().guiAchievement = new EnhancedGuiAchievement(Minecraft.getMinecraft());
		
	}
}
