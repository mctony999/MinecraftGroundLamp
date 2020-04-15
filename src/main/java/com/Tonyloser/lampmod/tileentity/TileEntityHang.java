package com.Tonyloser.lampmod.tileentity;

import com.Tonyloser.lampmod.Main;
import com.Tonyloser.lampmod.network.PacketRequestUpdateLantern;
import com.Tonyloser.lampmod.network.PacketUpdateLantern;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class TileEntityHang extends TileEntity {
	
	public int TileEntityHang;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("TileEntityHang", TileEntityHang);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		TileEntityHang = compound.getInteger("TileEntityHang");
		super.readFromNBT(compound);
	}
	
	public int getTileEntityHang()
	{
		return TileEntityHang;
	}
	
	public void SetTileEntityHangToTrue()
	{
		TileEntityHang = 1;
		markDirty();
	}
	
	public void SetTileEntityHangToFalse()
	{
		TileEntityHang = 0;
		markDirty();
	}
	
	@Override
	public void onLoad() {
		if (world.isRemote) {
			Main.network.sendToServer(new PacketRequestUpdateLantern(this));
		}
	}
	
	
	
}
