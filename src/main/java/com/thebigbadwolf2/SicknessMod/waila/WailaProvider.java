package com.thebigbadwolf2.SicknessMod.waila;

import com.thebigbadwolf2.SicknessMod.block.machines.BlockMachineDoorControl;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.tileentity.TileEntityDoorControl;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.List;

public class WailaProvider implements IWailaDataProvider
{
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		currenttip.add("MetaData : "+accessor.getMetadata());
		currenttip.add("X min|max : "+((TileEntityDoorControl)accessor.getTileEntity()).getXmin()+"|"+((TileEntityDoorControl)accessor.getTileEntity()).getXmax());
		currenttip.add("Y min|max : "+((TileEntityDoorControl)accessor.getTileEntity()).getYmin()+"|"+((TileEntityDoorControl)accessor.getTileEntity()).getYmax());
		currenttip.add("Z min|max : "+((TileEntityDoorControl)accessor.getTileEntity()).getZmin()+"|"+((TileEntityDoorControl)accessor.getTileEntity()).getZmax());
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	public static void callbackRegister(IWailaRegistrar registrar){
		registrar.addConfig("SicknessMod", "sicknessmod.showbody", "Show Body");
		registrar.registerHeadProvider(new WailaProvider(), BlockMachineDoorControl.class);
		registrar.registerBodyProvider(new WailaProvider(), BlockMachineDoorControl.class);
	}
}
