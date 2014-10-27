package com.thebigbadwolf2.SicknessMod;

import com.thebigbadwolf2.SicknessMod.config.ConfigHandler;
import com.thebigbadwolf2.SicknessMod.proxy.IProxy;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID,
     name = Reference.MOD_NAME,
     version = Reference.VERSION)
public class SicknessMod
{
	@Mod.Instance(Reference.MOD_ID)
	public static SicknessMod instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,
	            serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHandler.init(event.getSuggestedConfigurationFile());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
