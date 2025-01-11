package com.james.tinkersancient.mixins;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.tools.layout.StationSlotLayout;

@Mixin(StationSlotLayout.class)
public class StationScreenMixin{
    private static final Component BATTLESIGN_ACQUIRE = TConstruct.makeTranslation("gui", "battlesign.acquire").withStyle(ChatFormatting.RED);
    private static final Component WARPICK_ACQUIRE = TConstruct.makeTranslation("gui", "war_pick.acquire").withStyle(ChatFormatting.RED);

    @Inject(remap = false, method = "getDescription", at = @At("RETURN"), cancellable = true)
    private void getDescription(CallbackInfoReturnable<Component> cir) {
        Component value = cir.getReturnValue();
        if (value != null) {
            if (value.equals(Component.translatable("item.tinkersancient.battlesign.description"))) {
                Component valuenew = value.copy().append(BATTLESIGN_ACQUIRE);
                cir.setReturnValue(valuenew);
            }
            if (value.equals(Component.translatable("item.tinkersancient.war_pick.description"))) {
                Component valuenew = value.copy().append(WARPICK_ACQUIRE);
                cir.setReturnValue(valuenew);
            }
        }
    }
}// && ToolStack.from(result).getDefinition().equals(TinkerTools.battlesign.get().getToolDefinition())