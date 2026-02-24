package net.barnacle.unusualoddities.entity.ai;

import net.barnacle.unusualoddities.entity.custom.ZhuchengtyrannusEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;

public class RunGoal extends Goal {

    private final ZhuchengtyrannusEntity mob;

    public RunGoal(ZhuchengtyrannusEntity mob) {
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return mob.getTarget() != null && !mob.isPassive();
    }

    @Override
    public void start() {
        mob.setRunning(true);
        mob.getAttribute(Attributes.MOVEMENT_SPEED)
                .setBaseValue(0.45D);
    }

    @Override
    public void stop() {
        mob.setRunning(false);
        mob.getAttribute(Attributes.MOVEMENT_SPEED)
                .setBaseValue(0.25D);
    }
}