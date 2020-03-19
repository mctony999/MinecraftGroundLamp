package com.Tonyloser.lampmod.items.food;

import com.Tonyloser.lampmod.Main;
import com.Tonyloser.lampmod.init.ModItems;
import com.Tonyloser.lampmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood implements IHasModel
{
	public FoodBase(String name, int amount, float saturation, boolean isAnimalFood) //food name , food hunger bar, how long you wont feel hungry, 
	{
		super(amount, saturation, isAnimalFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.FOOD);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
