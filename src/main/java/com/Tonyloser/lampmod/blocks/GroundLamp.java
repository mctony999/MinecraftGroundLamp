package com.Tonyloser.lampmod.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GroundLamp extends BlockBase
{

	public GroundLamp(String name, Material material) 
	{
		super(name, material);
		
		setSoundType(SoundType.METAL); //The sound walk on it.
		setHardness(3.5F); //Hardness
		setResistance(3.5F); //Explosive Resistance
		setHarvestLevel("pickaxe", 0); //What tool can harvest the block
		setLightLevel(1.0F); //lightness only between 0~1
		//setLightOpacity(1); //1 for light can through the block
		//setBlockUnbreakable();
	}
	
	
}
