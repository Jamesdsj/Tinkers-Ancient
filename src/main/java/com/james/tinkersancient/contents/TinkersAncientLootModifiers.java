
package com.james.tinkersancient.contents;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegistryObject;

public class TinkersAncientLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTRY;
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_LOOT_TABLE;

    public TinkersAncientLootModifiers() {
    }

    public static void init(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }

    static {
        REGISTRY = DeferredRegister.create(Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, "tinkersancient");
        ADD_LOOT_TABLE = REGISTRY.register("add_loot_table", LootTableModifiers.CODEC);
    }
}
