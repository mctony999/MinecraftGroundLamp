package com.Tonyloser.lampmod;

import org.apache.logging.log4j.Logger;

import com.Tonyloser.lampmod.blocks.GroundLamp;
import com.Tonyloser.lampmod.init.ModRecipes;
import com.Tonyloser.lampmod.proxy.CommonProxy;
import com.Tonyloser.lampmod.util.Reference;
import com.Tonyloser.lampmod.util.handlers.RegistryHandler;
import com.Tonyloser.lampmod.util.handlers.SoundsHandler;
import com.Tonyloser.lampmod.network.PacketRequestUpdateLantern;
import com.Tonyloser.lampmod.network.PacketUpdateLantern;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	public static SimpleNetworkWrapper network; //For network stuff.
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static Logger logger; //For log
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		SoundsHandler.registerSounds();
//		Networking part start
		network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		network.registerMessage(new PacketUpdateLantern.Handler(), PacketUpdateLantern.class, 0, Side.CLIENT);
		network.registerMessage(new PacketRequestUpdateLantern.Handler(), PacketRequestUpdateLantern.class, 1, Side.SERVER);
//		Networking part end
		proxy.registerRenderers(); //For TileEntity render
		
		logger = event.getModLog(); //For log
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		ModRecipes.init();
	}
	
	@EventHandler
	public static void Postinit(FMLPostInitializationEvent event)
	{
		
	}
	


}
