package com.thebigbadwolf2.SicknessMod.item;

import com.thebigbadwolf2.SicknessMod.reference.MethodRef;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMagicBoneMeal extends ItemWSMod{

	private final int hRange = 25;
	private final int vRangeUp = 15;
	private final int vRangeDown = 5;
	private final int reduction = 2;

	public ItemMagicBoneMeal(){
		super(true,new int[]{1});
		subNames.add("dust");
		subNames.add("AOE");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (stack.getItemDamage()==1) {
			int x = Integer.valueOf(String.format("%.0f", entity.posX));
			int y = Integer.valueOf(String.format("%.0f", entity.posY));
			int z = Integer.valueOf(String.format("%.0f", entity.posZ));

			int a = Integer.valueOf(String.format("%.0f", (Math.pow(((2 * hRange) + 1), 2) * (vRangeUp + vRangeDown + 1))));

			if (!world.isRemote) {
				for (int i = -hRange; i <= hRange; i++) {
					for (int j = -vRangeDown; j <= vRangeUp; j++) {
						for (int k = -hRange; k <= hRange; k++) {
							Block block = world.getBlock(x + i, y + j, z + k);
							MethodRef.Good.Update.update(world, block, x + i, y + j, z + k, world.rand);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (stack.getItemDamage()==0) {
			if (!world.isRemote) {
				for (int i = -hRange / reduction; i <= hRange / reduction; i++) {
					for (int j = -vRangeDown / reduction; j <= vRangeUp / reduction; j++) {
						for (int k = -hRange / reduction; k <= hRange / reduction; k++) {
							Block block = world.getBlock(x + i, y + j, z + k);
							MethodRef.Good.Update.update(world, block, x + i, y + j, z + k, world.rand);
						}
					}
				}

			}
		}
		return false;
	}
}
