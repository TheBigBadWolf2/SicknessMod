package com.thebigbadwolf2.SicknessMod.handler;

import com.thebigbadwolf2.SicknessMod.SicknessMod;
import com.thebigbadwolf2.SicknessMod.item.dust.ItemDust;
import com.thebigbadwolf2.SicknessMod.potion.ModPotion;
import com.thebigbadwolf2.SicknessMod.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class EventHandlerWSMod
{
	@SubscribeEvent
	public void onPlayerPickUp(EntityItemPickupEvent event)
	{
		if(event.item != null && event.item.getEntityItem() != null && event.item.getEntityItem().getItem() instanceof ItemDust) {
			if (!ConfigHandler.inDev)
				event.item.worldObj.createExplosion(event.item,
				                                    event.item.posX,
				                                    event.item.posY,
				                                    event.item.posZ,
				                                    event.item.getEntityItem().stackSize,
				                                    false);
		}
		LogHelper.info("Item Picked up");
	}
	int tick = 0;
	@SubscribeEvent
	public void onEntityUpdate(LivingEvent.LivingUpdateEvent event)
	{
		for (int i = 0; i < ModPotion.medicine.length; i++)
		{
			if (event.entityLiving.isPotionActive(ModPotion.medicine[i])){ModPotion.applyPotion(i,true,event.entityLiving);}
		}
		for (int i = 0; i < ModPotion.flu.length; i++)
		{
			if (event.entityLiving.isPotionActive(ModPotion.flu[i])){ModPotion.applyPotion(i,false,event.entityLiving);}
		}
		tick++;
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{

		if(!event.world.isRemote && !event.entity.isDead){
			if(event.entity instanceof EntityItem){
				ItemStack stack = ((EntityItem)event.entity).getEntityItem();
				if (!(event.entity instanceof CustomEntityItem)){
					if (stack.getItem() instanceof ItemDust){
						if (!ConfigHandler.inDev){
							CustomEntityItem entityItem =
									new CustomEntityItem(event.world,
									                     event.entity.posX,
									                     event.entity.posY,
									                     event.entity.posZ,
									                     stack.copy());
							entityItem.delayBeforeCanPickup = ((EntityItem)event.entity).delayBeforeCanPickup;
							entityItem.copyDataFrom(event.entity,true);
							event.setCanceled(true);
							stack.stackSize = 0;
							event.world.spawnEntityInWorld(entityItem);
						}
					}
				}
			}
		}
	}
}

class CustomEntityItem extends EntityItem
{
	public CustomEntityItem(World world, double x, double y, double z, ItemStack stack)
	{
		super(world,x,y,z,stack);
		LogHelper.info("Recreated Item");
	}

	@Override
	public boolean attackEntityFrom(DamageSource dmgSource, float HP)
	{
//		if (dmgSource.isExplosion()){}
		return false;
	}
}
