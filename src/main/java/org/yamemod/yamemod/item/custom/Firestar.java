package org.yamemod.yamemod.item.custom;

import jdk.nashorn.internal.ir.BlockStatement;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import java.util.Objects;

public class Firestar extends Item {
    public Firestar(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();
        
        if(!world.isClientSide) {
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());

            rightClickOnCertainBlockState(clickedBlock, context, playerEntity);
            stack.hurtAndBreak(1, playerEntity, player ->
                    player.broadcastBreakEvent(context.getHand()));
        }
        return super.onItemUseFirst(stack, context);
    }

    private void rightClickOnCertainBlockState(BlockState clickedBlock, ItemUseContext context,
                                               PlayerEntity playerEntity) {
        if(random.nextFloat() > 0.5f) {
          lightEntityOnFire(playerEntity, 3);
        } else if(!playerEntity.isOnFire() && blockIsValidForResistance(clickedBlock)) {
            gainFireResistenceAndDestroyBlock(playerEntity, context.getLevel(),
                    context.getClickedPos());
        }else {
            lightGroundOnFire(context);
        }
    }

    private boolean blockIsValidForResistance(BlockState clickedBlock) {
        return clickedBlock.getBlock() == Blocks.OBSIDIAN || clickedBlock.getBlock() == Blocks.NETHERRACK;
    }

    public static void lightEntityOnFire(Entity entity, int seconds) {
        entity.setSecondsOnFire(seconds);
    }

    private void gainFireResistenceAndDestroyBlock(PlayerEntity playerEntity, World world, BlockPos pos) {
        gainFireResistence(playerEntity);
        world.destroyBlock(pos, false);
    }
    public static void gainFireResistence(PlayerEntity playerEntity) {
        playerEntity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
    }

    public static void lightGroundOnFire(ItemUseContext context) {

        PlayerEntity player = context.getPlayer();
        World world = context.getLevel();
        BlockPos blockPos = context.getClickedPos();

        if (AbstractFireBlock.canBePlacedAt(world, blockPos, context.getHorizontalDirection())) {

            world.playSound(player, blockPos, SoundEvents.FLINTANDSTEEL_USE,
                    SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.4F + 0.8F);

            BlockState fireState = AbstractFireBlock.getState(world, blockPos);
            world.setBlock(blockPos, fireState, 11);

            ItemStack stack = context.getItemInHand();

            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, blockPos, stack);
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
            }
        }
    }
}
