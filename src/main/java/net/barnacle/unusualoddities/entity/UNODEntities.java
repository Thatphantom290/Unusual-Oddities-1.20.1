package net.barnacle.unusualoddities.entity;

import net.barnacle.unusualoddities.UnusualOddities;
import net.barnacle.unusualoddities.entity.custom.ZhuchengtyrannusEggEntity;
import net.barnacle.unusualoddities.entity.custom.ZhuchengtyrannusEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UNODEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, UnusualOddities.MOD_ID);

    public static final RegistryObject<EntityType<ZhuchengtyrannusEntity>> ZHUCHENGTYRANNUS =
            ENTITIES.register("zhuchengtyrannus", () -> EntityType.Builder.of(ZhuchengtyrannusEntity::new, MobCategory.CREATURE)
                    .sized(2.5f, 3.5f)
                    .build("zhuchengtyrannus"));

    public static final RegistryObject<EntityType<ZhuchengtyrannusEggEntity>> ZHUCHENG_EGG_PROJECTILE =
            ENTITIES.register("zhucheng_egg_projectile",
                    () -> EntityType.Builder.<ZhuchengtyrannusEggEntity>of(ZhuchengtyrannusEggEntity::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F).build("zhucheng_egg_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}