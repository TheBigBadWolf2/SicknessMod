package com.thebigbadwolf2.SicknessMod.tileentity;

public class TileEntityDoorControl extends TileEntityWSMod
{
	private boolean active = false;

	public boolean isActive(){
		return active;
	}



	public void toggleActive(){
		active=!active;
	}
}
