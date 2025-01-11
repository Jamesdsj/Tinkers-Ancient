package com.james.tinkersancient.contents;

import com.james.tinkersancient.TinkersAncient;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.item.TooltipItem;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.tools.part.PartCastItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.LimbMaterialStats;
import slimeknights.tconstruct.tools.stats.PlatingMaterialStats;

public class TinkersAncientItems {
    protected static final SynchronizedDeferredRegister<CreativeModeTab> CREATIVE_TABS = SynchronizedDeferredRegister.create(Registries.CREATIVE_MODE_TAB, TConstruct.MOD_ID);

    public static final RegistryObject<CreativeModeTab> tabItem = CREATIVE_TABS.register(
            "smeltery", () -> CreativeModeTab.builder().title(TConstruct.makeTranslation("itemGroup", "tinkersancient"))
                    .icon(() -> new ItemStack(TinkersAncientItems.BATTLESIGN_HEAD.get()))
                    .displayItems(TinkersAncientItems::addTabItems)
                    .withTabsBefore(TinkerToolParts.tabToolParts.getId())
                    .build());
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersAncient.MODID);
    private static final Item.Properties ToolItem = new Item.Properties().stacksTo(1);
    private static final Item.Properties CastItem = new Item.Properties().rarity(Rarity.UNCOMMON);

    public static final RegistryObject<ToolPartItem> BATTLESIGN_HEAD = ITEMS.register("battlesign_head", () -> new ToolPartItem(ToolItem, HeadMaterialStats.ID));
    public static final RegistryObject<ToolPartItem> BATTLESIGN_HANDLE = ITEMS.register("battlesign_handle", () -> new ToolPartItem(ToolItem, PlatingMaterialStats.SHIELD.getId()));
    public static RegistryObject<Item> battlesign_head_cast = ITEMS.register("battlesign_head_cast", () -> new PartCastItem(CastItem, BATTLESIGN_HEAD));
    public static RegistryObject<Item> battlesign_handle_cast = ITEMS.register("battlesign_handle_cast", () -> new PartCastItem(CastItem, BATTLESIGN_HANDLE));
    public static final RegistryObject<ToolPartItem> WAR_PICK_LIMB = ITEMS.register("war_pick_limb", () -> new ToolPartItem(ToolItem, LimbMaterialStats.ID));
    public static RegistryObject<Item> war_pick_limb_cast = ITEMS.register("war_pick_limb_cast", () -> new PartCastItem(CastItem, WAR_PICK_LIMB));
    public static final RegistryObject<ToolPartItem> WAR_PICK_HANDLE = ITEMS.register("war_pick_handle", () -> new ToolPartItem(ToolItem, HandleMaterialStats.ID));
    public static RegistryObject<Item> war_pick_handle_cast = ITEMS.register("war_pick_handle_cast", () -> new PartCastItem(CastItem, WAR_PICK_HANDLE));
    public static final RegistryObject<ToolPartItem> MELTING_PAN_HEAD = ITEMS.register("melting_pan_head", () -> new ToolPartItem(ToolItem, PlatingMaterialStats.SHIELD.getId()));
    public static RegistryObject<Item> melting_pan_head_cast = ITEMS.register("melting_pan_head_cast", () -> new PartCastItem(CastItem, MELTING_PAN_HEAD));
    public static final RegistryObject<ToolPartItem> MELTING_PAN_HANDLE = ITEMS.register("melting_pan_handle", () -> new ToolPartItem(ToolItem, LimbMaterialStats.ID));
    public static RegistryObject<Item> melting_pan_handle_cast = ITEMS.register("melting_pan_handle_cast", () -> new PartCastItem(CastItem, MELTING_PAN_HANDLE));
    public static RegistryObject<Item> melting_pan_handle_cast_fragment_1 = ITEMS.register("melting_pan_handle_cast_fragment_1", () -> new TooltipItem(CastItem));
    public static RegistryObject<Item> melting_pan_handle_cast_fragment_2 = ITEMS.register("melting_pan_handle_cast_fragment_2", () -> new TooltipItem(CastItem));
    public static RegistryObject<Item> melting_pan_handle_cast_incomplete = ITEMS.register("incomplete_melting_pan_handle_cast", () -> new TooltipItem(CastItem));

    public static Item register() {
        return new Item(new Item.Properties());
    }

    private static void addTabItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        output.accept(BATTLESIGN_HEAD.get());
        output.accept(BATTLESIGN_HANDLE.get());
        output.accept(WAR_PICK_HANDLE.get());
        output.accept(WAR_PICK_LIMB.get());
        output.accept(MELTING_PAN_HANDLE.get());
        output.accept(MELTING_PAN_HEAD.get());
        output.accept(battlesign_head_cast.get());
        output.accept(battlesign_handle_cast.get());
        output.accept(war_pick_handle_cast.get());
        output.accept(war_pick_limb_cast.get());
        output.accept(melting_pan_handle_cast.get());
        output.accept(melting_pan_head_cast.get());
        output.accept(melting_pan_handle_cast_incomplete.get());
        output.accept(melting_pan_handle_cast_fragment_1.get());
        output.accept(melting_pan_handle_cast_fragment_2.get());
    }
}