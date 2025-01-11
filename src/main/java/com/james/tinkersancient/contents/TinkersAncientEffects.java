package com.james.tinkersancient.contents;

import com.james.tinkersancient.ModGroup;
import com.james.tinkersancient.TinkersAncient;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.common.registration.EnumDeferredRegister;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.PlatingMaterialStats;

import java.util.function.IntFunction;
import java.util.function.Supplier;



public class TinkersAncientEffects{
    public static final EnumDeferredRegister<MobEffect> MOB_EFFECTS = new EnumDeferredRegister<>(Registry.MOB_EFFECT_REGISTRY, "tinkersancient");;
    private static final IntFunction<Supplier<TinkerEffect>> MARKER_EFFECT = color -> () -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, color, true);
    public static final RegistryObject<TinkerEffect> attackDisabledEffect = MOB_EFFECTS.register("attack_disabled", () -> new NoMilkEffect(MobEffectCategory.NEUTRAL, 0xFC9600, false));
}
