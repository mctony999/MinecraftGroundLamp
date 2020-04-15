package com.Tonyloser.lampmod.proxy;

import com.Tonyloser.lampmod.blocks.TESGroundLamp;
import com.Tonyloser.lampmod.tileentity.TileEntityHang;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy 
{
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	@Override
	public void registerRenderers() 
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHang.class, new TESGroundLamp());
	}
}
