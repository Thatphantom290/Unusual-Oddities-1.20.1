package net.barnacle.unusualoddities.entity.client;

import net.barnacle.unusualoddities.UnusualOddities;
import net.barnacle.unusualoddities.entity.custom.ZhuchengtyrannusEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ZhuchengtyrannusRenderer extends MobRenderer<ZhuchengtyrannusEntity, ZhuchengtyrannusModel<ZhuchengtyrannusEntity>> {

    public ZhuchengtyrannusRenderer(EntityRendererProvider.Context context) {

        super(context, new ZhuchengtyrannusModel<>(context.bakeLayer(ModModelLayers.ZHUCHENG_LAYER)), 1.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(ZhuchengtyrannusEntity entity) {
        return new ResourceLocation(UnusualOddities.MOD_ID, "textures/entity/zhuchengtyrannus.png");
    }
}