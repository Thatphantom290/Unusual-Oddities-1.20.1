package net.barnacle.unusualoddities.event;

import net.barnacle.unusualoddities.UnusualOddities;
import net.barnacle.unusualoddities.entity.client.ModModelLayers;
import net.barnacle.unusualoddities.entity.client.ZhuchengtyrannusModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnusualOddities.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ZHUCHENG_LAYER, ZhuchengtyrannusModel::createBodyLayer);
    }
}