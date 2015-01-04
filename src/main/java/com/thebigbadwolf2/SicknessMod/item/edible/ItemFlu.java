package com.thebigbadwolf2.SicknessMod.item.edible;

import com.thebigbadwolf2.SicknessMod.potion.ModPotion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFlu extends ItemSicknessPill
{
	public ItemFlu(){
		super();
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player){
		super.onFoodEaten(stack,world,player,ModPotion.potionSick.getId(),Integer.MAX_VALUE);
	}

	@Override
	public boolean hasEffect(ItemStack stack, int pass){
		return true;
	}
}
