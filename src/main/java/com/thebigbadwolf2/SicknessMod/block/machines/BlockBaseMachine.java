package com.thebigbadwolf2.SicknessMod.block.machines;

import com.thebigbadwolf2.SicknessMod.block.BlockWSMod;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public abstract class BlockBaseMachine extends BlockWSMod implements ITileEntityProvider
{
	private IIcon[] icons;

	public BlockBaseMachine(){
		super(Material.iron);
		icons = new IIcon[4];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_bottom");
		icons[1] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_front");
		icons[2] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_top");
		icons[3] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()))+"_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side==0)
			return icons[0];
		else if (side==1)
			return icons[2];
		else if(side > 1 && side < 6)
			if (meta==0)
				if (side==2)
					return icons[1];
				else return icons[3];
			else if (meta==1)
				if (side==3)
					return icons[1];
				else return icons[3];
			else if (meta==2)
				if (side==4)
					return icons[1];
				else return icons[3];
			else if (meta==3)
				if (side==5)
					return icons[1];
				else return icons[3];
			else return icons[3];

		else return icons[3];
	}
}
