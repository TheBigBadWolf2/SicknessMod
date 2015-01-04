package com.thebigbadwolf2.SicknessMod.reference;

import com.thebigbadwolf2.SicknessMod.block.BlockDecomp;
import com.thebigbadwolf2.SicknessMod.block.BlockEncrustedMycelium;
import com.thebigbadwolf2.SicknessMod.block.crops.BlockCropWSMod;
import com.thebigbadwolf2.SicknessMod.block.crops.weed.BlockWeed;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class MethodRef {

	public static class Good {

		public static class Convert {

			public static boolean convert(World world, int x, int y, int z) {
				Block blockA = world.getBlock(x, y, z);
				Block blockB = world.getBlock(x, y - 1, z);
				Block block1 = null;
				Block block2 = null;
				if (!world.isRemote) if (blockA instanceof BlockCropWSMod) {
					if ((blockA instanceof BlockWeed)) {
						if (world.getBlockMetadata(x, y, z) == 0) {
							Block[] blocks = getBlocks(world, blockB);
							block1 = blocks[0];
							block2 = blocks[1];
						}
						else block1 = Blocks.air;
					}
					else {
						Block[] blocks = getBlocks(world, blockB);
						block1 = blocks[0];
						block2 = blocks[1];
					}

				}
				else if (blockA instanceof BlockEncrustedMycelium || blockA instanceof BlockDecomp) {
					block1 = convertSoil(world);
				}
				if (block1 != null) {
					if (block1 == Blocks.red_flower) world.setBlock(x, y, z, block1, world.rand.nextInt(8), 2);
					else if (block1 == Blocks.double_plant) {
						int r = world.rand.nextInt(5);
						world.setBlock(x, y, z, block1, r, 2);
						world.setBlock(x, y + 1, z, block1, 8, 2);
					}
					else world.setBlock(x, y, z, block1, 0, 2);
					if (block2 != null) world.setBlock(x, y - 1, z, block2, 0, 2);
					return true;
				}
				return false;
			}

			private static Block[] getBlocks(World world, Block blockB) {
				Block[] blocks = new Block[2];
				blocks[0] = convertPlant(world);
				if (blocks[0] instanceof BlockCrops) {
					if (blockB instanceof BlockEncrustedMycelium ||
					    blockB instanceof BlockDecomp ||
					    blockB instanceof BlockDirt ||
					    blockB instanceof BlockGrass ||
					    blockB instanceof BlockMycelium) blocks[1] = Blocks.farmland;
					else blocks[0] = Blocks.air;
				}
				else if (blocks[0] instanceof BlockMushroom) {
					blocks[1] = Blocks.mycelium;
				}
				else if (blockB instanceof BlockEncrustedMycelium || blockB instanceof BlockDecomp) blocks[1] = convertSoil(world);
				return blocks;
			}

			private static Block[] soils = {Blocks.grass,
			                                Blocks.dirt,
			                                Blocks.mycelium};

			private static Block convertSoil(World world) {
				int r = world.rand.nextInt(soils.length);
				return soils[r];
			}

			private static Block[] plants = {Blocks.tallgrass,
			                                 Blocks.deadbush,
			                                 Blocks.yellow_flower,
			                                 Blocks.red_flower,
			                                 Blocks.double_plant,
			                                 Blocks.brown_mushroom,
			                                 Blocks.red_mushroom,
			                                 Blocks.wheat,
			                                 Blocks.reeds,
			                                 Blocks.pumpkin,
			                                 Blocks.melon_block,
			                                 Blocks.carrots,
			                                 Blocks.potatoes};

			private static Block convertPlant(World world) {
				int r = world.rand.nextInt(plants.length);

				return plants[r];
			}
		}

		public static class Update{
			public static void update(World world, Block block, int x, int y, int z, Random rand){
				if (world.rand.nextInt(10) == 0) {
					block.updateTick(world, x, y, z, rand);
				}if (world.rand.nextInt(10) == 0) {
					if (block instanceof BlockMushroom)
						((BlockMushroom)block).func_149853_b(world,rand, x, y, z);
					else if (block instanceof BlockCrops){
						((BlockCrops)block).func_149853_b(world,rand, x, y, z);
					}
				}
			}
		}
	}

	public static class Bad {

		public static class Convert {

			public static void convert(World world, int x, int y, int z, boolean empowered){
				Block blockA = world.getBlock(x,y,z);
				Block blockB = world.getBlock(x,y-1,z);
				Block block1 = null;
				Block block2 = null;
				if (blockA instanceof BlockBush&&!(blockA instanceof BlockCropWSMod))
				{
					block1 = convertPlant(world, empowered);
					if (empowered&&block1 instanceof BlockWeed)block2 = ModBlocks.encrustedMycelium;
					else if (empowered&&(blockB == Blocks.dirt||blockB == Blocks.grass||blockB == Blocks.mycelium))
						block2=convertSoil(world);
				}
				else if (empowered){
					if(blockA == Blocks.grass||blockA == Blocks.mycelium) block1 = convertSoil(world);
					else if (blockA == Blocks.dirt)block1 = ModBlocks.decomp;
				}
				if (block1!=null)
				{
					if (block1 instanceof BlockWeed){
						world.setBlock(x, y, z, block1,0,2);
						world.setBlock(x, y+1, z, block1,2,2);
					}else if (empowered)world.setBlock(x, y, z, block1,world.rand.nextInt(8),2);
					else world.setBlock(x, y, z, block1);
				}
				if (block2!=null)world.setBlock(x,y-1,z,block2);
			}

			private static Block[] soil = {ModBlocks.encrustedMycelium,
			                               ModBlocks.decomp};

			private static Block convertSoil(World world){
				int r = world.rand.nextInt(soil.length);
				return soil[r];
			}

			private static Block[] plant = {ModBlocks.redDustCrop,
			                                ModBlocks.greenDustCrop,
			                                ModBlocks.blueDustCrop,
			                                ModBlocks.redDustWeed,
			                                ModBlocks.greenDustWeed,
			                                ModBlocks.blueDustWeed};

			private static Block convertPlant(World world, boolean empowered){
				int r;
				if (empowered)r = world.rand.nextInt(plant.length);
				else r = world.rand.nextInt(plant.length/2);

				return plant[r];
			}
		}
	}

}
