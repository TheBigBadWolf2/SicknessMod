package com.thebigbadwolf2.SicknessMod.block;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;

public class BlockMimic extends BlockWSMod
{
	private IIcon[] icons;

	public BlockMimic(){
		super();
		this.setBlockUnbreakable();
		this.setResistance(Float.MAX_VALUE);
		icons = new IIcon[4];
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		for (int i = 0; i < 4; i++)
		{
			list.add(new ItemStack(item,1,i));
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = ModBlocks.compactedRedDust.getBlockTextureFromSide(0);
		icons[1] = ModBlocks.compactedGreenDust.getBlockTextureFromSide(0);
		icons[2] = ModBlocks.compactedBlueDust.getBlockTextureFromSide(0);
		icons[3] = Blocks.dirt.getBlockTextureFromSide(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return icons[meta];
	}
}
