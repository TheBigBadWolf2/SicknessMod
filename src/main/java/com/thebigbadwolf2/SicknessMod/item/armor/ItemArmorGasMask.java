package com.thebigbadwolf2.SicknessMod.item.armor;

import com.thebigbadwolf2.SicknessMod.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemArmorGasMask extends ItemArmorWSMod
{
	public ItemArmorGasMask(int type)
	{
		super(ItemArmor.ArmorMaterial.GOLD,1,type);

	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return Reference.MOD_ID + ":textures/items/" + this.getName() + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
}
