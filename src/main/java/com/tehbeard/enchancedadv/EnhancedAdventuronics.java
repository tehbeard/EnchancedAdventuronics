package com.tehbeard.enchancedadv;

import com.tehbeard.enchancedadv.commands.CTabCommand;
import com.tehbeard.enchancedadv.commands.VelocityCommand;
import com.tehbeard.enchancedadv.tabs.FavouritesTab;

import net.minecraft.block.Block;
import net.minecraft.command.CommandHandler;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "enchancedadventuronics",useMetadata=true)
public class EnhancedAdventuronics {
	
	public static final FavouritesTab favouritesTab = new FavouritesTab(); 

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event){
		//TODO - Load configuration here.
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent event){
		LanguageRegistry.instance().addStringLocalization("command.velocity.usage", "/velocity player x y z or /velocity x y z");
		LanguageRegistry.instance().addStringLocalization("itemGroup.favourite", "Favourites");
		
		favouritesTab.addStack(new ItemStack(Block.blockEmerald));
	}
	
	@EventHandler
	public void onPostInit(FMLPostInitializationEvent event){
		//TODO - Needed?
	}
	
	@EventHandler
	public void onServerStart(FMLServerAboutToStartEvent event){
		CommandHandler manager = (CommandHandler) event.getServer().getCommandManager();
		manager.registerCommand(new VelocityCommand());
		manager.registerCommand(new CTabCommand());
		
	}
	
}
