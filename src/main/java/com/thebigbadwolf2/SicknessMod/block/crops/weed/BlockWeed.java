package com.thebigbadwolf2.SicknessMod.block.crops.weed;

import com.thebigbadwolf2.SicknessMod.block.crops.BlockCropWSMod;
import com.thebigbadwolf2.SicknessMod.creativetab.CreativeTabWSMod;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import com.thebigbadwolf2.SicknessMod.potion.ModPotion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockWeed extends BlockCropWSMod implements IGrowable
{
	private BlockCropWSMod plant = null;

	public BlockWeed(BlockCropWSMod block)
	{
		super();
		plant = block;
		float f = 0.4f;
		this.setCreativeTab(CreativeTabWSMod.WSMod_TAB_BLOCKS);
		this.setBlockBounds(0.0F+f, 0.0F, 0.0F+f, 1.0F-f, 1.0F, 1.0F-f);
		this.icons = new IIcon[3];
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
	{
		for (int i = 0; i < 6; i++)
			if (world.getBlock(x,y+i,z)==this){
				world.getBlock(x,y+i,z).dropBlockAsItem(world,x,y+i,z,0,0);
				world.setBlockToAir(x,y+i,z);
			}

	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (!canBlockStay(world, x, y, z)){
			for (int i = 0; i < 6; i++)
			{
				if (world.getBlock(x,y+i,z)==this){
					world.setBlockToAir(x,y+i,z);
				}else break;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_base");
		icons[1] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_middle");
		icons[2] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_top");
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		for (int i = 0; i < 3; i++)
		{
			list.add(new ItemStack(this,1,i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta>2)
			return icons[2];
		else return icons[meta];
	}

	@Override
	public boolean canPlaceBlockOn(Block block)
	{
		return block==ModBlocks.encrustedMycelium||block==this;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x,y,z);
		if (world.isAirBlock(x,y-1,z))return false;
		if (meta == 0)
			return canPlaceBlockOn(world.getBlock(x,y-1,z));
		else if (meta == 1)
			return world.getBlock(x,y-1,z) == this&&
			       (world.getBlockMetadata(x,y-1,z) == 0 ||
			        world.getBlockMetadata(x,y-1,z) == 1);
		else if (meta == 2)
			return world.getBlock(x,y-1,z) == this&&
			       world.getBlockMetadata(x,y-1,z) == 1;

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livingBase, ItemStack stack)
	{
		if (stack.getItem().getDamage(stack)==0){
			if (canPlaceBlockOn(world.getBlock(x,y-1,z))){
				world.setBlock(x,y,z,this,0,3);
				world.setBlock(x,y+1,z,this,2,3);
			}
		}

	}

	private boolean canGrow(World world, int x, int y, int z){
		if (world.isAirBlock(x, y - 1, z)) world.setBlockToAir(x, y, z);
		if (world.getBlockMetadata(x,y,z)==2&&
		    world.isAirBlock(x,y+1,z))
		{
			for (int i = 0; i > -6; i--)
			{
				if (world.getBlock(x, y + i, z) != this)
				{
					if (world.getBlockMetadata(x, y + i + 1, z) == 0)
						return true;
					else for (int j = 0; j > i ; j--)
					{
						if (world.getBlock(x,y+j,z)==this)
						{
							world.getBlock(x,y+j,z).dropBlockAsItem(world,x,y+j,z,0,0);
							world.setBlockToAir(x, y + j, z);
						}else break;
					}
				}

			}
		}

		if (world.getBlockMetadata(x, y - 6, z) != 0)
		for (int j = 0; j > -6; j--)
		{
			if (world.getBlock(x,y+j,z)==this)
			{
				world.getBlock(x,y+j,z).dropBlockAsItem(world,x,y+j,z,0,0);
				world.setBlockToAir(x, y + j, z);
			}else break;
		}

		return false;
	}

	@Override
	public void incrementGrowStage(World world, Random rand, int x, int y, int z){
		if(canBlockStay(world, x, y, z))
			canGrow(world, x, y, z);
		if (world.isAirBlock(x,y-1,z)){
			world.getBlock(x,y,z).dropBlockAsItem(world,x,y,z,0,0);
			world.setBlockToAir(x,y,z);
		}
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				for (int k = -1; k <= 1; k++)
				{
					if (!(x==0&&y==0&&z==0))world.markBlockForUpdate(x+i,y+j,z+k);
				}
			}

		}
		if (canGrow(world, x, y, z))
		{
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
			world.setBlock(x, y + 1, z, this, 2, 3);
		}

		if (world.getBlockMetadata(x, y, z) == 0 &&
		    world.isAirBlock(x, y + 1, z))
			world.setBlock(x, y + 1, z, this, 2, 0);

		if (world.getBlockMetadata(x, y, z) == 1 &&
		    world.isAirBlock(x, y + 1, z))
			world.setBlock(x, y + 1, z, this, 2, 0);

		for (int i = -2; i <= 2; i++)
		{
			for (int j = -1; j <= 3; j++)
			{
				for (int k = -2; k <= 2; k++)
				{
					if (plant.canReplace(world, x + i, y + j, z + k) &&
					    plant.canPlaceBlockOn(world.getBlock(x + i, y + j - 1, z + k)))
					{
						world.setBlock(x + i, y + j, z + k, plant);
					}
				}
			}
		}
		for (int i = 0; i < world.getLoadedEntityList().size(); i++)
		{
			if ((world.getLoadedEntityList().get(i)) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase)world.getLoadedEntityList().get(i);
				if (entity.getPosition(1.0f).distanceTo(Vec3.createVectorHelper(x, y, z)) < 20)
				{
					if (entity.getEquipmentInSlot(4)!= null){
						if(entity.getEquipmentInSlot(4).getItem()!= ModItems.gasMask)
							gas(rand,entity);
					}
					else gas(rand,entity);
				}
			}
		}

	}

	private void gas(Random rand, EntityLivingBase entity){
		if (rand.nextInt(10) == 0){
			PotionEffect effect = new PotionEffect(ModPotion.poisonGas.getId(), 100, 0, true);
			effect.setCurativeItems(new ArrayList<ItemStack>());
			entity.addPotionEffect(effect);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float f = 0.4f;
		float x1=0,x2=0,y1=0,y2=0,z1=0,z2=0;
		if (world.getBlockMetadata(x,y,z)==2){
			x1=x+0.0F;y1=y+0.0F;z1=z+0.0F;
			x2=x+1.0F;y2=y+1.0F;z2=z+1.0F;
		}else if (world.getBlockMetadata(x,y,z)==1){
			x1=x+0.0F+f;y1=y+0.0F;z1=z+0.0F+f;
			x2=x+1.0F-f;y2=y+1.0F;z2=z+1.0F-f;
		}else if (world.getBlockMetadata(x,y,z)==0){
			if (world.isAirBlock(x,y+1,z))
				return null;
			else {
				x1=x+0.0F+f;y1=y+0.0F;z1=z+0.0F+f;
				x2=x+1.0F-f;y2=y+1.0F;z2=z+1.0F-f;
			}
		}
		return AxisAlignedBB.getBoundingBox(x1,y1,z1,x2,y2,z2);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float f = 0.4f;
		float x1,x2,y1,y2,z1,z2;
		if (world.getBlockMetadata(x,y,z)==2){
			x1=x+0.0F;y1=y+0.0F;z1=z+0.0F;
			x2=x+1.0F;y2=y+1.0F;z2=z+1.0F;
		}else {
			x1=x+0.0F+f;y1=y+0.0F;z1=z+0.0F+f;
			x2=x+1.0F-f;y2=y+1.0F;z2=z+1.0F-f;
		}
		return AxisAlignedBB.getBoundingBox(x1,y1,z1,x2,y2,z2);
	}
}
