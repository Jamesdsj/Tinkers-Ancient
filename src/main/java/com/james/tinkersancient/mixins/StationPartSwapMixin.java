package com.james.tinkersancient.mixins;

import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.recipe.tinkerstation.ITinkerStationContainer;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tables.recipe.TinkerStationPartSwapping;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.List;

@Mixin(TinkerStationPartSwapping.class)
public class StationPartSwapMixin {
    List<ToolDefinition> ancientTools = List.of(TinkerTools.battlesign.get().getToolDefinition(), TinkerTools.warPick.get().getToolDefinition(), TinkerTools.skyStaff.get().getToolDefinition(), TinkerTools.earthStaff.get().getToolDefinition(), TinkerTools.ichorStaff.get().getToolDefinition());
    @Inject(remap=false,
            method = {"matches(Lslimeknights/tconstruct/library/recipe/tinkerstation/ITinkerStationContainer;Lnet/minecraft/world/level/Level;)Z"},
            at = {@At("RETURN")},
            cancellable = true)
    public void matchesAncient(ITinkerStationContainer inv, Level world, CallbackInfoReturnable<Boolean> cir) {
        for(ToolDefinition definition : ancientTools)
            if(ToolStack.from(inv.getTinkerableStack()).getDefinition().equals(definition))
                cir.setReturnValue(false);
    }

}
