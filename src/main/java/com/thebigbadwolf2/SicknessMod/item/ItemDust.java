package com.thebigbadwolf2.SicknessMod.item;

import com.thebigbadwolf2.SicknessMod.block.BlockCompactedDust;
import com.thebigbadwolf2.SicknessMod.block.BlockWSMod;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class ItemDust extends ItemWSMod
{
	public ItemDust()
	{
		super();
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		BlockWSMod modBlock = null;

		int x2 = x;
		int y2 = y;
		int z2 = z;

		if (stack.getItem().equals(ModItems.redDust))
			modBlock =  ModBlocks.compactedRedDust;
		else if (stack.getItem().equals(ModItems.greenDust))
			modBlock =  ModBlocks.compactedGreenDust;
		else if (stack.getItem().equals(ModItems.blueDust))
			modBlock =  ModBlocks.compactedBlueDust;

		switch (side)
		{
			case 0:
				y2--;
				break;
			case 1:
				y2++;
				break;
			case 2:
				z2--;
				break;
			case 3:
				z2++;
				break;
			case 4:
				x2--;
				break;
			case 5:
				x2++;
				break;
		}

		if (modBlock!=null)
		{
			world.setBlock(x2, y2, z2, modBlock);
			if (!player.capabilities.isCreativeMode)stack.stackSize-=4;
		}



		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
}
