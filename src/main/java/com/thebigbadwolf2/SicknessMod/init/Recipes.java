package com.thebigbadwolf2.SicknessMod.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
	private static ItemStack redDust = new ItemStack(ModItems.redDust);
	private static ItemStack greenDust = new ItemStack(ModItems.greenDust);
	private static ItemStack blueDust = new ItemStack(ModItems.blueDust);

	private static ItemStack redCompactedDust = new ItemStack(ModBlocks.compactedRedDust);
	private static ItemStack greenCompactedDust = new ItemStack(ModBlocks.compactedGreenDust);
	private static ItemStack blueCompactedDust = new ItemStack(ModBlocks.compactedBlueDust);

	private static ItemStack paste = new ItemStack(ModItems.nutrientPaste);
	private static ItemStack pill = new ItemStack(ModItems.regenPill);

	private static ItemStack redSeed = new ItemStack(ModItems.redDustSeed);
	private static ItemStack greenSeed = new ItemStack(ModItems.greenDustSeed);
	private static ItemStack blueSeed = new ItemStack(ModItems.blueDustSeed);

	private static ItemStack water = new ItemStack(Items.water_bucket);
	private static ItemStack bowl = new ItemStack(Items.bowl);
	private static ItemStack paper = new ItemStack(Items.paper);
	private static ItemStack seed = new ItemStack(Items.wheat_seeds);

	public static void init(){
		GameRegistry.addRecipe(paste,"rgb",
			                         " w ",
			                         " f ",
			                         'r',redDust,'g',greenDust,
			                         'b',blueDust,'w',water,
			                         'f',bowl);

		GameRegistry.addRecipe(redSeed,"prp",
				                       "psp",
				                       "ppp",
				                       'r',redDust,'p',paper,
				                       's',seed);
		GameRegistry.addRecipe(greenSeed,"pgp",
				                         "psp",
				                         "ppp",
				                         'g',greenDust,'p',paper,
				                         's',seed);
		GameRegistry.addRecipe(blueSeed,"pbp",
				                        "psp",
				                        "ppp",
				                        'b',blueDust,'p',paper,
				                        's',seed);

		GameRegistry.addRecipe(redCompactedDust,"dd","dd",'d',redDust);
		GameRegistry.addRecipe(greenCompactedDust,"dd","dd",'d',greenDust);
		GameRegistry.addRecipe(blueCompactedDust,"dd","dd",'d',blueDust);

		GameRegistry.addShapelessRecipe(pill,paste,paper);
	}
}
