package com.thebigbadwolf2.SicknessMod.item;

import com.thebigbadwolf2.SicknessMod.potion.ModPotion;
import com.thebigbadwolf2.SicknessMod.reference.MethodRef;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemConvertDustA extends ItemWSMod
{

	public ItemConvertDustA(){
		super(true,new int[]{1});
		subNames.add("dust");
		subNames.add("AOE");
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity){
		entity.addPotionEffect(new PotionEffect(ModPotion.potionCure.getId(), 500));
		entity.moveEntity(0,1,0);
		return super.itemInteractionForEntity(stack, player, entity);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (stack.getItemDamage()==1) {
			int x = Integer.valueOf(String.format("%.0f", entity.posX));
			int y = Integer.valueOf(String.format("%.0f", entity.posY));
			int z = Integer.valueOf(String.format("%.0f", entity.posZ));

			for (int i = -25; i <= 25; i++)
				for (int j = -15; j <= 5; j++)
					for (int k = -25; k <= 25; k++)
						MethodRef.Good.Convert.convert(world, x + i, y + j, z + k);
		}
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ){
		if (stack.getItemDamage()==0)
			for (int i = -5; i <= 5; i++)
				for (int j = -5; j <= 5; j++)
					for (int k = -5; k <=5 ; k++)
						MethodRef.Good.Convert.convert(world, x + i, y + j, z + k);
		return true;
	}
}
