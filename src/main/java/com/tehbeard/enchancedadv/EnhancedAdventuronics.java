package com.tehbeard.enchancedadv;

import com.tehbeard.enchancedadv.commands.VelocityCommand;

import net.minecraft.command.CommandHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "enchancedadventuronics",useMetadata=true)
public class EnhancedAdventuronics {

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event){
		//TODO - Load configuration here.
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent event){
		LanguageRegistry.instance().addStringLocalization("command.velocity.usage", "/velocity player x y z or /velocity x y z");
	}
	
	@EventHandler
	public void onPostInit(FMLPostInitializationEvent event){
		//TODO - Needed?
	}
	
	@EventHandler
	public void onServerStart(FMLServerAboutToStartEvent event){
		CommandHandler manager = (CommandHandler) event.getServer().getCommandManager();
		manager.registerCommand(new VelocityCommand());
		
	}
	
}
