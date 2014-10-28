package com.thebigbadwolf2.SicknessMod.item;

import com.thebigbadwolf2.SicknessMod.creativetab.CreativeTabWSMod;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemWSMod extends Item
{
	public ItemWSMod()
	{
		super();
		this.setUnlocalizedName(Name());
		this.setCreativeTab(CreativeTabWSMod.WSMod_TAB_ITEMS);
	}

	private String Name()
	{
		String name = this.getClass().getSimpleName();
		name = name.replaceFirst("Item","");
		char[] nameChar = name.toCharArray();
		nameChar[0] = name.toLowerCase().charAt(0);
		name = String.valueOf(nameChar);
		return name;
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s%s",
		                     Reference.MOD_ID.toLowerCase() + ":",
		                     getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return String.format("item.%s%s",
		                     Reference.MOD_ID.toLowerCase() + ":",
		                     getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName()
				                                     .substring(this.getUnlocalizedName()
						                                                .indexOf(".") + 1));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}


}
