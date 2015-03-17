package palaster97.ss.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLittlePeople extends ModelBase {
	
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer lArm;
    ModelRenderer rArm;
    ModelRenderer lLeg;
    ModelRenderer rLeg;
	
	public ModelLittlePeople() {    
		head = new ModelRenderer(this, 0, 0);
		head.addBox(0F, 0F, 0F, 6, 6, 6);
		head.setRotationPoint(-3F, 9F, -3F);
		body = new ModelRenderer(this, 24, 0);
		body.addBox(0F, 0F, 0F, 6, 6, 3);
		body.setRotationPoint(-3F, 15F, -1.5F);
		lArm = new ModelRenderer(this, 0, 12);
		lArm.addBox(0F, 0F, 0F, 2, 6, 3);
		lArm.setRotationPoint(3F, 15F, -1.5F);
		rArm = new ModelRenderer(this, 0, 12);
		rArm.addBox(0F, 0F, 0F, 2, 6, 3);
		rArm.setRotationPoint(-5F, 15F, -1.5F);
		lLeg = new ModelRenderer(this, 10, 15);
		lLeg.addBox(0F, 0F, 0F, 3, 3, 3);
		lLeg.setRotationPoint(0F, 21F, -1.5F);
		rLeg = new ModelRenderer(this, 10, 15);
		rLeg.addBox(0F, 0F, 0F, 3, 3, 3);
		rLeg.setRotationPoint(-3F, 21F, -1.5F);
	}
	
	@Override
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
		super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
		setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
		head.render(p_78088_7_);
		body.render(p_78088_7_);
		lArm.render(p_78088_7_);
		rArm.render(p_78088_7_);
		lLeg.render(p_78088_7_);
		rLeg.render(p_78088_7_);
	}
	
	@Override
	public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
		// TODO: Get Entity Sitting Working
	}
	
	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
		/* TODO : FIX WEIRD HEAD MOVEMENTS
		head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
		head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
		*/
        rArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 2.0F * p_78087_2_ * 0.5F;
        lArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 2.0F * p_78087_2_ * 0.5F;
        rArm.rotateAngleZ = 0.0F;
        lArm.rotateAngleZ = 0.0F;
        rLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        lLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        rLeg.rotateAngleY = 0.0F;
        lLeg.rotateAngleY = 0.0F;
	}
}
