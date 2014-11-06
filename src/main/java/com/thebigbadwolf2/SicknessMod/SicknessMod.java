package com.thebigbadwolf2.SicknessMod;

import com.thebigbadwolf2.SicknessMod.handler.ConfigHandler;
import com.thebigbadwolf2.SicknessMod.handler.EventHandlerWSMod;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import com.thebigbadwolf2.SicknessMod.init.Recipes;
import com.thebigbadwolf2.SicknessMod.potion.ModPotion;
import com.thebigbadwolf2.SicknessMod.proxy.IProxy;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import com.thebigbadwolf2.SicknessMod.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Mod(modid = Reference.MOD_ID,
     name = Reference.MOD_NAME,
     version = Reference.VERSION,
     guiFactory = Reference.GUI_FACTORY_CLASS)
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
		FMLCommonHandler.instance().bus().register(new ConfigHandler());

		Potion[] potionTypes = null;

		for (Field f : Potion.class.getDeclaredFields())
		{
			f.setAccessible(true);
			try
			{
				if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a"))
				{
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

					potionTypes = (Potion[]) f.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
					f.set(null, newPotionTypes);
				}
			}
			catch (Exception e)
			{
				System.err.println("Severe error, please report this to the mod author:");
				System.err.println(e);
			}
		}

		MinecraftForge.EVENT_BUS.register(new EventHandlerWSMod());
		LogHelper.info("Pre Initialization Complete!");

		ModItems.init();
		ModBlocks.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{

		Recipes.init();
		LogHelper.info("Initialization Complete!");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Post Initialization Complete!");

		for (String oreName : OreDictionary.getOreNames()){
			LogHelper.info(oreName);
		}
	}
}
