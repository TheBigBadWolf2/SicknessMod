package com.thebigbadwolf2.SicknessMod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArrowShooter extends ItemWSMod {

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		double x = player.posX;
		double y = player.posY + player.eyeHeight;
		double z = player.posZ;

		for (float i = -4.5f; i <= 4.5f; i++) {
			for (float k = -4.5f; k <= 4.5f; k++) {
				double m = 2;
				double yaw = Math.toRadians((player.rotationYaw+(i*m)));
				double pitch = Math.toRadians((player.rotationPitch+(k*m)));

				double vxa = (-Math.sin(yaw));
				double vza = (Math.cos(yaw));

				double vx = (-Math.sin(yaw))*(Math.cos(pitch));
				double vy = (-Math.sin(pitch));
				double vz = (Math.cos(yaw))*((Math.cos(pitch)));

				double d = 5;

				EntityArrow arrow = new EntityArrow(world,x+vxa,y,z+vza);
				arrow.setVelocity(vx*d,vy*d,vz*d);
				//arrow.setKnockbackStrength(10);
				arrow.setFire(5000);
				arrow.setDamage(10);
				if (!world.isRemote)world.spawnEntityInWorld(arrow);
			}
		}
		return super.onItemRightClick(stack, world, player);
	}

	private void arrow(){

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		itemIcon = Items.arrow.getIcon(new ItemStack(Items.arrow),0);
	}
}
