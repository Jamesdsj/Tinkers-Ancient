package com.james.tinkersancient.mixins;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.recipe.partbuilder.IPartBuilderContainer;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tables.recipe.PartBuilderToolRecycle;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.Collections;
import java.util.List;

@Mixin(PartBuilderToolRecycle.class)
public class PartBuilderRecycleMixin {
    List<ToolDefinition> ancientTools = List.of(TinkerTools.battlesign.get().getToolDefinition(), TinkerTools.warPick.get().getToolDefinition(), TinkerTools.skyStaff.get().getToolDefinition(), TinkerTools.earthStaff.get().getToolDefinition(), TinkerTools.ichorStaff.get().getToolDefinition());

    @Inject(remap=false,
            method = {"matches(Lslimeknights/tconstruct/library/recipe/partbuilder/IPartBuilderContainer;Lnet/minecraft/world/level/Level;)Z"},
            at = {@At("RETURN")},
            cancellable = true)
    public void matchesAncient(IPartBuilderContainer inv, Level pLevel, CallbackInfoReturnable<Boolean> cir) {
        for (ToolDefinition definition : ancientTools) {
            if (ToolStack.from(inv.getStack()).getDefinition().equals(definition))
                cir.setReturnValue(false);
        }
    }

    @Inject(remap=false,
            method = {"getText"},
            at = {@At("RETURN")},
            cancellable = true)
    public void textAncient(IPartBuilderContainer inv, CallbackInfoReturnable<List<Component>> cir) {
        for (ToolDefinition definition : ancientTools) {
            if (ToolStack.from(inv.getStack()).getDefinition().equals(definition)) {
                cir.setReturnValue(NO_ANCIENT);
                return;
            }
        }
        if (ModifierUtil.hasUpgrades(inv.getStack())) {
            cir.setReturnValue(NO_MODIFIERS);
        } else {
            cir.setReturnValue(INSTRUCTIONS);
        }
    }

    private static final List<Component> INSTRUCTIONS = Collections.singletonList(TConstruct.makeTranslation("recipe", "tool_recycling.info"));

    private static final List<Component> NO_MODIFIERS = Collections.singletonList(TConstruct.makeTranslation("recipe", "tool_recycling.no_modifiers").withStyle(ChatFormatting.RED));
    @Unique
    private static final List<Component> NO_ANCIENT = Collections.singletonList(TConstruct.makeTranslation("recipe", "tool_recycling.no_ancient").withStyle(ChatFormatting.RED));

}
