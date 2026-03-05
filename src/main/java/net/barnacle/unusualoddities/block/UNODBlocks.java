package net.barnacle.unusualoddities.block;

import net.barnacle.unusualoddities.UnusualOddities;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.barnacle.unusualoddities.block.custom.ZhuchengEggBlock;
import net.barnacle.unusualoddities.item.UNODItems;

import java.util.function.Supplier;

public class UNODBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, UnusualOddities.MOD_ID);

    public static final RegistryObject<Block> ZHUCHENGTYRANNUS_EGG = registerBlock("zhuchengtyrannus_egg",
            () -> new ZhuchengEggBlock(BlockBehaviour.Properties.copy(Blocks.DRAGON_EGG)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return UNODItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}