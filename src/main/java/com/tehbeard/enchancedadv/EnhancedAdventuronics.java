package com.tehbeard.enchancedadv;

import com.tehbeard.enchancedadv.commands.CTabCommand;
import com.tehbeard.enchancedadv.commands.NotifyCommand;
import com.tehbeard.enchancedadv.commands.VelocityCommand;
import com.tehbeard.enchancedadv.commands.WipeEntitiesCommand;
import com.tehbeard.enchancedadv.network.NetworkControl;
import com.tehbeard.enchancedadv.proxy.CommonProxy;

import net.minecraft.command.CommandHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "enchancedadventuronics",useMetadata=true)
@NetworkMod(clientSideRequired=true,channels=NetworkControl.CHANNEL_NAME,packetHandler=NetworkControl.class)
public class EnhancedAdventuronics {
	
	

	@SidedProxy(clientSide="com.tehbeard.enchancedadv.proxy.ClientProxy",serverSide="com.tehbeard.enchancedadv.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event){
		proxy.init();
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent event){
		LanguageRegistry.instance().addStringLocalization("command.velocity.usage", "/velocity player x y z or /velocity x y z");
		LanguageRegistry.instance().addStringLocalization("command.ctab.usage", "/ctab [remove]");
		LanguageRegistry.instance().addStringLocalization("command.wipeentities.usage","/wipeentities x y z radius");
		LanguageRegistry.instance().addStringLocalization("itemGroup.favourite", "Favourites");
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
		manager.registerCommand(new NotifyCommand());
		manager.registerCommand(new WipeEntitiesCommand());
		
	}
	
}
