package net.barnacle.unusualoddities.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.barnacle.unusualoddities.entity.animations.ModAnimationDefition;
import net.barnacle.unusualoddities.entity.custom.ZhuchengtyrannusEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ZhuchengtyrannusModel<T extends ZhuchengtyrannusEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = ModModelLayers.ZHUCHENG_LAYER;
    private final ModelPart literallyeverything;
	private final ModelPart everything_except_legs;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart upper_mouth_thingie;
	private final ModelPart jaw_2;
	private final ModelPart lower_jaw;
	private final ModelPart tail_1;
	private final ModelPart tail_2;
	private final ModelPart tail_3;
	private final ModelPart arm_1;
	private final ModelPart arm_2;
	private final ModelPart body_wowie;
	private final ModelPart legs;
	private final ModelPart upper_leg_1;
	private final ModelPart middle_leg_1;
	private final ModelPart foot_1;
	private final ModelPart upper_leg_2;
	private final ModelPart middle_leg_2;
	private final ModelPart foot_2;

	public ZhuchengtyrannusModel(ModelPart root) {
		this.literallyeverything = root.getChild("literallyeverything");
		this.everything_except_legs = this.literallyeverything.getChild("everything_except_legs");
		this.neck = this.everything_except_legs.getChild("neck");
		this.head = this.neck.getChild("head");
		this.upper_mouth_thingie = this.head.getChild("upper_mouth_thingie");
		this.jaw_2 = this.upper_mouth_thingie.getChild("jaw_2");
		this.lower_jaw = this.jaw_2.getChild("lower_jaw");
		this.tail_1 = this.everything_except_legs.getChild("tail_1");
		this.tail_2 = this.tail_1.getChild("tail_2");
		this.tail_3 = this.tail_2.getChild("tail_3");
		this.arm_1 = this.everything_except_legs.getChild("arm_1");
		this.arm_2 = this.everything_except_legs.getChild("arm_2");
		this.body_wowie = this.everything_except_legs.getChild("body_wowie");
		this.legs = this.literallyeverything.getChild("legs");
		this.upper_leg_1 = this.legs.getChild("upper_leg_1");
		this.middle_leg_1 = this.upper_leg_1.getChild("middle_leg_1");
		this.foot_1 = this.middle_leg_1.getChild("foot_1");
		this.upper_leg_2 = this.legs.getChild("upper_leg_2");
		this.middle_leg_2 = this.upper_leg_2.getChild("middle_leg_2");
		this.foot_2 = this.middle_leg_2.getChild("foot_2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition literallyeverything = partdefinition.addOrReplaceChild("literallyeverything", CubeListBuilder.create(), PartPose.offset(0.0F, -35.0F, 18.0F));

		PartDefinition everything_except_legs = literallyeverything.addOrReplaceChild("everything_except_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = everything_except_legs.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(67, 284).addBox(-4.5F, -30.0F, -19.0F, 10.0F, 46.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -11.0F, -40.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, -13.0F));

		PartDefinition upper_mouth_thingie = head.addOrReplaceChild("upper_mouth_thingie", CubeListBuilder.create().texOffs(0, 95).addBox(-4.0F, -16.0F, -30.0F, 8.0F, 16.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(46, 48).addBox(-7.0F, -16.0F, -15.0F, 14.0F, 16.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 77).addBox(-8.0F, -19.0F, -13.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(20, 77).addBox(4.0F, -19.0F, -13.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(68, 22).addBox(6.0F, -18.0F, -7.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(40, 82).addBox(-8.0F, -18.0F, -7.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(68, 0).addBox(4.0F, -24.0F, -13.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(68, 11).addBox(-8.0F, -24.0F, -13.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(42, 71).addBox(-12.0F, -24.0F, -13.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(62, 71).addBox(8.0F, -24.0F, -13.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 59).addBox(-3.0F, 0.0F, -30.0F, 6.0F, 3.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(46, 30).addBox(-6.0F, 0.0F, -15.0F, 12.0F, 3.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(68, 104).addBox(-7.0F, -16.0F, -8.0F, 14.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(41, 78).addBox(5.0F, -8.0F, -16.0F, 0.0F, 13.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(41, 78).addBox(-5.0F, -8.0F, -16.0F, 0.0F, 13.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, 0.0F));

		PartDefinition jaw_2 = upper_mouth_thingie.addOrReplaceChild("jaw_2", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, 0.0F));

		PartDefinition lower_jaw = jaw_2.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(76, 77).addBox(-5.0F, -3.0F, -15.0F, 11.0F, 9.0F, 15.0F, new CubeDeformation(-0.01F))
				.texOffs(11, 6).addBox(-2.0F, 0.0F, -29.0F, 5.0F, 4.0F, 14.0F, new CubeDeformation(-0.01F))
				.texOffs(89, 22).addBox(-1.0F, -2.0F, -28.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(82, 0).addBox(-4.0F, -5.0F, -15.0F, 9.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_1 = everything_except_legs.addOrReplaceChild("tail_1", CubeListBuilder.create().texOffs(172, 28).addBox(-13.0F, -10.0F, 0.0F, 26.0F, 24.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 20.0F));

		PartDefinition tail_2 = tail_1.addOrReplaceChild("tail_2", CubeListBuilder.create().texOffs(269, 0).addBox(-8.0F, -7.0F, 0.0F, 16.0F, 16.0F, 36.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 34.0F));

		PartDefinition tail_3 = tail_2.addOrReplaceChild("tail_3", CubeListBuilder.create().texOffs(309, 62).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 8.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 36.0F));

		PartDefinition arm_1 = everything_except_legs.addOrReplaceChild("arm_1", CubeListBuilder.create().texOffs(88, 243).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 20.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(4, 228).addBox(2.0F, 20.0F, -4.0F, 0.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(18.0F, 17.0F, -35.0F));

		PartDefinition arm_2 = everything_except_legs.addOrReplaceChild("arm_2", CubeListBuilder.create().texOffs(4, 228).addBox(-2.0F, 20.0F, -4.0F, 0.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-18.0F, 17.0F, -35.0F));

		PartDefinition cube_r1 = arm_2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(57, 240).addBox(18.0F, 45.0F, -1.0F, 4.0F, 20.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.0F, -45.0F, 3.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition body_wowie = everything_except_legs.addOrReplaceChild("body_wowie", CubeListBuilder.create().texOffs(151, 116).addBox(-3.0F, -28.0F, -21.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(151, 116).addBox(-3.0F, -28.0F, -12.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(151, 116).addBox(-3.0F, -28.0F, -3.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(115, 147).addBox(-16.0F, -24.0F, -28.0F, 32.0F, 49.0F, 60.0F, new CubeDeformation(0.0F))
				.texOffs(58, 135).addBox(16.0F, -20.0F, -31.0F, 4.0F, 0.0F, 63.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -12.0F));

		PartDefinition cube_r2 = body_wowie.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(58, 135).addBox(0.0F, 0.0F, -19.0F, 4.0F, 0.0F, 63.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -20.0F, -12.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition legs = literallyeverything.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(18.0F, -5.0F, 6.0F));

		PartDefinition upper_leg_1 = legs.addOrReplaceChild("upper_leg_1", CubeListBuilder.create().texOffs(214, 286).addBox(-5.0F, 0.0F, -19.0F, 18.0F, 38.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

		PartDefinition middle_leg_1 = upper_leg_1.addOrReplaceChild("middle_leg_1", CubeListBuilder.create().texOffs(135, 286).addBox(-7.0F, 0.0F, -13.0F, 14.0F, 34.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 25.0F, 19.0F));

		PartDefinition foot_1 = middle_leg_1.addOrReplaceChild("foot_1", CubeListBuilder.create().texOffs(293, 111).addBox(-9.0F, 0.0F, -22.0F, 18.0F, 7.0F, 22.0F, new CubeDeformation(0.0F))
				.texOffs(198, 118).addBox(-9.0F, 0.0F, -26.0F, 18.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 32.0F, 3.0F));

		PartDefinition upper_leg_2 = legs.addOrReplaceChild("upper_leg_2", CubeListBuilder.create().texOffs(0, 134).addBox(-13.0F, 0.0F, -19.0F, 18.0F, 38.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offset(-34.0F, 0.0F, 0.0F));

		PartDefinition middle_leg_2 = upper_leg_2.addOrReplaceChild("middle_leg_2", CubeListBuilder.create().texOffs(305, 251).addBox(-7.0F, 0.0F, -13.0F, 14.0F, 34.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 25.0F, 19.0F));

		PartDefinition foot_2 = middle_leg_2.addOrReplaceChild("foot_2", CubeListBuilder.create().texOffs(293, 174).addBox(-9.0F, 0.0F, -22.0F, 18.0F, 7.0F, 22.0F, new CubeDeformation(0.0F))
				.texOffs(198, 118).addBox(-9.0F, 0.0F, -26.0F, 18.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 32.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 373, 373);
	}

	@Override
	public ModelPart root() {
		return literallyeverything;
	}

	// Dentro de ZhuchengtyrannusModel.java

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(entity.idleAnimationState, ModAnimationDefition.idle, ageInTicks);
		this.animate(entity.walkAnimationState, ModAnimationDefition.walk, ageInTicks, limbSwingAmount);
		this.animate(entity.attackAnimationState, ModAnimationDefition.scream, ageInTicks);
		this.animate(entity.runAnimationState, ModAnimationDefition.run, ageInTicks, limbSwingAmount);

		if (!entity.isRoaring()) {
			float totalYRot = netHeadYaw * ((float)Math.PI / 180F);
			float totalXRot = headPitch * ((float)Math.PI / 180F);

			this.neck.yRot = totalYRot * 0.5F;
			this.head.yRot = totalYRot * 0.5F;

			this.neck.xRot = totalXRot * 0.4F;
			this.head.xRot = totalXRot * 0.6F;
		}

		if (entity.isBaby()) {
			float headScale = 1.5F;
			this.neck.xScale = headScale;
			this.neck.yScale = headScale;
			this.neck.zScale = headScale;
			this.neck.y -= 2.0F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (this.young) {
			poseStack.pushPose();
			poseStack.scale(0.4F, 0.4F, 0.4F);
			poseStack.translate(0.0D, 2.25D, 0.0D);
			this.root().render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
			poseStack.popPose();
		} else {
			this.root().render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}
	}
}