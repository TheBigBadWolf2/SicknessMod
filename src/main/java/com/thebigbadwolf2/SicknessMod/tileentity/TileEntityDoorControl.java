package com.thebigbadwolf2.SicknessMod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;

public class TileEntityDoorControl extends TileEntityWSMod
{
	private boolean active = false;
	private boolean complete = false;
	private boolean lastRS;

	private int Xmax,Xmin,Ymax,Ymin,Zmax,Zmin;

	public boolean isActive(){
		return this.active;
	}
	public void toggleActive(){
		this.active=!this.active;
	}

	public boolean isComplete(){return this.complete;}
	public void setComplete(boolean val){this.complete = val;}

	public boolean getLastRS(){return this.lastRS;}
	public void setLastRS(boolean thisRS){this.lastRS=thisRS;}

	public void setXmax(int xmax)
	{
		Xmax = xmax;
	}

	public void setXmin(int xmin)
	{
		Xmin = xmin;
	}

	public void setYmax(int ymax)
	{
		Ymax = ymax;
	}

	public void setYmin(int ymin)
	{
		Ymin = ymin;
	}

	public void setZmax(int zmax)
	{
		Zmax = zmax;
	}

	public void setZmin(int zmin)
	{
		Zmin = zmin;
	}

	public int getXmax()
	{
		return Xmax;
	}

	public int getXmin()
	{
		return Xmin;
	}

	public int getYmax()
	{
		return Ymax;
	}

	public int getYmin()
	{
		return Ymin;
	}

	public int getZmax()
	{
		return Zmax;
	}

	public int getZmin()
	{
		return Zmin;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeToNBT(nbtTagCompound);
		int[] min = {Xmin,Ymin,Zmin};
		int[] max = {Xmax,Ymax,Zmax};
		nbtTagCompound.setBoolean("active",this.active);
		nbtTagCompound.setBoolean("lastRS",this.lastRS);
		nbtTagCompound.setIntArray("min",min);
		nbtTagCompound.setIntArray("max",max);

	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readFromNBT(nbtTagCompound);
		this.active = nbtTagCompound.getBoolean("active");
		this.lastRS = nbtTagCompound.getBoolean("lastRS");
		int[] min = nbtTagCompound.getIntArray("min");
		int[] max = nbtTagCompound.getIntArray("max");

		Xmin = min[0];Ymin = min[1];Zmin = min[2];
		Xmax = max[0];Ymax = max[1];Zmax = max[2];
	}
}
