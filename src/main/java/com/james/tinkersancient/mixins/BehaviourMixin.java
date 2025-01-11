package com.james.tinkersancient.mixins;

import com.james.tinkersancient.contents.TinkersAncientEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.StartAttacking;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Behavior.class)
public class BehaviourMixin<E extends LivingEntity> {

    @Inject(at = @At("HEAD"), method = "tryStart", cancellable = true)
    private void tryStart(ServerLevel level, E entity, long time, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof PiglinBrute brute && this.cast() instanceof StartAttacking) {
            if (entity.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_ITEM) || brute.hasEffect(TinkersAncientEffects.attackDisabledEffect.get())) {
                cir.setReturnValue(false);
            }
        }
    }

    private Behavior<?> cast() {
        //noinspection ConstantConditions
        return (Behavior<?>) (Object) this;
    }
}