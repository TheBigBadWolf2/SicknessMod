package com.thebigbadwolf2.SicknessMod.block.machines;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.tileentity.TileEntityDoorControl;
import com.thebigbadwolf2.SicknessMod.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
		TileEntityDoorControl tile = (TileEntityDoorControl) world.getTileEntity(x,y,z);
		boolean active;
		boolean powered = world.isBlockIndirectlyGettingPowered(x,y,z);
		boolean lastRS = tile.getLastRS();

		if (powered&&!lastRS){
			tile.toggleActive();
		}
		active = tile.isActive();
		if (powered&&!lastRS){
			if (active)this.doorClose(world,tile);
			else this.doorOpen(world,tile);
			LogHelper.info("min: (" + tile.getXmin() + "," + tile.getYmin() + "," + tile.getZmin() + ")");
		}

		if (powered!=lastRS)tile.setLastRS(powered);

	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (!player.isSneaking())return false;
		if (player.isUsingItem())return false;

		TileEntityDoorControl tile = (TileEntityDoorControl) world.getTileEntity(x,y,z);
		Block[] blocks = {world.getBlock(x+1,y,z),
		                  world.getBlock(x-1,y,z),
		                  world.getBlock(x,y,z+1),
		                  world.getBlock(x,y,z-1)};

		boolean isFrame = false;
		int frameSide = -1;
		for (int i = 0; i < blocks.length; i++){
			if (blocks[i]==ModBlocks.doorFrame){
				isFrame = true;
				frameSide = i;
			}
		}

		if (isFrame){
			switch (frameSide){
				case 0:
					for (int i = 0; i < 5; i++){
						if (world.getBlock(x+1,y-i,z)!=ModBlocks.doorFrame){
							tile.setYmin(y-i+1);
							break;
						}
					}
					for (int i = 0; i < 5; i++){
						if (world.getBlock(x+1,y+i,z)!=ModBlocks.doorFrame){
							tile.setYmax(y + i - 1);
							break;
						}
					}
					for (int i = 0; i < 10; i++)
					{
						if (world.getBlock(x+i+1,tile.getYmax(),z)!=ModBlocks.doorFrame){
							tile.setXmax(x+i);
							tile.setXmin(x+1);
							break;
						}
					}
					tile.setZmax(z);
					tile.setZmin(z);
					break;
				case 1:
					for (int i = 0; i < 10; i++){
						if (world.getBlock(x-1,y-i,z)!=ModBlocks.doorFrame){
							tile.setYmin(y-i+1);
							break;
						}
					}
					for (int i = 0; i < 10; i++){
						if (world.getBlock(x-1,y+i,z)!=ModBlocks.doorFrame){
							tile.setYmax(y+i-1);
							break;
						}
					}
					for (int i = 0; i < 10; i++)
					{
						if (world.getBlock(x-i-1,tile.getYmax(),z)!=ModBlocks.doorFrame){
							tile.setXmax(x-1);
							tile.setXmin(x-i);
							break;
						}
					}
					tile.setZmax(z);
					tile.setZmin(z);
					break;
				case 2:
					for (int i = 0; i < 10; i++){
						if (world.getBlock(x,y-i,z+1)!=ModBlocks.doorFrame){
							tile.setYmin(y-i+1);
							break;
						}
					}
					for (int i = 0; i < 10; i++){
						if (world.getBlock(x,y+i,z+1)!=ModBlocks.doorFrame){
							tile.setYmax(y+i-1);
							break;
						}
					}

					for (int i = 0; i < 10; i++)
					{
						if (world.getBlock(x,tile.getYmax(),z+i+1)!=ModBlocks.doorFrame){
							tile.setZmax(z + i);
							tile.setZmin(z + 1);
							break;
						}
					}
					tile.setXmax(x);
					tile.setXmin(x);
					break;
				case 3:
					for (int i = 0; i < 10; i++){
						if (world.getBlock(x,y-i,z-1)!=ModBlocks.doorFrame){
							tile.setYmin(y-i+1);
							break;
						}
					}
					for (int i = 0; i < 10; i++){
						if (world.getBlock(x,y+i,z-1)!=ModBlocks.doorFrame){
							tile.setYmax(y+i-1);
							break;
						}
					}
					for (int i = 0; i < 10; i++)
					{
						if (world.getBlock(x,tile.getYmax(),z-i-1)!=ModBlocks.doorFrame){
							tile.setZmax(z - 1);
							tile.setZmin(z - i);
							break;
						}
					}
					tile.setXmax(x);
					tile.setXmin(x);
					break;
			}
		}

		return true;
	}

	private void doorClose(World world, TileEntityDoorControl tile){
		if (tile.getXmin()==tile.getXmax()){
			for (int i = tile.getYmin()+1; i <= tile.getYmax()-1; i++)
				for (int j = tile.getZmin()+1; j <= tile.getZmax()-1; j++)
					door(world,tile.getXmin(),i,j,true);
		}else if (tile.getZmin()==tile.getZmax()){
			for (int i = tile.getXmin()+1; i <= tile.getXmax()-1; i++)
				for (int j = tile.getYmin()+1; j <= tile.getYmax()-1; j++)
					door(world,i,j,tile.getZmin(),true);
		}
	}

	private void door(World world, int x, int y, int z, boolean close){
		Block block = world.getBlock(x,y,z);
		if (close&&block==Blocks.air)world.setBlock(x,y,z,ModBlocks.door);
		else if (!close&&block==ModBlocks.door)world.setBlockToAir(x,y,z);
	}

	private void doorOpen(World world, TileEntityDoorControl tile){
		if (tile.getXmin()==tile.getXmax()){
			for (int i = tile.getYmin()+1; i <= tile.getYmax()-1; i++)
				for (int j = tile.getZmin()+1; j <= tile.getZmax()-1; j++)
					door(world,tile.getXmin(),i,j,false);
		}else if (tile.getZmin()==tile.getZmax()){
			for (int i = tile.getXmin()+1; i <= tile.getXmax()-1; i++)
				for (int j = tile.getYmin()+1; j <= tile.getYmax()-1; j++)
					door(world,i,j,tile.getZmin(),false);
		}
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
