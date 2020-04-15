package com.Tonyloser.lampmod.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes 
{
	public static void init() 
	{
		//GameRegistry.addSmelting(ModBlocks.GROUND_LAMP_BLOCK, new ItemStack(Items.GOLDEN_APPLE, 1), 1.5f); 
		//add new recipes into smelt. use items. for vanilla item. use block for vanilla block 
		GameRegistry.addSmelting(ModBlocks.GROUND_LAMP_BLOCK, new ItemStack(ModItems.EATABLE_LANTERN, 1), 1.5f); //add new recipes into smelt. use items. for vanilla item. use block for vanilla block 
	}
}
