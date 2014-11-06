package com.thebigbadwolf2.SicknessMod.item.edible;

import com.thebigbadwolf2.SicknessMod.creativetab.CreativeTabWSMod;
import com.thebigbadwolf2.SicknessMod.potion.ModPotion;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import com.thebigbadwolf2.SicknessMod.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ItemMedicine extends ItemEdible
{

	public ItemMedicine()
	{
		super(0, 0, false);
		this.setCreativeTab(CreativeTabWSMod.WSMod_TAB_MED);
		this.setAlwaysEdible();
		this.maxStackSize=16;
	}

	/*protected int getID(String potion){
		int ID = 0;
		for (int i = 0; i < ModPotion.potionNames.size(); i++)
		{
			if (ModPotion.potionNames.get(i).equals(potion)){
				ID = ModPotion.potionIDs.get(i);
			}
		}
		//LogHelper.info(potion+ " : " + ID);
		return ID;
	}*/

	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player, int potionID, int potionDuration)
	{
		PotionEffect effect = new PotionEffect(potionID,potionDuration,0,true);
		effect.setCurativeItems(new ArrayList<ItemStack>());
		player.addPotionEffect(effect);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID+":medicine");
	}
}
