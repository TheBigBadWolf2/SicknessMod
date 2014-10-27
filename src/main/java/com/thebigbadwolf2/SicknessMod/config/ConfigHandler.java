package com.thebigbadwolf2.SicknessMod.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler
{
	public static void init(File configFile)
	{
		Configuration config = new Configuration(configFile);
		boolean configValue = false;
		try
		{
			config.load();
			configValue = config.get("Sickness",
			                         "configValue",true,
			                         "This is an example config value")
					.getBoolean(true);
		}
		catch (Exception ex){}
		finally
		{
			config.save();
		}

		System.out.println("Configuration Test: " + configValue);
	}
}
