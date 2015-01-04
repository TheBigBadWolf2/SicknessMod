package com.thebigbadwolf2.SicknessMod.init;

import com.thebigbadwolf2.SicknessMod.item.*;
import com.thebigbadwolf2.SicknessMod.item.armor.ItemArmorBadSuit;
import com.thebigbadwolf2.SicknessMod.item.armor.ItemArmorGasMask;
import com.thebigbadwolf2.SicknessMod.item.armor.ItemArmorGoodSuit;
import com.thebigbadwolf2.SicknessMod.item.armor.ItemArmorWSMod;
import com.thebigbadwolf2.SicknessMod.item.dust.ItemBlueDust;
import com.thebigbadwolf2.SicknessMod.item.dust.ItemGreenDust;
import com.thebigbadwolf2.SicknessMod.item.dust.ItemRedDust;
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

	public static final ItemWSMod conversionDustA = new ItemConvertDustA();
	public static final ItemWSMod conversionDustB = new ItemConvertDustB();
	public static final ItemWSMod magicBoneMeal = new ItemMagicBoneMeal();
	public static final ItemWSMod instaStructure = new ItemInstaStructure();

	public static final ItemWSMod arrowShooter = new ItemArrowShooter();

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

	public static final ItemArmorWSMod goodSuitChest = new ItemArmorGoodSuit(1);
	public static final ItemArmorWSMod goodSuitLegs = new ItemArmorGoodSuit(2);
	public static final ItemArmorWSMod goodSuitBoots = new ItemArmorGoodSuit(3);

	public static final ItemArmorWSMod badSuitChest = new ItemArmorBadSuit(1);
	public static final ItemArmorWSMod badSuitLegs = new ItemArmorBadSuit(2);
	public static final ItemArmorWSMod badSuitBoots = new ItemArmorBadSuit(3);


	public static void init(){
		registerDust();
		registerFood();
		registerSeeds();
		registerArmor();
		GameRegistry.registerItem(conversionDustA,"conversionDustA");
		GameRegistry.registerItem(conversionDustB,"conversionDustB");
		GameRegistry.registerItem(magicBoneMeal,"magicBoneMeal");
		GameRegistry.registerItem(instaStructure,"instaStructure");
		GameRegistry.registerItem(arrowShooter,"arrowShooter");
	}

	private static void registerArmor(){
		GameRegistry.registerItem(gasMask,"gasMask");
		GameRegistry.registerItem(gasSuitChest,"gasSuitChest");
		GameRegistry.registerItem(gasSuitLegs,"gasSuitLegs");
		GameRegistry.registerItem(gasSuitBoots,"gasSuitBoots");

		GameRegistry.registerItem(goodSuitChest,"goodSuitChest");
		GameRegistry.registerItem(goodSuitLegs,"goodSuitLegs");
		GameRegistry.registerItem(goodSuitBoots,"goodSuitBoots");

		GameRegistry.registerItem(badSuitChest,"badSuitChest");
		GameRegistry.registerItem(badSuitLegs,"badSuitLegs");
		GameRegistry.registerItem(badSuitBoots,"badSuitBoots");
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
