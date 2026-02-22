package net.barnacle.unusualoddities.entity.custom;

import net.barnacle.unusualoddities.entity.UNODEntities;
import net.barnacle.unusualoddities.item.UNODItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.HitResult;

public class ZhuchengtyrannusEggEntity extends ThrowableItemProjectile {
    public ZhuchengtyrannusEggEntity(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    public ZhuchengtyrannusEggEntity(Level level, LivingEntity shooter) {
        super(UNODEntities.ZHUCHENG_EGG_PROJECTILE.get(), shooter, level);
    }

    @Override
    protected Item getDefaultItem() {
        return UNODItems.ZHUCHENGTYRANNUS_EGG.get();
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide()) {
            ZhuchengtyrannusEntity dino = UNODEntities.ZHUCHENGTYRANNUS.get().create(this.level());
            if (dino != null) {
                dino.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                dino.setBaby(true);

                dino.finalizeSpawn((ServerLevelAccessor) this.level(),
                        this.level().getCurrentDifficultyAt(dino.blockPosition()),
                        MobSpawnType.EVENT, null, null);

                this.level().addFreshEntity(dino);
                this.discard();
            }
        }
    }
}