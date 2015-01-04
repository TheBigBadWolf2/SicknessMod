package com.thebigbadwolf2.SicknessMod.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import oracle.jrockit.jfr.events.Bits;

import java.util.BitSet;

public class ItemInstaStructure extends ItemWSMod{

	Block stone = Blocks.cobblestone;
	Block wood = Blocks.planks;
	Block glass = Blocks.glass;
	Block bed = Blocks.bed;
	Block table = Blocks.crafting_table;
	Block chest = Blocks.chest;
	Block furnace = Blocks.furnace;
	Block torch = Blocks.torch;
	Block shroom = Blocks.red_mushroom_block;
	Block mycelium = Blocks.mycelium;
	//Block air = Blocks.air;
	Block door = Blocks.wooden_door;

	public ItemInstaStructure(){
		super(true);
		subNames.add("9x9");
		subNames.add("shroom");
	}

	int width = 11;
	int height = 5;

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

		/*for (int i = -width/2; i <= width/2; i++)
			for (int j = 0; j <= height; j++)
				for (int k = -width/2; k <= width/2; k++)
					world.setBlock(x+i,y+j,z+k,air);*/
		switch (stack.getItemDamage()) {
			case 0:
				for (int i = -width / 2; i <= width / 2; i++) {
					for (int j = 1; j <= height - 1; j++) {
						world.setBlock(x + i, y + j, z + width / 2, stone);
						world.setBlock(x + i, y + j, z - width / 2, stone);
						world.setBlock(x + width / 2, y + j, z + i, stone);
						world.setBlock(x - width / 2, y + j, z + i, stone);
					}
				}


				for (int i = -width / 2; i <= width / 2; i++) {
					for (int k = -width / 2; k <= width / 2; k++) {
						world.setBlock(x + i, y, z + k, wood);
					}
				}

				for (int i = -width / 2; i <= width / 2; i++) {
					for (int k = -width / 2; k <= width / 2; k++) {
						world.setBlock(x + i, y + height, z + k, stone);
					}
				}

				for (int i = -width / 2 + 2; i <= width / 2 - 2; i++) {
					for (int k = -width / 2 + 2; k <= width / 2 - 2; k++) {
						if (!(i == 0 || k == 0)) world.setBlock(x + i, y + height, z + k, glass);
					}
				}

				world.setBlock(x + (width / 2 - 1), y + 3, z, torch);
				world.setBlock(x - (width / 2 - 1), y + 3, z, torch);
				world.setBlock(x, y + 3, z + (width / 2 - 1), torch);
				world.setBlock(x, y + 3, z - (width / 2 - 1), torch);

				world.setBlock(x - width / 2, y + 1, z, door, 0x0, 2);
				world.setBlock(x - width / 2, y + 2, z, door, 0x8, 2);

				world.setBlock(x + width / 2 - 1, y + 1, z, bed, 0, 2);
				world.setBlock(x + width / 2 - 2, y + 1, z, bed, 0,2);
				break;
			case 1:
				for (int i = -2; i <= 2 ; i++)
					for (int k = -2; k <= 2 ; k++)
						world.setBlock(x+i,y,z+k,mycelium);

				for (int i = -1; i <= 1; i++) {
					for (int j = 1; j <= 3; j++) {
						world.setBlock(x+i,y+j,z+2,shroom,0,2);
						world.setBlock(x+i,y+j,z-2,shroom,0,2);
						world.setBlock(x+2,y+j,z+i,shroom,0,2);
						world.setBlock(x-2,y+j,z+i,shroom,0,2);
					}
				}

				for (int i = -1; i <= 1; i++) {
					for (int k = -1; k <= 1; k++) {
						world.setBlock(x+i,y+4,z+k,shroom);
					}
				}

				world.setBlock(x+1,y+1,z,chest);
				world.setBlock(x+1,y+1,z+1,table);
				world.setBlock(x-1,y+1,z+1,furnace);

				world.setBlock(x - 2, y + 1, z, door, 0x0, 2);
				world.setBlock(x - 2, y + 2, z, door, 0x8, 2);

				world.setBlock(x, y + 1, z - 1, bed, 0x3, 2);
				world.setBlock(x + 1, y + 1, z - 1, bed, 0x8|0x3, 2);

				world.setBlock(x - 1, y + 3, z, torch, 0x1, 2);

				break;
		}

		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
}
