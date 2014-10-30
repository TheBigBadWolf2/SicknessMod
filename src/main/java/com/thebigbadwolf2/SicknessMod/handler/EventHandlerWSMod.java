package com.thebigbadwolf2.SicknessMod.handler;

import com.thebigbadwolf2.SicknessMod.item.ItemDust;
import com.thebigbadwolf2.SicknessMod.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class EventHandlerWSMod
{
	@SubscribeEvent
	public void onPlayerPickUp(EntityItemPickupEvent event)
	{
		if(event.item != null && event.item.getEntityItem() != null && event.item.getEntityItem().getItem() instanceof ItemDust) {
			event.item.worldObj.createExplosion(event.item, event.item.posX, event.item.posY, event.item.posZ, event.item.getEntityItem().stackSize, false);
		}
		LogHelper.info("Item Picked up");
	}



	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if(!event.world.isRemote && !event.entity.isDead){
			if(event.entity instanceof EntityItem){
				ItemStack stack = ((EntityItem)event.entity).getEntityItem();
				if (!(event.entity instanceof CustomEntityItem)){
					if (stack.getItem() instanceof ItemDust){
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
//		if (dmgSource.isExplosion()){
//
//		}
		return false;
	}
}
