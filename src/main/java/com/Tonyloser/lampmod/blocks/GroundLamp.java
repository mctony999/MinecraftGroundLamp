package com.Tonyloser.lampmod.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GroundLamp extends BlockBase
{
	public static final AxisAlignedBB GROUND_LAMP_BLOCK_AABB = new AxisAlignedBB(0.0625 * 5, 0, 0.0625*5, 0.0625*11, 0.0625*10, 0.0625*11); //collision control, set to point in the space and the rectangle will be the collision space
	
	public GroundLamp(String name, Material material) //block property
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
	
	@Override
	public boolean isOpaqueCube(IBlockState stat) //make the floor can see
	{
		return false;
	}
	
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return GROUND_LAMP_BLOCK_AABB;
	}
	
	public BlockRenderLayer getBlockLayer() //Make it's texture partial transparent
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
}
