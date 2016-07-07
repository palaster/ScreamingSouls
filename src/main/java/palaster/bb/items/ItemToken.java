package palaster.bb.items;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import palaster.bb.api.BBApi;
import palaster.bb.blocks.BlockBonfire;
import palaster.bb.libs.LibNBT;

public class ItemToken extends ItemModSpecial {

	public ItemToken() {
		super();
		setMaxDamage(1);
		setUnlocalizedName("token");
		addPropertyOverride(new ResourceLocation("type"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
            	if(stack.getItemDamage() > 0)
            		return stack.getItemDamage();
            	return 0.0f;
            }
        });
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(stack.getItemDamage() == 1) {
			if(stack.hasTagCompound() && stack.getTagCompound().getInteger(LibNBT.number) >= 0) {
				EntityLiving boss = null;
				try {
					boss = BBApi.getBossFromToken(stack.getTagCompound().getInteger(LibNBT.number)).getConstructor(World.class).newInstance(playerIn.worldObj);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				if(boss != null)
					tooltip.add(I18n.format("bb.undead.bossToken") + " : " + boss.getName());
			}
		} else if(stack.getItemDamage() == 2) {
			if(stack.hasTagCompound() && stack.getTagCompound().getInteger(LibNBT.number) >= 0) {
				ItemStack item = BBApi.getItemStackFromToken(stack.getTagCompound().getInteger(LibNBT.number));
				if(item != null)
					tooltip.add(I18n.format("bb.undead.itemToken") + " : " + item.getDisplayName());
			}
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote)
			if(!itemStackIn.hasTagCompound()) {
				itemStackIn.setTagCompound(new NBTTagCompound());
				itemStackIn.getTagCompound().setInteger(LibNBT.number, -1);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
			
	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		if(!world.isRemote) {
			if(!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setInteger(LibNBT.number, -1);
			}
			if(stack.getItemDamage() == 1) {
				if(stack.getTagCompound().getInteger(LibNBT.number) >= 0)
					if(world.getBlockState(pos) != null)
						if(world.getBlockState(pos).getBlock() instanceof BlockBonfire)
							if(BBApi.getBossFromToken(stack.getTagCompound().getInteger(LibNBT.number)) != null) {
								EntityLiving boss = null;
								try {
									boss = BBApi.getBossFromToken(stack.getTagCompound().getInteger(LibNBT.number)).getConstructor(World.class).newInstance(world);
								} catch (InstantiationException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								} catch (NoSuchMethodException e) {
									e.printStackTrace();
								} catch (SecurityException e) {
									e.printStackTrace();
								}
								if(boss != null) {
									boss.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
									world.spawnEntityInWorld(boss);
									player.setHeldItem(hand, null);
									return EnumActionResult.SUCCESS;
								}
							}
			} else if(stack.getItemDamage() == 2)
				if(stack.getTagCompound().getInteger(LibNBT.number) >= 0)
					if(world.getBlockState(pos) != null)
						if(world.getBlockState(pos).getBlock() instanceof BlockBonfire)
							if(BBApi.getItemStackFromToken(stack.getTagCompound().getInteger(LibNBT.number)) != null) {
								player.setHeldItem(hand, BBApi.getItemStackFromToken(stack.getTagCompound().getInteger(LibNBT.number)));
								return EnumActionResult.SUCCESS;
							}
		}
		return super.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ, hand);
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) { return false; }
	
	@SuppressWarnings("deprecation")
	@Override
	public String getItemStackDisplayName(ItemStack stack) { return ("" + net.minecraft.util.text.translation.I18n.translateToLocal(getUnlocalizedNameInefficiently(stack) + "." + stack.getItemDamage() + ".name")).trim(); }
}
