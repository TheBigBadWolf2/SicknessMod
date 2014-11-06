package com.thebigbadwolf2.SicknessMod.item.edible;

import com.thebigbadwolf2.SicknessMod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

public class ItemThrowUp extends ItemEdible
{
	public ItemThrowUp()
	{
		super(0, 0, true);
		this.setHasSubtypes(true);
		this.setAlwaysEdible();
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list)
	{
		for (int i = 1; i <= 20; i++)
		{
			list.add(new ItemStack(this,1,i));
		}
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()+stack.getItem().getDamage(stack));
		player.addPotionEffect(new PotionEffect(Potion.confusion.id,200,1,true));
	}
}
