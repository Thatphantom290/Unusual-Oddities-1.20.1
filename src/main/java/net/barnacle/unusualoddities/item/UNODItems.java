package net.barnacle.unusualoddities.item;

import net.barnacle.unusualoddities.UnusualOddities;
import net.barnacle.unusualoddities.item.custom.ZhuchengtyrannusEggItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.barnacle.unusualoddities.entity.UNODEntities.ZHUCHENGTYRANNUS;

public class UNODItems {
    public static DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, UnusualOddities.MOD_ID);

    public static final RegistryObject<Item> TYRANNISED_FOSSIL = ITEMS.register("tyrannised_fossil",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ZHUCHENGTYRANNUS_EGG = ITEMS.register("zhuchengtyrannus_egg",
            () -> new ZhuchengtyrannusEggItem(new Item.Properties()));

    public static final RegistryObject<Item> ZHUCHENGTYRANNUS_SPAWN_EGG =
            ITEMS.register("zhuchengtyrannus_spawn_egg", () -> new ForgeSpawnEggItem(ZHUCHENGTYRANNUS, 0x5C4033, 0x8B0000, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
