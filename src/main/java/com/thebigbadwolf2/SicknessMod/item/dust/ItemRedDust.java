package com.thebigbadwolf2.SicknessMod.item.dust;

import com.thebigbadwolf2.SicknessMod.block.compactedDust.BlockCompactedDust;
import com.thebigbadwolf2.SicknessMod.handler.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;

public class ItemRedDust extends ItemDust
{
	public ItemRedDust()
	{
		super();
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entity)
	{
		double x = (int)entity.posX;
		double y = (int)entity.posY;
		double z = (int)entity.posZ;
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				if (entity.worldObj.getBlock((int)x+i,(int)y-1,(int)z+j) instanceof BlockCompactedDust && !entity.isBurning() && !entity.isWet())
				{
					Entity entityLightningBolt = new EntityLightningBolt(entity.worldObj, x+i, y, z+j);
					if (!ConfigHandler.inDev)
						entity.worldObj.spawnEntityInWorld(entityLightningBolt);
				}
			}

		}


		return false;
	}
}
