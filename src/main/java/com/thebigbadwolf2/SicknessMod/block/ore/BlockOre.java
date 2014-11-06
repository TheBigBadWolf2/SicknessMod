package com.thebigbadwolf2.SicknessMod.block.ore;

import com.thebigbadwolf2.SicknessMod.block.BlockWSMod;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import com.thebigbadwolf2.SicknessMod.item.ItemWSMod;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockOre extends BlockWSMod
{
	public BlockOre(){
		super();
		this.setHardness(10);
	}

	public void harvestBlock(World world, int x, int y, int z, ItemWSMod item)
	{
		ItemStack stack = new ItemStack(item,1);
		EntityItem itemEnt = new EntityItem(world,x,y,z,stack);
		if (!world.isRemote)world.spawnEntityInWorld(itemEnt);
	}
}
