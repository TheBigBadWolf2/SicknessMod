package com.thebigbadwolf2.SicknessMod.item.armor;

import com.thebigbadwolf2.SicknessMod.reference.MethodRef;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArmorGoodSuit extends ItemArmorWSMod
{
	public ItemArmorGoodSuit(int type)
	{
		super(ItemArmorWSMod.gasSuit,1,type);

	}

	private final int hRange = 25;
	private final int vRangeUp = 15;
	private final int vRangeDown = 5;

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		int x = Integer.valueOf(String.format("%.0f", player.posX));
		int y = Integer.valueOf(String.format("%.0f", player.posY));
		int z = Integer.valueOf(String.format("%.0f", player.posZ));

		if (this.armorType == 1){
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
		}else if (this.armorType == 3){
			for (int i = -25; i <= 25; i++)
				for (int j = -15; j <= 5; j++)
					for (int k = -25; k <= 25; k++)
						MethodRef.Good.Convert.convert(world, x + i, y + j, z + k);
		}

	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return Reference.MOD_ID + ":textures/armor/" + "gasMask" + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":gasMask_"+(this.armorType == 0 ? "helmet" : (this.armorType == 1 ? "chest" : (this.armorType == 2 ? "legs" : "boots"))));
	}
}
