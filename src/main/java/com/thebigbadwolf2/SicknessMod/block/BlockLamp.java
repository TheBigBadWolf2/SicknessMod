package com.thebigbadwolf2.SicknessMod.block;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockLamp extends BlockWSMod{

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = Blocks.redstone_lamp.getIcon(0,0);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (world.isBlockIndirectlyGettingPowered(x,y,z)&&world.isAirBlock(x,y-1,z))
			world.setBlock(x,y-1,z, ModBlocks.light,15,2);
		else if (world.getBlock(x,y-1,z)==ModBlocks.light)
			world.setBlockToAir(x,y-1,z);
	}
}
