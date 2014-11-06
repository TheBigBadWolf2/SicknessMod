package com.thebigbadwolf2.SicknessMod.init;

import com.thebigbadwolf2.SicknessMod.block.*;
import com.thebigbadwolf2.SicknessMod.block.compactedDust.BlockCompactedBlueDust;
import com.thebigbadwolf2.SicknessMod.block.compactedDust.BlockCompactedGreenDust;
import com.thebigbadwolf2.SicknessMod.block.compactedDust.BlockCompactedRedDust;
import com.thebigbadwolf2.SicknessMod.block.crops.*;
import com.thebigbadwolf2.SicknessMod.block.crops.weed.BlockBlueDustWeed;
import com.thebigbadwolf2.SicknessMod.block.crops.weed.BlockGreenDustWeed;
import com.thebigbadwolf2.SicknessMod.block.crops.weed.BlockRedDustWeed;
import com.thebigbadwolf2.SicknessMod.block.crops.weed.BlockWeed;
import com.thebigbadwolf2.SicknessMod.block.fire.BlockBlueFire;
import com.thebigbadwolf2.SicknessMod.block.fire.BlockColoredFire;
import com.thebigbadwolf2.SicknessMod.block.fire.BlockGreenFire;
import com.thebigbadwolf2.SicknessMod.block.fire.BlockRedFire;
import com.thebigbadwolf2.SicknessMod.block.machines.BlockBaseMachine;
import com.thebigbadwolf2.SicknessMod.block.machines.BlockMachineDoor;
import com.thebigbadwolf2.SicknessMod.block.machines.BlockMachineDoorControl;
import com.thebigbadwolf2.SicknessMod.block.ore.BlockBlueOre;
import com.thebigbadwolf2.SicknessMod.block.ore.BlockGreenOre;
import com.thebigbadwolf2.SicknessMod.block.ore.BlockOre;
import com.thebigbadwolf2.SicknessMod.block.ore.BlockRedOre;
import com.thebigbadwolf2.SicknessMod.item.itemblock.ItemBlockMimic;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
	public static final BlockWSMod compactedRedDust = new BlockCompactedRedDust();
	public static final BlockWSMod compactedGreenDust = new BlockCompactedGreenDust();
	public static final BlockWSMod compactedBlueDust = new BlockCompactedBlueDust();

	public static final BlockWSMod grassCement = new BlockGrassCement();
	public static final BlockWSMod mimic = new BlockMimic();

	public static final BlockColoredFire redFire = new BlockRedFire();
	public static final BlockColoredFire greenFire = new BlockGreenFire();
	public static final BlockColoredFire blueFire = new BlockBlueFire();

	public static final BlockOre redOre = new BlockRedOre();
	public static final BlockOre greenOre = new BlockGreenOre();
	public static final BlockOre blueOre = new BlockBlueOre();

	public static final BlockCropWSMod redDustCrop = new BlockRedDustCrop();
	public static final BlockCropWSMod greenDustCrop = new BlockGreenDustCrop();
	public static final BlockCropWSMod blueDustCrop = new BlockBlueDustCrop();

	public static final BlockWeed redDustWeed = new BlockRedDustWeed();
	public static final BlockWeed greenDustWeed = new BlockGreenDustWeed();
	public static final BlockWeed blueDustWeed = new BlockBlueDustWeed();

	public static final BlockWSMod encrustedMycelium = new BlockEncrustedMycelium();
	public static final BlockWSMod decomp = new BlockDecomp();

	public static final BlockBaseMachine doorControl = new BlockMachineDoorControl();
	public static final BlockBaseMachine door = new BlockMachineDoor();

	public static void init(){
		registerCompactedDust();
		GameRegistry.registerBlock(grassCement, grassCement.getName());
		GameRegistry.registerBlock(encrustedMycelium,"encrustedMycelium");
		GameRegistry.registerBlock(decomp,"decomp");
		GameRegistry.registerBlock(mimic, ItemBlockMimic.class,mimic.getName());
		registerFire();
		registerOre();
		registerCrops();
		registerWeeds();
		registerMachines();
	}

	private static void registerCompactedDust(){
		GameRegistry.registerBlock(compactedRedDust,compactedRedDust.getName());
		GameRegistry.registerBlock(compactedGreenDust,compactedGreenDust.getName());
		GameRegistry.registerBlock(compactedBlueDust,compactedBlueDust.getName());

		Blocks.fire.setFireInfo(compactedRedDust, 1000, 0);
		Blocks.fire.setFireInfo(compactedGreenDust,1000,0);
		Blocks.fire.setFireInfo(compactedBlueDust,1000,0);
	}

	private static void registerFire(){
		GameRegistry.registerBlock(redFire,redFire.getName());
		GameRegistry.registerBlock(greenFire,greenFire.getName());
		GameRegistry.registerBlock(blueFire,blueFire.getName());
	}

	private static void registerOre(){
		GameRegistry.registerBlock(redOre,redOre.getName());
		GameRegistry.registerBlock(greenOre,greenOre.getName());
		GameRegistry.registerBlock(blueOre,blueOre.getName());
	}

	private static void registerCrops(){
		GameRegistry.registerBlock(redDustCrop,redDustCrop.getName());
		GameRegistry.registerBlock(greenDustCrop,greenDustCrop.getName());
		GameRegistry.registerBlock(blueDustCrop,blueDustCrop.getName());
	}

	private static void registerMachines(){
		GameRegistry.registerBlock(doorControl,"doorControl");
		GameRegistry.registerBlock(door,"door");
	}

	private static void registerWeeds(){
		GameRegistry.registerBlock(redDustWeed, /*ItemBlockRedDustWeed.class,*/"redDustWeed");
		GameRegistry.registerBlock(greenDustWeed, /*ItemBlockGreenDustWeed.class,*/"greenDustWeed");
		GameRegistry.registerBlock(blueDustWeed, /*ItemBlockBlueDustWeed.class,*/"blueDustWeed");
	}

	public static boolean isOrganic(Material material){
		return material == Material.grass||
		        material == Material.wood||
		        material == Material.clay||
		        material == Material.ground||
		        material == Material.cloth||
		        material == Material.gourd||
		        material == Material.leaves||
		        material == Material.vine||
		        material == Material.carpet||
		        material == Material.cake||
		        material == Material.web||
		        material == Material.tnt;
	}

	public static boolean isOrganic(Block block){
		return isOrganic(block.getMaterial())&&
		       !(block==Blocks.wooden_door);
	}
}
