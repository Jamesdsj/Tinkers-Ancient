package com.james.tinkersancient.contents;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.registration.EnumDeferredRegister;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

import java.util.function.IntFunction;
import java.util.function.Supplier;



public class TinkersAncientEffects{
    public static final EnumDeferredRegister<MobEffect> MOB_EFFECTS = new EnumDeferredRegister<>(Registries.MOB_EFFECT, "tinkersancient");;
    private static final IntFunction<Supplier<TinkerEffect>> MARKER_EFFECT = color -> () -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, color, true);
    public static final RegistryObject<TinkerEffect> attackDisabledEffect = MOB_EFFECTS.register("attack_disabled", () -> new NoMilkEffect(MobEffectCategory.NEUTRAL, 0xFC9600, false));
}
