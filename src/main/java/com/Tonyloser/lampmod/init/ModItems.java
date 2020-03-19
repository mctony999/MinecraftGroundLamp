package com.Tonyloser.lampmod.init;

import java.util.ArrayList;
import java.util.List;

import com.Tonyloser.lampmod.items.ItemBase;
import com.Tonyloser.lampmod.items.food.FoodBase;
import com.Tonyloser.lampmod.items.food.FoodEffectBase;

import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

public class ModItems 
{
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item GROUND_LAMP = new ItemBase("ground_lamp");
	
	
	//Food
	//public static final Item EVIL_APPLE = new FoodBase("evil_apple", 4, 2.4f, false);
	public static final Item EATABLE_LANTERN = new FoodEffectBase("eatable_latern", 4, 2.4f, false, new PotionEffect(MobEffects.POISON, (60*10), 0, false, true)); //what effect, duration, effect level 0~2,beacon, particle
	
}
