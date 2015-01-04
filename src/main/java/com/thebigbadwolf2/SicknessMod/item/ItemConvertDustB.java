package com.thebigbadwolf2.SicknessMod.item;

import com.thebigbadwolf2.SicknessMod.block.crops.BlockCropWSMod;
import com.thebigbadwolf2.SicknessMod.block.crops.weed.BlockWeed;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.potion.ModPotion;
import com.thebigbadwolf2.SicknessMod.reference.MethodRef;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ItemConvertDustB extends ItemWSMod
{

	public ItemConvertDustB(){
		super(true,new int[]{1,3});
		subNames.add("generic");
		subNames.add("empowered");
		subNames.add("genericAOE");
		subNames.add("empoweredAOE");
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity){
		entity.addPotionEffect(new PotionEffect(ModPotion.poisonGas.getId(), 500));
		entity.moveEntity(0,1,0);
		return super.itemInteractionForEntity(stack, player, entity);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		int dmg = stack.getItemDamage();
		if (dmg==2||dmg==3) {
			int x = Integer.valueOf(String.format("%.0f", entity.posX));
			int y = Integer.valueOf(String.format("%.0f", entity.posY));
			int z = Integer.valueOf(String.format("%.0f", entity.posZ));

			for (int i = -25; i <= 25; i++)
				for (int j = -15; j <= 5; j++)
					for (int k = -25; k <= 25; k++)
						MethodRef.Bad.Convert.convert(world, x + i, y + j, z + k,dmg==3);
		}
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int xxx, float hitX, float hitY, float hitZ){
		int dmg = stack.getItemDamage();
		if (dmg==0||dmg==1)
			for (int i = -5; i <= 5 ; i++)
				for (int j = -5; j <= 5; j++)
					for (int k = -5; k <= 5; k++)
						MethodRef.Bad.Convert.convert(world, x+i, y+j, z+k, dmg==1);

		return false;
	}


}
