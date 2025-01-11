package com.james.tinkersancient;

import com.james.tinkersancient.contents.TinkersAncientItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.shared.TinkerMaterials;

public class ModGroup extends CreativeModeTab {
    public ModGroup() {
    super("TinkersAncientGroup");
}
    public static final CreativeModeTab itemGroup = new ModGroup();

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(TinkersAncientItems.battlesign_head_cast.get());
    }

}
