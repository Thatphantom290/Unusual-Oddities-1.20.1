package net.barnacle.unusualoddities.entity.custom;

import net.barnacle.unusualoddities.entity.UNODEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class ZhuchengtyrannusEntity extends Animal {
    private static final EntityDataAccessor<Boolean> IS_ROARING = SynchedEntityData.defineId(ZhuchengtyrannusEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_RUNNING = SynchedEntityData.defineId(ZhuchengtyrannusEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID =
            SynchedEntityData.defineId(ZhuchengtyrannusEntity.class, EntityDataSerializers.INT);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState runAnimationState = new AnimationState();
    public final AnimationState swimAnimationState = new AnimationState();

    public int roarCooldown = 0;

    public ZhuchengtyrannusEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(IS_ROARING, false);
        this.entityData.define(IS_RUNNING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
    }

    public int getVariant() { return this.entityData.get(DATA_VARIANT_ID); }
    public void setVariant(int variant) { this.entityData.set(DATA_VARIANT_ID, variant); }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag nbt) {
        this.setVariant(this.random.nextInt(3));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData, nbt);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }

        if (!this.level().isClientSide()) {
            this.setRunning(this.getTarget() != null);
        } else {
            setupAnimationStates();
        }

        if (this.roarCooldown > 0) {
            this.roarCooldown--;
        }
    }

    private void setupAnimationStates() {

        double horizontalSpeed = this.getDeltaMovement().horizontalDistance();
        boolean isMoving = horizontalSpeed > 0.01D;
        boolean inWater = this.isInWater();

        if (inWater) {

            this.idleAnimationState.stop();
            this.walkAnimationState.stop();
            this.runAnimationState.stop();

            this.swimAnimationState.startIfStopped(this.tickCount);
        }

        else if (isMoving) {
            this.swimAnimationState.stop();
            this.idleAnimationState.stop();

            if (horizontalSpeed > 0.15D || this.isSprinting()) {
                this.walkAnimationState.stop();
                this.runAnimationState.startIfStopped(this.tickCount);
            } else {
                this.runAnimationState.stop();
                this.walkAnimationState.startIfStopped(this.tickCount);
            }
        }

        else {
            this.swimAnimationState.stop();
            this.walkAnimationState.stop();
            this.runAnimationState.stop();

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
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.05D, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true) {
            @Override public boolean canUse() { return !ZhuchengtyrannusEntity.this.isBaby() && super.canUse(); }
        });
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (super.doHurtTarget(target)) {

            if (target instanceof LivingEntity livingTarget) {

                livingTarget.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 0), this);
            }
            return true;
        }
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
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

    public boolean isRunning() {
        return this.entityData.get(IS_RUNNING);
    }

    public void setRunning(boolean running) {
        this.entityData.set(IS_RUNNING, running);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return UNODEntities.ZHUCHENGTYRANNUS.get().create(level);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();

        if (!this.level().isClientSide() && this.getVariant() == 0) {
            this.setVariant(this.random.nextInt(3));
        }
    }
}