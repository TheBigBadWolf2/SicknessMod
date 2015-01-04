package com.thebigbadwolf2.SicknessMod.item.edible;

import com.thebigbadwolf2.SicknessMod.init.ModItems;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

import java.util.ArrayList;
import java.util.List;

public class ItemThrowUp extends ItemEdible //implements IItemRenderer
{
	ArrayList<IIcon> numbers = new ArrayList<IIcon>();
	ArrayList<String> numb = new ArrayList<String>();

	public ItemThrowUp()
	{
		super(0, 0, true);
		this.setHasSubtypes(true);
		this.setAlwaysEdible();

		String prefix = Reference.MOD_ID+":"+"numbers/";
		for (int i = 0; i < 10; i++) {
			if (i!=9)numb.add(prefix+(i+1));
			else numb.add(prefix+(0));
		}
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list)
	{
		for (int i = 1; i <= 20; i++)
		{
			list.add(new ItemStack(this,1,i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		super.registerIcons(iconRegister);
		for (int i = 0; i < numb.size(); i++) {
			if (numb.get(i)!=null)
				numbers.add(iconRegister.registerIcon(numb.get(i)));
		}

	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player){
		player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()+stack.getItem().getDamage(stack));
		player.addPotionEffect(new PotionEffect(Potion.confusion.id,200,1,true));
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int dmg, int pass){
		dmg+=(numb.size()-1);
		if (pass == 0) return this.itemIcon;
		else if (pass == 1)
			if (this.numbers.size() > dmg && dmg > 0) return this.numbers.get(dmg);
			else return super.getIconFromDamageForRenderPass(dmg, pass);
		else return super.getIconFromDamageForRenderPass(dmg, pass);
	}

	/*@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

	}*/
}
