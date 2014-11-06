package com.thebigbadwolf2.SicknessMod.block.compactedDust;

import com.thebigbadwolf2.SicknessMod.block.BlockWSMod;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import com.thebigbadwolf2.SicknessMod.item.dust.ItemDust;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class BlockCompactedDust extends BlockWSMod
{
	public BlockCompactedDust()
	{
		super();
		this.setHardness(10);
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return super.getFlammability(world, x, y, z, face);
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
	{
		if (!(explosion.exploder instanceof EntityItem) || !(((EntityItem) explosion.exploder).getEntityItem().getItem() instanceof ItemDust)){
			EntityCreeper creeper = new EntityCreeper(world);
			creeper.setPosition(x + 0.5, y, z + 0.5);
			creeper.setWorld(world);
			if (!world.isRemote)world.spawnEntityInWorld(creeper);
			if (!world.isRemote)world.setBlock(x, y, z, Blocks.air);
		}
		//super.onBlockExploded(world, x, y, z, explosion);
	}

	//@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		Block block1 = world.getBlock(x,y+1,z);
		Block block2 = world.getBlock(x,y,z);
		if (block1 instanceof BlockFire){
			if (block2 instanceof BlockCompactedRedDust)
				world.setBlock(x,y+1,z,ModBlocks.redFire);
			else if (block2 instanceof BlockCompactedGreenDust)
				world.setBlock(x,y+1,z,ModBlocks.greenFire);
			else if (block2 instanceof BlockCompactedBlueDust)
				world.setBlock(x,y+1,z,ModBlocks.blueFire);
		}
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
	{
		int size;
		if (meta==0) size=4;
		else size = meta;
		ItemStack stack = null;
		EntityItem stackEnt = null;
		if (this.equals(ModBlocks.compactedRedDust))
			stack = new ItemStack(ModItems.redDust,size);
		else if (this.equals(ModBlocks.compactedGreenDust))
			stack = new ItemStack(ModItems.greenDust,size);
		else if (this.equals(ModBlocks.compactedBlueDust))
			stack = new ItemStack(ModItems.blueDust,size);

		if (stack!=null){
			stackEnt = new EntityItem(world,x+0.5,y+0.5,z+0.5,stack);

		}
		if (stackEnt!=null)
		{
			if (!world.isRemote&&this.canHarvestBlock(player,0))
				world.spawnEntityInWorld(stackEnt);
		}
	}
}

//class StackEnt extends EntityItem
//{
//
//	public StackEnt(World world, double x, double y, double z, ItemStack stack)
//	{
//		super(world, x, y, z, stack);
//	}
//
//	@Override
//	public void onCollideWithPlayer(EntityPlayer player)
//	{
//		this.worldObj.createExplosion(player, this.posX, this.posY, this.posZ, 1, true);
//		super.onCollideWithPlayer(player);
//	}
//}
