package com.Tonyloser.lampmod.blocks;

import com.Tonyloser.lampmod.tileentity.TileEntityHang;
import com.Tonyloser.lampmod.blocks.GroundLamp;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TESGroundLamp extends TileEntitySpecialRenderer<TileEntityHang> {
	/** This is the render for lantern. 
	 *  It will keep firing when game is running.
	 */
	@Override
	public void render(TileEntityHang te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		int TESHangORNot = te.getTileEntityHang();
		World worldIn = te.getWorld();
		BlockPos pos = te.getPos();
		
		if (TESHangORNot == 1)
		{
			GroundLamp.SetBlockState(worldIn, pos ,true);
		}
		else
		{
			GroundLamp.SetBlockState(worldIn, pos ,false);
		}
	}
}
