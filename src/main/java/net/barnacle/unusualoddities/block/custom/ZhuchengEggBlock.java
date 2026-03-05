package net.barnacle.unusualoddities.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import net.barnacle.unusualoddities.entity.UNODEntities;
import net.barnacle.unusualoddities.entity.custom.ZhuchengtyrannusEntity;

public class ZhuchengEggBlock extends Block {

    public static final IntegerProperty HATCH = IntegerProperty.create("hatch", 0, 2);

    public ZhuchengEggBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HATCH);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level,
                           BlockPos pos, RandomSource random) {

        if (random.nextInt(10) != 0) return;

        int hatch = state.getValue(HATCH);

        if (hatch < 2) {
            level.setBlock(pos, state.setValue(HATCH, hatch + 1), 2);

            level.playSound(null, pos,
                    SoundEvents.TURTLE_EGG_CRACK,
                    SoundSource.BLOCKS,
                    1.0F, 1.0F);
        }
        else {
            hatch(level, pos);
        }
    }

    private void hatch(ServerLevel level, BlockPos pos) {

        level.destroyBlock(pos, false);

        ZhuchengtyrannusEntity dino =
                UNODEntities.ZHUCHENGTYRANNUS.get().create(level);

        if (dino != null) {
            dino.setBaby(true);

            dino.moveTo(
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5,
                    level.random.nextFloat() * 360F,
                    0
            );

            level.addFreshEntity(dino);
        }

        level.playSound(null, pos,
                SoundEvents.TURTLE_EGG_HATCH,
                SoundSource.BLOCKS,
                1F, 1F);
    }
}