package com.james.tinkersancient.mixins;

import com.james.tinkersancient.contents.TinkersAncientEffects;
import com.james.tinkersancient.contents.TinkersAncientItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.StartAttacking;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.mantle.block.entity.InventoryBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.CastingBlockEntity;

@Mixin(CastingBlockEntity.class)
public abstract class CastingTableMixin extends InventoryBlockEntity {

    public CastingTableMixin(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, Component name, boolean saveSizeToNBT, int inventorySize) {
        super(tileEntityTypeIn, pos, state, name, saveSizeToNBT, inventorySize);
    }

    @Inject(at = @At("HEAD"), method = "interact", remap = false, cancellable = true)
    private void cancelInteract(Player player, InteractionHand hand, CallbackInfo ci) {
        if (player.getItemInHand(hand).is(TinkersAncientItems.melting_pan_handle_cast_fragment_2.get()) && getItem(0).is(TinkersAncientItems.melting_pan_handle_cast_fragment_1.get())) {
            ci.cancel();
        }
        if (player.getItemInHand(hand).is(TinkersAncientItems.melting_pan_handle_cast_fragment_1.get()) && getItem(0).is(TinkersAncientItems.melting_pan_handle_cast_fragment_2.get())) {
            ci.cancel();
        }
        if (player.getItemInHand(hand).isEmpty() && getItem(0).is(TinkersAncientItems.melting_pan_handle_cast_incomplete.get())) {
            ci.cancel();
        }
    }
}