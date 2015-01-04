package com.thebigbadwolf2.SicknessMod.item.edible;

import com.thebigbadwolf2.SicknessMod.SicknessMod;
import com.thebigbadwolf2.SicknessMod.potion.ModPotion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ItemRegenMed extends ItemMedicine
{
	public ItemRegenMed(){
		super();
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player){
		super.onFoodEaten(stack,world,player,ModPotion.potionCure.getId(),200);
	}

	@Override
	public boolean hasEffect(ItemStack stack, int pass){
		return true;
	}
}
