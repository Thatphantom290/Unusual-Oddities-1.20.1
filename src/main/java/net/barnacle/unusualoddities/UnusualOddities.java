package net.barnacle.unusualoddities;

import com.mojang.logging.LogUtils;
import net.barnacle.unusualoddities.entity.UNODEntities;
import net.barnacle.unusualoddities.entity.client.ModModelLayers;
import net.barnacle.unusualoddities.entity.client.ZhuchengtyrannusModel;
import net.barnacle.unusualoddities.entity.client.ZhuchengtyrannusRenderer;
import net.barnacle.unusualoddities.entity.custom.ZhuchengtyrannusEntity;
import net.barnacle.unusualoddities.item.UNODCreativeModeTabs;
import net.barnacle.unusualoddities.item.UNODItems;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(UnusualOddities.MOD_ID)
public class UnusualOddities
{
    public static final String MOD_ID = "unusualoddities";
    private static final Logger LOGGER = LogUtils.getLogger();

    public UnusualOddities(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        UNODCreativeModeTabs.register(modEventBus);
        UNODItems.register(modEventBus);
        UNODEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(UNODEntities.ZHUCHENGTYRANNUS.get(), ZhuchengtyrannusEntity.createAttributes().build());
        }
        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModModelLayers.ZHUCHENG_LAYER, ZhuchengtyrannusModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

            event.registerEntityRenderer(UNODEntities.ZHUCHENG_EGG_PROJECTILE.get(), ThrownItemRenderer::new);
            event.registerEntityRenderer(UNODEntities.ZHUCHENGTYRANNUS.get(), ZhuchengtyrannusRenderer::new);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
