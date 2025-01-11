package com.james.tinkersancient.mixins;

import com.google.common.collect.ImmutableList;
import com.james.tinkersancient.contents.TinkersAncientEffects;
import com.james.tinkersancient.contents.TinkersAncientItems;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.monster.piglin.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.TinkerMaterials;
import slimeknights.tconstruct.world.TinkerWorld;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.james.tinkersancient.TinkersAncient.*;

@Mixin(PiglinBrute.class)
public abstract class PiglinBruteMixin extends AbstractPiglin {
    @Shadow public abstract Brain<PiglinBrute> getBrain();

    @Shadow @Final protected static ImmutableList<MemoryModuleType<?>> MEMORY_TYPES;

    public PiglinBruteMixin(EntityType<? extends AbstractPiglin> p_34683_, Level p_34684_) {
        super(p_34683_, p_34684_);
        this.xpReward = 5;
    }

    List<ItemStack> responseList = List.of(new ItemStack(TinkerWorld.ichorGeode.get(), RANDOM.nextInt(16, 32)), new ItemStack(TinkerWorld.ichorGeode.get(), RANDOM.nextInt(14, 28)), new ItemStack(Items.WITHER_SKELETON_SKULL, RANDOM.nextInt(1, 2)), new ItemStack(TinkerWorld.rawCobalt.get(), RANDOM.nextInt(8, 10)), new ItemStack(TinkerWorld.rawCobalt.get(), RANDOM.nextInt(12, 15)), new ItemStack(TinkerWorld.rawCobalt.get(), RANDOM.nextInt(4, 7)), new ItemStack(Items.ANCIENT_DEBRIS, RANDOM.nextInt(4, 7)), new ItemStack(Items.DIAMOND, RANDOM.nextInt(5, 9)), new ItemStack(TinkersAncientItems.battlesign_handle_cast.get()), new ItemStack(TinkersAncientItems.battlesign_head_cast.get()));
    @Override
    public boolean canHunt() {
        return false;
    }

    @Override
    public PiglinArmPose getArmPose() {
        return null;
    }

    @Override
    protected void playConvertedSound() {

    }

    @Inject(method = {"getArmPose"},
            at = {@At("RETURN")},
            cancellable = true)
    public void getPose(CallbackInfoReturnable<PiglinArmPose> cir) {
        if ((this.getOffhandItem().is(ItemTags.PIGLIN_LOVED))) {
            cir.setReturnValue(PiglinArmPose.ADMIRING_ITEM);
        } else if (this.isAggressive() && this.isHoldingMeleeWeapon()) {
            cir.setReturnValue(PiglinArmPose.ATTACKING_WITH_MELEE_WEAPON);
        } else {
            cir.setReturnValue(PiglinArmPose.DEFAULT);
        }
    }

    @Inject(method = {"customServerAiStep"},
            at = {@At("RETURN")}
    )
    public void customServerAiStep(CallbackInfo ci) {
        if (this.getOffhandItem().is(Items.GOLD_BLOCK) && !this.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_ITEM)) {
            this.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
            Optional<Player> optional = this.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);
            int count = this.getPersistentData().getInt("golden_armor_count");
            if(RANDOM_2.nextFloat() <= 0.4f + count * 0.15f) {
                this.swing(InteractionHand.OFF_HAND);
                int i = this.getCommandSenderWorld().random.nextInt(responseList.size());
                BehaviorUtils.throwItem(this, responseList.get(i), optional.map(player -> player.position().add(0.0, 1.0, 0.0)).orElseGet(() -> getRandomNearbyPos(this).add(0.0, 1.0, 0.0)));
                this.addEffect(new MobEffectInstance(TinkersAncientEffects.attackDisabledEffect.get(), 300, 0));
            }
        }
    }

    @Inject(method = {"hurt"},
            at = {@At("RETURN")}
    )
    public void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(source.getEntity() instanceof Player && this.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_ITEM))
        {
            this.getBrain().eraseMemory(MemoryModuleType.ADMIRING_ITEM);
            this.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
        }
        if(source.getEntity() instanceof Player player && player.getPersistentData().getBoolean("piglin_brute_friendly"))
        {
            player.getPersistentData().remove("piglin_brute_friendly");
        }
    }


    private static Vec3 getRandomNearbyPos(AbstractPiglin p_35017_) {
        Vec3 vec3 = LandRandomPos.getPos(p_35017_, 4, 2);
        return vec3 == null ? p_35017_.position() : vec3;
    }
}
