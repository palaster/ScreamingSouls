package palaster.bb.entities.knowledge.pieces;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import palaster.bb.core.helpers.BBItemStackHelper;
import palaster.bb.entities.knowledge.BBKnowledgePiece;
import palaster.bb.items.BBArmor;
import palaster.bb.items.BBItems;

public class KPBoundArmor extends BBKnowledgePiece {

    public KPBoundArmor() {
        super("bb.kp.boundArmor", 0);
    }

    @Override
    public void onBookInteract(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {}

    @Override
    public void onBookRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        for(int i = 0; i < 4; i++) {
            if(playerIn.inventory.armorInventory[i] != null) {
                if(playerIn.inventory.armorInventory[i].getItem() == BBItems.boundHelmet || playerIn.inventory.armorInventory[i].getItem() == BBItems.boundChestplate || playerIn.inventory.armorInventory[i].getItem() == BBItems.boundLeggings || playerIn.inventory.armorInventory[i].getItem() == BBItems.boundBoots) {
                    if(playerIn.inventory.armorInventory[i].getItem() == BBItems.boundHelmet)
                        BBArmor.removeBoundArmorFromArmor(playerIn.inventory.armorInventory[i], playerIn, 3);
                    else if(playerIn.inventory.armorInventory[i].getItem() == BBItems.boundChestplate)
                        BBArmor.removeBoundArmorFromArmor(playerIn.inventory.armorInventory[i], playerIn, 2);
                    else if(playerIn.inventory.armorInventory[i].getItem() == BBItems.boundLeggings)
                        BBArmor.removeBoundArmorFromArmor(playerIn.inventory.armorInventory[i], playerIn, 1);
                    else if(playerIn.inventory.armorInventory[i].getItem() == BBItems.boundBoots)
                        BBArmor.removeBoundArmorFromArmor(playerIn.inventory.armorInventory[i], playerIn, 0);
                } else {
                    ItemStack boundArmor = new ItemStack(BBItems.boundHelmet);
                    switch(i) {
                        case 3: boundArmor = new ItemStack(BBItems.boundHelmet);
                            break;
                        case 2: boundArmor = new ItemStack(BBItems.boundChestplate);
                            break;
                        case 1: boundArmor = new ItemStack(BBItems.boundLeggings);
                            break;
                        case 0: boundArmor = new ItemStack(BBItems.boundBoots);
                            break;
                    }
                    playerIn.inventory.armorInventory[i] = BBItemStackHelper.setItemStackInsideItemStack(boundArmor, playerIn.inventory.armorInventory[i]);
                }
            } else {
                ItemStack boundArmor = new ItemStack(BBItems.boundHelmet);
                switch(i) {
                    case 3:
                        boundArmor = new ItemStack(BBItems.boundHelmet);
                        break;
                    case 2:
                        boundArmor = new ItemStack(BBItems.boundChestplate);
                        break;
                    case 1:
                        boundArmor = new ItemStack(BBItems.boundLeggings);
                        break;
                    case 0:
                        boundArmor = new ItemStack(BBItems.boundBoots);
                        break;
                }
                playerIn.inventory.armorInventory[i] = boundArmor;
            }
        }
    }

    @Override
    public void onBookUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {}
}
