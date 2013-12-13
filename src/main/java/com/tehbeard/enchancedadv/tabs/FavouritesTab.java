package com.tehbeard.enchancedadv.tabs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class FavouritesTab extends CreativeTabs {
	
	private Set<ItemStack> stacks = new HashSet<ItemStack>();

	public FavouritesTab() {
		super("favourite");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void displayAllReleventItems(List list){
		list.addAll(stacks);
	}
	
	public void addStack(ItemStack stack){
		ItemStack toUse = stack.copy();
		toUse.stackSize = 1;
		stacks.add(toUse);
	}
	
	public void removeStack(ItemStack stack){
		ItemStack toUse = stack.copy();
		toUse.stackSize = 1;
		stacks.remove(toUse);
	}

}
