package com.tehbeard.enchancedadv.tabs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.tehbeard.enchancedadv.items.ItemLocationEncoder;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FavouritesTab extends CreativeTabs {

	private Set<ItemStack> stacks = new HashSet<ItemStack>();
	private Set<ItemStack> staticStacks = new HashSet<ItemStack>();

	public FavouritesTab() {
		super("favourite");
		setBackgroundImageName("item_search.png");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void displayAllReleventItems(List list){
		list.addAll(stacks);
		list.addAll(staticStacks);
	}

	/**
	 * Add a stack to this tab
	 * @param stack
	 */
	public void addStack(ItemStack stack){
		ItemStack toUse = stack.copy();
		toUse.stackSize = 1;
		_add(toUse);
	}

	private void _add(ItemStack toUse) {
		for(ItemStack is : stacks){
			if(ItemStack.areItemStacksEqual(is, toUse) && 
					ItemStack.areItemStackTagsEqual(is,toUse)){
				return;
			}
		}
		stacks.add(toUse);
	}

	/**
	 * Removes a stack from tab
	 * @param stack
	 */
	public void removeStack(ItemStack stack){
		ItemStack toUse = stack.copy();
		toUse.stackSize = 1;
		_remove(toUse);
	}

	private void _remove(ItemStack toUse) {
		Iterator<ItemStack> it = stacks.iterator();
		while(it.hasNext()){
			ItemStack is = it.next();
			if(ItemStack.areItemStacksEqual(is, toUse) && 
					ItemStack.areItemStackTagsEqual(is,toUse)){
				it.remove();
			}
		}
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}


	public int getTabIconItemIndex()
	{
		return Item.emerald.itemID;
	}

	public void addStaticItem(ItemStack item) {
		staticStacks.add(item);
		
	}
}
