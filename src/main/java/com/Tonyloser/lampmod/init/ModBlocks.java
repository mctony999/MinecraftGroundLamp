package com.Tonyloser.lampmod.init;

import java.util.ArrayList;
import java.util.List;

import com.Tonyloser.lampmod.blocks.BlockBase;
import com.Tonyloser.lampmod.blocks.GroundLamp;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block GROUND_LAMP_BLOCK = new GroundLamp("ground_lamp_block", Material.IRON);
	
}
