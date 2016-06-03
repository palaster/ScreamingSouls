package palaster.bb.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import palaster.bb.core.CreativeTabBB;
import palaster.bb.libs.LibMod;

public abstract class BlockMod extends Block {

	public BlockMod(Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(CreativeTabBB.tabBB);
		setHardness(3F);
		setHarvestLevel("pickaxe", 0);
	}
	
	@Override
	public Block setUnlocalizedName(String name) {
		setRegistryName(new ResourceLocation(LibMod.modid, name));
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(getRegistryName()));
		setCustomModelResourceLocation();
		return super.setUnlocalizedName(LibMod.modid + ":" + name);
	}

	@SideOnly(Side.CLIENT)
	public void setCustomModelResourceLocation() { ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory")); }
}
