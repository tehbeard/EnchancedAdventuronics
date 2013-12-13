package com.tehbeard.enchancedadv.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.tehbeard.enchancedadv.EnhancedAdventuronics;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class NetworkControl implements IPacketHandler {
	
	public static final String CHANNEL_NAME = "tehbeard:encadv";

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		try{
		NBTTagCompound packetTag = CompressedStreamTools.readCompressed(new ByteArrayInputStream(packet.data));
		
		if(packetTag.getBoolean("add")){
			EnhancedAdventuronics.proxy.addFavourite(null, ItemStack.loadItemStackFromNBT(packetTag.getCompoundTag("item")));
		}
		else
		{
			EnhancedAdventuronics.proxy.removeFavourite(null, ItemStack.loadItemStackFromNBT(packetTag.getCompoundTag("item")));
		}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static void sendFavouriteTab(EntityPlayerMP player,boolean add, ItemStack stack){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		NBTTagCompound packet = new NBTTagCompound("packet");
		
		NBTTagCompound itemTag = new NBTTagCompound();
		stack.writeToNBT(itemTag);
		
		packet.setCompoundTag("item",itemTag);
		packet.setBoolean("add", add);
		
		try{
		CompressedStreamTools.writeCompressed(packet, bos);
		player.playerNetServerHandler.sendPacketToPlayer(new Packet250CustomPayload(CHANNEL_NAME, bos.toByteArray()));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}
