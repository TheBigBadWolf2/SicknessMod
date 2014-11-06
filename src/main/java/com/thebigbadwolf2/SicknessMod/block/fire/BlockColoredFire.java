package com.thebigbadwolf2.SicknessMod.block.fire;

import com.thebigbadwolf2.SicknessMod.block.compactedDust.BlockCompactedDust;
import com.thebigbadwolf2.SicknessMod.block.BlockWSMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

public class BlockColoredFire extends BlockWSMod
{

	public BlockColoredFire()
	{
		super(Material.fire);
		//this.setBlockName(Name());
		this.setCreativeTab(null);
		this.setTickRandomly(true);
	}

//	private String Name()
//	{
//		String name = this.getClass().getSimpleName();
//		name = name.replaceFirst("Block","");
//		char[] nameChar = name.toCharArray();
//		nameChar[0] = name.toLowerCase().charAt(0);
//		name = String.valueOf(nameChar);
//		return name;
//	}
//
//	@Override
//	public String getUnlocalizedName()
//	{
//		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
//	}

//	@Override
//	public IIcon getFireIcon(int p_149840_1_)
//	{
//		return blockIcon;
//	}

//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerBlockIcons(IIconRegister iconRegister)
//	{
//		blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
//	}
//
//	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
//	{
//		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
//	}


	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		entity.setFire(15);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.getBlock(x,y-1,z) instanceof BlockCompactedDust;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		world.setBlockToAir(x,y,z);
	}


	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (!this.canBlockStay(world, x, y, z)){
			world.setBlockToAir(x,y,z);
		}
	}


	public int tickRate(World world)
	{
		return 30;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (!canBlockStay(world, x, y, z)){
			world.setBlockToAir(x,y,z);
			//return;
		}

		int range = 1;

		for (int i = -range; i <= range;i++){
			for (int j = -range; j <= range;j++){
				for (int k = -range; k <= range;k++){
					int x2 = x+i;
					int y2 = y+j;
					int z2 = z+k;

					if (world.isAirBlock(x2,y2,z2)&&
					    !(world.isAirBlock(x2,y2-1,z2)||
					      world.getBlock(x2,y2-1,z2) instanceof BlockFire))
						world.setBlock(x2,y2,z2, Blocks.fire);
				}
			}
		}
	}

	public boolean isCollidable()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public int getRenderType()
	{
		return 6;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}
}
