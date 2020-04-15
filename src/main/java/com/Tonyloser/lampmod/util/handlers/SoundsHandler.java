package com.Tonyloser.lampmod.util.handlers;

import com.Tonyloser.lampmod.util.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler
{
    public static SoundEvent BLOCK_LANTERN_BREAK;
    public static SoundEvent BLOCK_LANTERN_STEP;
    public static SoundEvent BLOCK_LANTERN_FALL;
    public static SoundEvent BLOCK_LANTERN_HIT;
    public static SoundEvent BLOCK_LANTERN_PLACE;
    
    
	
	
	public static void registerSounds() 
	{
		BLOCK_LANTERN_BREAK = registerSound("block_lantern_break");
		BLOCK_LANTERN_STEP = registerSound("block_lantern_step");
		BLOCK_LANTERN_FALL = registerSound("block_lantern_fall");
		BLOCK_LANTERN_HIT = registerSound("block_lantern_hit");
		BLOCK_LANTERN_PLACE = registerSound("block_lantern_place");
		
		
	}
	
	private static SoundEvent registerSound (String name) 
	{
		SoundEvent sound = new SoundEvent(new ResourceLocation(Reference.MOD_ID, name)).setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		ForgeRegistries.SOUND_EVENTS.register(sound);
		return sound;
	}
	
//	private static SoundEvent registerSound (String name) 
//	{
//		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
//		SoundEvent sound = new SoundEvent(location);
//		sound.setRegistryName(name);
//		ForgeRegistries.SOUND_EVENTS.register(sound);
//		return sound;
//	}
	
}
