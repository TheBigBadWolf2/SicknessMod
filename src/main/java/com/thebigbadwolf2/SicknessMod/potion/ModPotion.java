package com.thebigbadwolf2.SicknessMod.potion;

import com.thebigbadwolf2.SicknessMod.init.ModItems;
import com.thebigbadwolf2.SicknessMod.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

import java.util.ArrayList;

public class ModPotion extends Potion
{
	public static final ModPotion potionCure = (ModPotion)(new ModPotion(32, false, 0)).setIconIndex(2,2).setPotionName("potion.cure");

	public static final ModPotion[] medicine = {potionCure};

	public static final ModPotion potionSick = (ModPotion)(new ModPotion(33, true, 0)).setIconIndex(6,0).setPotionName("potion.sick");

	public static final ModPotion poisonGas = (ModPotion)(new ModPotion(34, true, 0)).setIconIndex(6,0).setPotionName("potion.Gas");

	public static final ModPotion[] flu = {potionSick,poisonGas};

	public ModPotion(int id, boolean isBadEffect, int liquidColor)
	{
		super(id, isBadEffect, liquidColor);

	}

/*	private static int ID;

	public static ArrayList<String> potionNames = new ArrayList<String>();
	public static ArrayList<Integer> potionIDs = new ArrayList<Integer>();

	private static int getNewID(String potion){
		if (ID<32)ID=32;
		if (potionNames!=null){
			potionNames.add(potion);
			potionIDs.add(ID);
		}
		return ID++;
	}*/

	public Potion setIconIndex(int par1, int par2)
	{
		super.setIconIndex(par1, par2);
		return this;
	}
	private static int[] tickGood = new  int[medicine.length];
	private static int[] tickBad = new  int[flu.length];
	public static void applyPotion(int Numb, boolean isGood, EntityLivingBase entity){
		{

			if (isGood)
			{
				switch (Numb)
				{
					case 0:
						if (tickGood[0]++ % 20 == 0)
						{
							if (entity.getHealth() < entity.getMaxHealth())
							{
								entity.heal(1);
							}
						}
						for (ModPotion potion : ModPotion.flu)
						{
							if (entity.isPotionActive(potion))
							{
								entity.removePotionEffect(potion.id);
							}
						}
						if (entity.getActivePotionEffect(potionCure).getDuration() == 0)
						{
							tickGood[0] = 0;
						}
						break;
				}
			}else{
				switch (Numb)
				{
					case 0:
						entity.getActivePotionEffect(potionSick).setPotionDurationMax(true);
						if (tickBad[0]++ % 40 == 0){
							if (entity instanceof EntityPlayer){
								EntityPlayer player = (EntityPlayer)entity;
								if (player.getFoodStats().getFoodLevel()>1)
								{
									ItemStack stack = new ItemStack(ModItems.throwUp,1,player.getFoodStats().getFoodLevel()-1);
									EntityItem item = new EntityItem(player.worldObj,player.posX,player.posY,player.posZ,stack);
									if (!player.worldObj.isRemote)player.worldObj.spawnEntityInWorld(item);
									player.getFoodStats().setFoodLevel(1);


								}else if (player.getFoodStats().getFoodLevel()<1) player.getFoodStats().setFoodLevel(1);
							}
							if (entity.worldObj.rand.nextInt(20)==0){
								entity.addPotionEffect(new PotionEffect(Potion.confusion.id,200,1,true));
							}
						}
						if (entity instanceof EntityPlayer)
						{
							EntityPlayer player = (EntityPlayer) entity;

						}
						if (entity.getActivePotionEffect(potionSick).getDuration() == 0)
						{
							tickBad[0] = 0;
						}
						break;
					case 1:
						if (tickBad[1]++ % 40 == 0)
						{
							if (Minecraft.getMinecraft().theWorld.rand.nextInt(20) == 0)
								entity.attackEntityFrom(DamageSource.generic,5);
						}

						if (entity.getActivePotionEffect(poisonGas).getDuration() == 0)
						{
							tickBad[1] = 0;
						}
						break;
				}
			}
		}
	}
}
