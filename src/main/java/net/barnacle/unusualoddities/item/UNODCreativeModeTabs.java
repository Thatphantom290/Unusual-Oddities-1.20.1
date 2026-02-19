package net.barnacle.unusualoddities.item;

import net.barnacle.unusualoddities.UnusualOddities;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class UNODCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UnusualOddities.MOD_ID);

    public static final RegistryObject<CreativeModeTab> UNUSUAL_ODDITIES_TAB = CREATIVE_MODE_TABS.register("unusual_oddities_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(UNODItems.TYRANNISED_FOSSIL.get()))
                    .title(Component.translatable("creativetab.unusual_oddities_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(UNODItems.TYRANNISED_FOSSIL.get());
                        output.accept(UNODItems.ZHUCHENGTYRANNUS_EGG.get());
                        output.accept(UNODItems.ZHUCHENGTYRANNUS_SPAWN_EGG.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
