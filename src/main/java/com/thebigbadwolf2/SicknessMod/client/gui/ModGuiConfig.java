package com.thebigbadwolf2.SicknessMod.client.gui;

import com.thebigbadwolf2.SicknessMod.handler.ConfigHandler;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModGuiConfig extends GuiConfig
{
	public ModGuiConfig(GuiScreen guiScreen)
	{
		super(guiScreen,
		      new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
		      Reference.MOD_ID,
		      false,
		      false,
		      GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
	}
}
