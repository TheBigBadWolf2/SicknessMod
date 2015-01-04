package com.thebigbadwolf2.SicknessMod.item.armor;

import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemArmorWSMod extends ItemArmor
{
	public static ArmorMaterial gasSuit = EnumHelper.addArmorMaterial("gasSuit",100,new int[]{3, 8, 6, 3},30);
	public static ArmorMaterial badSuit = EnumHelper.addArmorMaterial("badSuit",100,new int[]{3, 8, 6, 3},30);
	public static ArmorMaterial goodSuit = EnumHelper.addArmorMaterial("goodSuit",100,new int[]{3, 8, 6, 3},30);


	public ItemArmorWSMod(ArmorMaterial material, int render, int type)
	{
		super(material, render, type);
		this.setUnlocalizedName(Name()+"_"+(this.armorType == 0 ? "helmet" : (this.armorType == 1 ? "chest" : (this.armorType == 2 ? "legs" : "boots"))));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return Reference.MOD_ID + ":textures/armor/" + this.getName() + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}

	private String Name(){
		String name = this.getClass().getSimpleName();
		name = name.replaceFirst("ItemArmor","");
		char[] nameChar = name.toCharArray();
		nameChar[0] = name.toLowerCase().charAt(0);
		name = String.valueOf(nameChar);
		return name;
	}

	public String getName(){
		return Name();
	}

	@Override
	public String getUnlocalizedName(){
		return String.format("item.%s%s",
		                     Reference.MOD_ID.toLowerCase() + ":",
		                     getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		return String.format("item.%s%s",
		                     Reference.MOD_ID.toLowerCase() + ":",
		                     getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName()
				                                     .substring(this.getUnlocalizedName()
						                                                .indexOf(".") + 1));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
