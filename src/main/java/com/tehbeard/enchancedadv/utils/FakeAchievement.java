package com.tehbeard.enchancedadv.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

public class FakeAchievement extends Achievement {
	
	private String title;
	private String desc;
	
	public FakeAchievement(String title,String desc,ItemStack icon){
		super(0, null, 0, 0, icon, null);
		this.title = title;
		this.desc = desc;
		
	}
	
	public String getDescription()
    {
		return desc;
    }
	
	public String getName(){
		return title;
	}
	
	public String toString(){
		return title;
	}

}
