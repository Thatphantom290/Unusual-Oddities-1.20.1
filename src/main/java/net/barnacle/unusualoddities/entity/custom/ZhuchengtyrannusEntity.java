package net.barnacle.unusualoddities.entity.custom;

import net.barnacle.unusualoddities.entity.UNODEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class ZhuchengtyrannusEntity extends Animal {
    private static final EntityDataAccessor<Boolean> IS_ROARING = SynchedEntityData.defineId(ZhuchengtyrannusEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState runAnimationState = new AnimationState();

    public int roarCooldown = 0;

    public ZhuchengtyrannusEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    // Dentro de ZhuchengtyrannusEntity.java

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {

        boolean isMoving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;

        if (isMoving) {
            this.idleAnimationState.stop();

            this.walkAnimationState.startIfStopped(this.tickCount);
        } else {
            this.walkAnimationState.stop();
            this.idleAnimationState.startIfStopped(this.tickCount);
        }

        if (this.isRoaring()) {
            this.attackAnimationState.startIfStopped(this.tickCount);
        } else {
            this.attackAnimationState.stop();
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RoarGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true) {
            @Override public boolean canUse() { return !ZhuchengtyrannusEntity.this.isBaby() && super.canUse(); }
        });
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);
    }

    public static class RoarGoal extends Goal {
        private final ZhuchengtyrannusEntity mob;
        private int roarTime;

        public RoarGoal(ZhuchengtyrannusEntity mob) {
            this.mob = mob;

            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {

            return mob.getTarget() != null && mob.roarCooldown == 0 && !mob.isRoaring();
        }

        @Override
        public void start() {
            mob.setRoaring(true);
            this.roarTime = 70;
            mob.playSound(SoundEvents.RAVAGER_ROAR, 1.5F, 0.8F);
        }

        @Override
        public void stop() {
            mob.setRoaring(false);
            mob.roarCooldown = 100;
        }

        @Override
        public void tick() {

            this.roarTime--;
        }

        @Override
        public boolean canContinueToUse() {
            return this.roarTime > 0;
        }
    }

    @Override
    public boolean isBaby() {
        return this.getAge() < 0;
    }

    public boolean isRoaring() {
        return this.entityData.get(IS_ROARING);
    }

    public void setRoaring(boolean roaring) {
        this.entityData.set(IS_ROARING, roaring);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_ROARING, false);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return UNODEntities.ZHUCHENGTYRANNUS.get().create(level);
    }
}