package com.tehbeard.enchancedadv;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import net.minecraft.command.CommandHandler;
import net.minecraft.item.ItemStack;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import com.tehbeard.enchancedadv.commands.CTabCommand;
import com.tehbeard.enchancedadv.commands.NotifyCommand;
import com.tehbeard.enchancedadv.commands.VelocityCommand;
import com.tehbeard.enchancedadv.commands.WipeEntitiesCommand;
import com.tehbeard.enchancedadv.items.ItemLocationEncoder;
import com.tehbeard.enchancedadv.network.NetworkControl;
import com.tehbeard.enchancedadv.proxy.ClientProxy;
import com.tehbeard.enchancedadv.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "enchancedadventuronics",useMetadata=true)
@NetworkMod(clientSideRequired=true,channels=NetworkControl.CHANNEL_NAME,packetHandler=NetworkControl.class)
public class EnhancedAdventuronics {



	@SidedProxy(clientSide="com.tehbeard.enchancedadv.proxy.ClientProxy",serverSide="com.tehbeard.enchancedadv.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static ItemLocationEncoder encoderItem;

	public static Config config = new Config();
	

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event){
		proxy.init();
		try{
			File configFile = event.getSuggestedConfigurationFile();
			if(configFile.exists()){
				config = new GsonBuilder().create().fromJson(new FileReader(configFile), Config.class);
			}
			//Re write config to disk to push new changes.
			JsonWriter jw = new JsonWriter(new FileWriter(configFile));
			new GsonBuilder().setPrettyPrinting().create().toJson(config, TypeToken.of(Config.class).getType(), jw);
			jw.flush();
			jw.close();


		}catch(Exception e){
			System.out.println("FAILURE LOADING CONFIG FOR ENHANCED ADVENTURES.");
			throw new RuntimeException(e);
		}
	}

	@EventHandler
	public void onInit(FMLInitializationEvent event){
		LanguageRegistry.instance().addStringLocalization("command.velocity.usage", "/velocity player x y z or /velocity x y z");
		LanguageRegistry.instance().addStringLocalization("command.ctab.usage", "/ctab [remove]");
		LanguageRegistry.instance().addStringLocalization("command.wipeentities.usage","/wipeentities x y z radius [entity|arrow|item|living|animal|mob]");
		LanguageRegistry.instance().addStringLocalization("itemGroup.favourite", "Favourites");
		
		
		//Init and add LocationEncoder
		encoderItem = new ItemLocationEncoder(config.itemLocationEncoderId);
		ClientProxy.favouritesTab.addStaticItem(new ItemStack(encoderItem));
		GameRegistry.registerItem(encoderItem,"locationencoder", "enchancedadventuronics");
		LanguageRegistry.addName(encoderItem, "Location Encoder");
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
