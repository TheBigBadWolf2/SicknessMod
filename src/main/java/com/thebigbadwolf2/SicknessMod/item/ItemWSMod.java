package com.thebigbadwolf2.SicknessMod.item;

import com.thebigbadwolf2.SicknessMod.creativetab.CreativeTabWSMod;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemWSMod extends Item
{
	boolean hasSubItems;
	protected ArrayList<String> subNames;

	private int[] glows;

	public ItemWSMod(){
		this(false);
	}

	public ItemWSMod(boolean hasSubItems){
		this(hasSubItems,null);
	}

	public ItemWSMod(boolean hasSubItems, int[] glows){
		super();
		this.hasSubItems = hasSubItems;
		this.glows = glows;
		if (this.hasSubItems)
			subNames = new ArrayList<String>();
		this.setHasSubtypes(hasSubItems);
		this.setUnlocalizedName(Name());
		this.setCreativeTab(CreativeTabWSMod.WSMod_TAB_ITEMS);
	}

	private String Name(){
		String name = this.getClass().getSimpleName();
		name = name.replaceFirst("Item","");
		char[] nameChar = name.toCharArray();
		nameChar[0] = name.toLowerCase().charAt(0);
		name = String.valueOf(nameChar);
		return name;
	}

	public String getName(){
		return Name();
	}

	@Override
	public String getUnlocalizedName(){
		return String.format("item.%s%s",
		                     Reference.MOD_ID.toLowerCase() + ":",
		                     getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list){
		if (subNames!=null
		    &&subNames.size()!=0)
			for (int i = 0; i < subNames.size(); i++)
				list.add(new ItemStack(this,1,i));
		else list.add(new ItemStack(this,1,0));
	}

	@Override
	public boolean hasEffect(ItemStack stack, int pass){
		int meta = stack.getItemDamage();
		if (glows==null)
			return super.hasEffect(stack, pass);
		else for (int i = 0; i < glows.length; i++)
			if (meta==glows[i])
				return true;
		return false;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		if (hasSubItems)
			return String.format("item.%s%s",
			                     Reference.MOD_ID.toLowerCase() + ":",
			                     getUnwrappedUnlocalizedName(super.getUnlocalizedName()))
			       +"."+subNames.get(itemStack.getItemDamage());
		else
			return String.format("item.%s%s",
			                     Reference.MOD_ID.toLowerCase() + ":",
			                     getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName()
				                                     .substring(this.getUnlocalizedName()
						                                                .indexOf(".") + 1));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
