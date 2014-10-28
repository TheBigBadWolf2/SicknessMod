package com.thebigbadwolf2.SicknessMod.item;

import com.thebigbadwolf2.SicknessMod.block.BlockCompactedDust;
import com.thebigbadwolf2.SicknessMod.block.BlockWSMod;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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

		if (stack.getItem().equals(ModItems.redDust))
			modBlock =  ModBlocks.compactedRedDust;
		else if (stack.getItem().equals(ModItems.greenDust))
			modBlock =  ModBlocks.compactedGreenDust;
		else if (stack.getItem().equals(ModItems.blueDust))
			modBlock =  ModBlocks.compactedBlueDust;
		if (modBlock!=null)
		{
			switch (side)
			{
				case 0:
					world.setBlock(x, y - 1, z, modBlock);
					break;
				case 1:
					world.setBlock(x, y + 1, z, modBlock);
					break;
				case 2:
					world.setBlock(x, y, z - 1, modBlock);
					break;
				case 3:
					world.setBlock(x, y, z + 1, modBlock);
					break;
				case 4:
					world.setBlock(x - 1, y, z, modBlock);
					break;
				case 5:
					world.setBlock(x + 1, y, z, modBlock);
					break;
			}
			stack.stackSize--;
		}

		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
}
