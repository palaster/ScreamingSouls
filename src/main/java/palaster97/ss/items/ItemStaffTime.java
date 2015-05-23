package palaster97.ss.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStaffTime extends ItemModStaff {

	public ItemStaffTime() {
		super();
		setUnlocalizedName("staffTime");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		if(!worldIn.isRemote) {
			worldIn.provider.setWorldTime(worldIn.provider.getWorldTime() + 12000);
			itemStackIn.damageItem(1, playerIn);
		}
		return itemStackIn;
	}
}
