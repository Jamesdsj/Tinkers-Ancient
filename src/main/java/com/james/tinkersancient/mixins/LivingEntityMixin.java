package com.james.tinkersancient.mixins;

import com.james.tinkersancient.contents.TinkersAncientEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.StartAttacking;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(at = @At("RETURN"), method = "canAttack(Lnet/minecraft/world/entity/LivingEntity;)Z", cancellable = true)
    private void cannotAttack(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this instanceof PiglinBrute brute) {
            if (brute.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_ITEM) || brute.hasEffect(TinkersAncientEffects.attackDisabledEffect.get())) {
                cir.setReturnValue(false);
            }
        }
    }

}