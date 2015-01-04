package com.thebigbadwolf2.SicknessMod.block.crops;

import com.thebigbadwolf2.SicknessMod.block.crops.weed.BlockWeed;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import com.thebigbadwolf2.SicknessMod.potion.ModPotion;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Random;

public class BlockCropWSMod extends BlockBush implements IGrowable
{
	protected int maxGrowthStage = 7;

	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;

	public BlockCropWSMod()
	{
		// Basic block setup
		this.setTickRandomly(true);
		float f = 0.2F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
		this.setBlockName(Name());
		this.setCreativeTab(null);
		this.setHardness(0.0F);
		this.setStepSound(soundTypeGrass);
		this.disableStats();
		this.icons = new IIcon[maxGrowthStage+1];
	}

	protected String Name()
	{
		String name = this.getClass().getSimpleName();
		name = name.replaceFirst("Block","");
		char[] nameChar = name.toCharArray();
		nameChar[0] = name.toLowerCase().charAt(0);
		name = String.valueOf(nameChar);
		return name;
	}

	public String getName(){
		return Name();
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_stage_0");
		icons[1] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_stage_1");
		icons[2] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_stage_2");
		icons[3] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_stage_3");
		icons[4] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_stage_4");
		icons[5] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_stage_5");
		icons[6] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_stage_6");
		icons[7] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_stage_7");
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}


	/**
	 * is the block grass, dirt or farmland
	 */
	@Override
	public boolean canPlaceBlockOn(Block block)
	{
		return ModBlocks.isOrganic(block);
	}

	public boolean canReplace(World world, int x, int y, int z){
		if (world.isAirBlock(x,y,z)) return true;
		else if (world.isAirBlock(x,y,z)||
		         (world.getBlock(x,y,z) instanceof BlockBush||
		          world.getBlock(x,y,z) instanceof BlockCrops)&&
		         !(world.getBlock(x,y,z) instanceof BlockCropWSMod||
		           world.getBlock(x,y,z) instanceof BlockWeed)){
			world.getBlock(x,y,z).dropBlockAsItem(world,x,y,z,0,0);
			return true;
		} else return false;
	}

	public void incrementGrowStage(World world, Random rand, int x, int y, int z)
	{
		int growStage = world.getBlockMetadata(x, y, z) +
		                //MathHelper.getRandomIntegerInRange(parRand, 1, 3);
						1;

		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				for (int k = -1; k <= 1; k++)
				{
					if (canReplace(world,x+i,y+j,z+k)&&
					    this.canPlaceBlockOn(world.getBlock(x+i,y+j-1,z+k))){
						world.setBlock(x+i,y+j,z+k,this);
					}
				}

			}

		}

		if (growStage > maxGrowthStage)
		{
			growStage = maxGrowthStage;
		}

		if (growStage==maxGrowthStage){
			world.setBlock(x,y-1,z, ModBlocks.encrustedMycelium);
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

		world.setBlockMetadataWithNotify(x, y, z, growStage, 2);
	}

	private void gas(Random rand, EntityLivingBase entity){
		if (rand.nextInt(100) == 0){
			PotionEffect effect = new PotionEffect(ModPotion.poisonGas.getId(), 100, 0, true);
			effect.setCurativeItems(new ArrayList<ItemStack>());
			entity.addPotionEffect(effect);
		}
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random parRand, int parFortune)
	{
		return Item.getItemFromBlock(this);
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return 1; // Cross like flowers
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int growthStage)
	{
		return icons[growthStage];
	}

    /*
     * Need to implement the IGrowable interface methods
     */

	/*
	 * (non-Javadoc)
	 * @see net.minecraft.block.IGrowable#func_149851_a(net.minecraft.world.World,
	 * int, int, int, boolean)
	 */
	@Override
	// checks if finished growing (a grow stage of 7 is final stage)
	public boolean func_149851_a(World parWorld, int parX, int parY, int parZ,
	                             boolean p_149851_5_)
	{
		return true;// parWorld.getBlockMetadata(parX, parY, parZ) != 7;
	}

	/*
	 * (non-Javadoc)
	 * @see net.minecraft.block.IGrowable#func_149852_a(net.minecraft.world.World,
	 * java.util.Random, int, int, int)
	 */
	@Override
	public boolean func_149852_a(World p_149852_1_, Random parRand, int p_149852_3_,
	                             int p_149852_4_, int p_149852_5_)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see net.minecraft.block.IGrowable#func_149853_b(net.minecraft.world.World,
	 * java.util.Random, int, int, int)
	 */
	@Override
	public void func_149853_b(World parWorld, Random parRand, int parX, int parY,
	                          int parZ)
	{
		incrementGrowStage(parWorld, parRand, parX, parY, parZ);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		super.updateTick(world, x, y, z, rand);
		int chance = 6;
		if (rand.nextInt(chance) == 0)incrementGrowStage(world, rand, x, y, z);
	}


}
