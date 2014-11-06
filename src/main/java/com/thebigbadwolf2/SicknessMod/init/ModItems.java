package com.thebigbadwolf2.SicknessMod.init;

import com.thebigbadwolf2.SicknessMod.item.armor.ItemArmorGasMask;
import com.thebigbadwolf2.SicknessMod.item.armor.ItemArmorWSMod;
import com.thebigbadwolf2.SicknessMod.item.dust.ItemBlueDust;
import com.thebigbadwolf2.SicknessMod.item.dust.ItemGreenDust;
import com.thebigbadwolf2.SicknessMod.item.dust.ItemRedDust;
import com.thebigbadwolf2.SicknessMod.item.ItemWSMod;
import com.thebigbadwolf2.SicknessMod.item.edible.*;
import com.thebigbadwolf2.SicknessMod.item.seeds.ItemBlueDustSeed;
import com.thebigbadwolf2.SicknessMod.item.seeds.ItemGreenDustSeed;
import com.thebigbadwolf2.SicknessMod.item.seeds.ItemRedDustSeed;
import com.thebigbadwolf2.SicknessMod.item.seeds.ItemSeed;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemArmor;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
	public static final ItemWSMod redDust = new ItemRedDust();
	public static final ItemWSMod greenDust = new ItemGreenDust();
	public static final ItemWSMod blueDust = new ItemBlueDust();

	public static final ItemEdible nutrientPaste = new ItemNutrientPaste();
	public static final ItemEdible throwUp = new ItemThrowUp();

	public static final ItemMedicine pillBase = new ItemMedicine();
	public static final ItemMedicine regenPill = new ItemRegenMed();

	public static final ItemSicknessPill sickPill = new ItemSicknessPill();
	public static final ItemSicknessPill fluPill = new ItemFlu();

	public static final ItemSeed redDustSeed = new ItemRedDustSeed();
	public static final ItemSeed greenDustSeed = new ItemGreenDustSeed();
	public static final ItemSeed blueDustSeed = new ItemBlueDustSeed();

	public static final ItemArmorWSMod gasMask = new ItemArmorGasMask(0);
	public static final ItemArmorWSMod gasSuitChest = new ItemArmorGasMask(1);
	public static final ItemArmorWSMod gasSuitLegs = new ItemArmorGasMask(2);
	public static final ItemArmorWSMod gasSuitBoots = new ItemArmorGasMask(3);

	public static void init(){
		registerDust();
		registerFood();
		registerSeeds();
		GameRegistry.registerItem(gasMask,"gasMask");
		GameRegistry.registerItem(gasSuitChest,"gasSuitChest");
		GameRegistry.registerItem(gasSuitLegs,"gasSuitLegs");
		GameRegistry.registerItem(gasSuitBoots,"gasSuitBoots");
	}

	private static void registerDust(){
		GameRegistry.registerItem(redDust,redDust.getName());
		GameRegistry.registerItem(greenDust,greenDust.getName());
		GameRegistry.registerItem(blueDust,blueDust.getName());
	}

	private static void registerFood(){
		GameRegistry.registerItem(nutrientPaste,nutrientPaste.getName());
		GameRegistry.registerItem(throwUp,throwUp.getName());
		registerMedicine();
		registerSickness();
	}

	private static void registerMedicine(){
		GameRegistry.registerItem(pillBase,"pillBase");
		GameRegistry.registerItem(regenPill,"regenPill");
	}

	private static void registerSickness(){
		GameRegistry.registerItem(sickPill,"sickPill");
		GameRegistry.registerItem(fluPill,"fluPill");
	}

	private static void registerSeeds(){
		GameRegistry.registerItem(redDustSeed,redDustSeed.getName());
		GameRegistry.registerItem(greenDustSeed,greenDustSeed.getName());
		GameRegistry.registerItem(blueDustSeed,blueDustSeed.getName());
	}
}
