package net.barnacle.unusualoddities.entity.client;

import net.barnacle.unusualoddities.UnusualOddities;
import net.barnacle.unusualoddities.entity.custom.ZhuchengtyrannusEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ZhuchengtyrannusRenderer extends MobRenderer<ZhuchengtyrannusEntity, ZhuchengtyrannusModel<ZhuchengtyrannusEntity>> {

    public ZhuchengtyrannusRenderer(EntityRendererProvider.Context context) {

        super(context, new ZhuchengtyrannusModel<>(context.bakeLayer(ModModelLayers.ZHUCHENG_LAYER)), 2.5f);
    }
    private static final ResourceLocation NEON = new ResourceLocation(UnusualOddities.MOD_ID, "textures/entity/zhuchengtyrannus.png");
    private static final ResourceLocation YING = new ResourceLocation(UnusualOddities.MOD_ID, "textures/entity/zhuchengtyrannus_ying.png");
    private static final ResourceLocation DEFAULT = new ResourceLocation(UnusualOddities.MOD_ID, "textures/entity/zhuchengtyrannus_neon.png");

    @Override
    public ResourceLocation getTextureLocation(ZhuchengtyrannusEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> NEON;
            case 2 -> YING;
            default -> DEFAULT;
        };
    }
}