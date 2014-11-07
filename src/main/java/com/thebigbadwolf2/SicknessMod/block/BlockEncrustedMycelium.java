package com.thebigbadwolf2.SicknessMod.block;

import com.thebigbadwolf2.SicknessMod.block.BlockWSMod;
import com.thebigbadwolf2.SicknessMod.block.crops.BlockCropWSMod;
import com.thebigbadwolf2.SicknessMod.creativetab.CreativeTabWSMod;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockEncrustedMycelium extends BlockWSMod
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	public BlockEncrustedMycelium()
	{
		super(Material.ground);
		this.setStepSound(Block.soundTypeGrass);
		this.setTickRandomly(true);
		this.setHardness(10.0F);
		icons = new IIcon[3];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_top");
		icons[1] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_side");
		icons[2] = ModBlocks.decomp.getBlockTextureFromSide(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side==0)return icons[2];
		else if (side==1)return icons[0];
		else return icons[1];
	}

	private boolean canReplace(Block block)
	{
		return ModBlocks.isOrganic(block)&&
		       !(block==ModBlocks.decomp||
		         block==ModBlocks.encrustedMycelium);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (!(world.isAirBlock(x,y+1,z)||world.getBlock(x,y+1,z) instanceof BlockCropWSMod))
			world.setBlock(x,y,z,ModBlocks.decomp);
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 0; j++)
				for (int k = -1; k <= 1; k++)
					if (canReplace(world.getBlock(x+i,y+j,z+k)))
						if (rand.nextInt(20)==0)
							world.setBlock(x+i,y+j,z+k, ModBlocks.decomp);
		for (int i = -1; i <= 1; i++)
			for (int j = -2; j <= 1; j++)
				for (int k = -1; k <= 1; k++)
					if (world.getBlock(x+i,y+j,z+k)==ModBlocks.decomp)
						if (world.isAirBlock(x+i,y+j+1,z+k)||world.getBlock(x+i,y+j+1,z+k) instanceof BlockCropWSMod)
							if (rand.nextInt(10)==0)
								world.setBlock(x+i,y+j,z+k,this);
	}
}
