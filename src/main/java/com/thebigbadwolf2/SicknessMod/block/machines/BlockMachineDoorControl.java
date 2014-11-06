package com.thebigbadwolf2.SicknessMod.block.machines;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.tileentity.TileEntityDoorControl;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachineDoorControl extends BlockBaseMachine
{
	public BlockMachineDoorControl(){
		super();

	}

	@Override
	public int getRenderType()
	{
		return super.getRenderType();
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (world.isBlockIndirectlyGettingPowered(x,y,z)){
			world.setBlock(x,y+1,z, ModBlocks.door);
		}else world.setBlock(x,y+1,z, Blocks.air);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livingBase, ItemStack itemStack)
	{
		byte b0 = 0;
		int l = MathHelper.floor_double((double) (livingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0)
		{
			b0 = 0;
		}

		if (l == 1)
		{
			b0 = 3;
		}

		if (l == 2)
		{
			b0 = 1;
		}

		if (l == 3)
		{
			b0 = 2;
		}

		world.setBlockMetadataWithNotify(x, y, z, b0, 3);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityDoorControl();
	}
}
