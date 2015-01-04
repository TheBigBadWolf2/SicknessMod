package com.thebigbadwolf2.SicknessMod.tileentity;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityLight extends TileEntity{

	int tick = 0;

	@Override
	public void updateEntity() {
		if (tick%10==0) {
			int x = xCoord;
			int y = yCoord;
			int z = zCoord;
			int meta = blockMetadata;
			if (meta == -1) worldObj.setBlockToAir(x, y, z);
			//System.out.println(String.format("TE at (%d,%d,%d) with metadata: (%d) updated",x,y,z,meta));
			if (check(worldObj, x, y, z, meta)) if (meta > 0) {
				expand(worldObj, x + 1, y, z, meta - 1);
				expand(worldObj, x - 1, y, z, meta - 1);
				expand(worldObj, x, y, z + 1, meta - 1);
				expand(worldObj, x, y, z - 1, meta - 1);
				expand(worldObj, x, y - 1, z, meta - 1);
			}
		}
		tick++;
	}

	private boolean check(World world, int x, int y, int z, int meta){
		boolean shouldDisappear = true;
		if (world.getBlock(x+1,y,z)==getBlockType())
			if (world.getBlockMetadata(x+1,y,z)>meta)
				shouldDisappear = false;
		if (world.getBlock(x-1,y,z)==getBlockType())
			if (world.getBlockMetadata(x-1,y,z)>meta)
				shouldDisappear = false;
		if (world.getBlock(x,y,z+1)==getBlockType())
			if (world.getBlockMetadata(x,y,z+1)>meta)
				shouldDisappear = false;
		if (world.getBlock(x,y,z-1)==getBlockType())
			if (world.getBlockMetadata(x,y,z-1)>meta)
				shouldDisappear = false;
		if (world.getBlock(x,y+1,z)==getBlockType())
			if (world.getBlockMetadata(x,y+1,z)>meta)
				shouldDisappear = false;

		if (world.getBlock(x+1,y,z) == ModBlocks.lamp)
			shouldDisappear = false;
		if (world.getBlock(x-1,y,z) == ModBlocks.lamp)
			shouldDisappear = false;
		if (world.getBlock(x,y,z+1) == ModBlocks.lamp)
			shouldDisappear = false;
		if (world.getBlock(x,y,z-1) == ModBlocks.lamp)
			shouldDisappear = false;
		if (world.getBlock(x,y+1,z) == ModBlocks.lamp)
			shouldDisappear = false;

		if (shouldDisappear) {
			world.setBlockToAir(x, y, z);
			if (world.getBlock(x+1,y,z)==getBlockType())
				world.setBlockToAir(x+1, y, z);
			if (world.getBlock(x-1,y,z)==getBlockType())
				world.setBlockToAir(x-1, y, z);
			if (world.getBlock(x,y,z+1)==getBlockType())
				world.setBlockToAir(x, y, z+1);
			if (world.getBlock(x,y,z-1)==getBlockType())
				world.setBlockToAir(x, y, z-1);
			if (world.getBlock(x,y+1,z)==getBlockType())
				world.setBlockToAir(x, y+1, z);
		}

		return !shouldDisappear;
	}

	private void expand(World world, int x, int y, int z, int meta){
		if ((world.isAirBlock(x,y,z)&&world.getBlock(x,y,z)!=getBlockType())||(world.getBlock(x,y,z)==getBlockType()&&world.getBlockMetadata(x,y,z)<meta))
			world.setBlock(x,y,z,getBlockType(),meta,2);
	}
}
