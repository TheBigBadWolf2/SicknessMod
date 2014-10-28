package com.thebigbadwolf2.SicknessMod.block;

import com.thebigbadwolf2.SicknessMod.creativetab.CreativeTabWSMod;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockWSMod extends Block
{
	public BlockWSMod(Material material)
	{
		super(material);
		this.setBlockName(Name());
		this.setCreativeTab(CreativeTabWSMod.WSMod_TAB_BLOCKS);
	}

	public BlockWSMod()
	{
		this(Material.rock);
	}

	private String Name()
	{
		String name = this.getClass().getSimpleName();
		name = name.replaceFirst("Block","");
		char[] nameChar = name.toCharArray();
		nameChar[0] = name.toLowerCase().charAt(0);
		name = String.valueOf(nameChar);
		return name;
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
		blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
