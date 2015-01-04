package com.thebigbadwolf2.SicknessMod.item.edible;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNutrientPaste extends ItemEdible
{
	public ItemNutrientPaste(){
		super(5,0.5f,false);
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player){
		super.onEaten(stack, world, player);
		return new ItemStack(Items.bowl);
	}
}
