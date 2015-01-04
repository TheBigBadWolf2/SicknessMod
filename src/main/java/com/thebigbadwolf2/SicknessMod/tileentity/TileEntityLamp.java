package com.thebigbadwolf2.SicknessMod.tileentity;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityLamp extends TileEntity{

	private boolean rsState = false;

	@Override
	public void updateEntity() {

		int x = xCoord;
		int y = yCoord;
		int z = zCoord;

		if (worldObj.isBlockIndirectlyGettingPowered(x,y,z)!=rsState) {
			rsState = worldObj.isBlockIndirectlyGettingPowered(x,y,z);
			toggle(worldObj,x,y,z,rsState);
		}
	}

	private void toggle(World world, int x, int y, int z, boolean state){
		if (state){
			spread(world, x + 1, y, z);
			spread(world, x - 1, y, z);
			spread(world, x, y, z + 1);
			spread(world, x, y, z - 1);
			spread(world, x, y - 1, z);
		}else {
			remove(world, x + 1, y, z);
			remove(world, x - 1, y, z);
			remove(world, x, y, z + 1);
			remove(world, x, y, z - 1);
			remove(world, x, y - 1, z);
		}
	}

	private void remove(World world, int x, int y, int z){
		if (world.getBlock(x,y,z)==ModBlocks.light)
			world.setBlockToAir(x,y,z);
	}

	private void spread(World world, int x, int y, int z){
		if (world.isAirBlock(x,y,z))
			world.setBlock(x,y,z, ModBlocks.light,15,2);
	}

}
