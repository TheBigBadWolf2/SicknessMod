package com.thebigbadwolf2.SicknessMod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGrassCement extends BlockWSMod
{
	public BlockGrassCement(){
		super(Material.grass);
		this.setTickRandomly(true);
	}

	@Override
	public int tickRate(World world)
	{
		return 1;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random){
		//if (!world.isRemote)
		{
			for (int i = -1; i <= 1; i++)
				for (int j = -1; j <= 0; j++)
					for (int k = -1; k <= 1; k++)
						if (world.isAirBlock(x+i,y+j,z+k))
							world.setBlock(x+i,y+j,z+k,this);
			world.setBlock(x,y,z,Blocks.grass);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = Blocks.dirt.getBlockTextureFromSide(0);
	}
}
