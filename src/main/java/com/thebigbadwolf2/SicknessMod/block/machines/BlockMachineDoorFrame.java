package com.thebigbadwolf2.SicknessMod.block.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMachineDoorFrame extends BlockBaseMachine
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockMachineDoorFrame(){
		super();
		icons = new IIcon[4];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return icons[0];
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return null;
	}
}
