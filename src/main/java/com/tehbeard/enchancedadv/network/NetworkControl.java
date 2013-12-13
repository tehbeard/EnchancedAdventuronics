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

			if(packetTag.getName().equalsIgnoreCase("favourite")){
				handleFavouritePacket(packetTag);return;
			}

			if(packetTag.getName().equalsIgnoreCase("notify")){
				handleNotifyPacket(packetTag);return;
			}



		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	private void handleNotifyPacket(NBTTagCompound packetTag) {
		EnhancedAdventuronics.proxy.sendNotification(
				null, 
				ItemStack.loadItemStackFromNBT(packetTag.getCompoundTag("item")), 
				packetTag.getString("title"), 
				packetTag.getString("subText"));

	}

	private void handleFavouritePacket(NBTTagCompound packetTag){
		if(packetTag.getBoolean("add")){
			EnhancedAdventuronics.proxy.addFavourite(null, ItemStack.loadItemStackFromNBT(packetTag.getCompoundTag("item")));
		}
		else
		{
			EnhancedAdventuronics.proxy.removeFavourite(null, ItemStack.loadItemStackFromNBT(packetTag.getCompoundTag("item")));
		}

	}

	public static void sendFavouriteTab(EntityPlayerMP player,boolean add, ItemStack stack){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		NBTTagCompound packet = new NBTTagCompound("favourite");

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

	public static void sendNotification(EntityPlayerMP player, String text,String subText, ItemStack stack){

	}

}
