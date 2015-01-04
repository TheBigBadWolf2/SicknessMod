package com.thebigbadwolf2.SicknessMod.block;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.tileentity.TileEntityLamp;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLamp extends BlockWSMod implements ITileEntityProvider{

	public BlockLamp(){
		super(Material.redstoneLight);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = Blocks.redstone_lamp.getIcon(0,0);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		//toggle(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		//toggle(world, x, y, z);
	}

	private void toggle(World world, int x, int y, int z){
		if (world.isBlockIndirectlyGettingPowered(x,y,z)){
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

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityLamp();
	}
}
