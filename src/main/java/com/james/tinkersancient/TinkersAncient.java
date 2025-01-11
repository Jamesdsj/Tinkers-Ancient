package com.james.tinkersancient;

import com.james.tinkersancient.contents.FilePackResources;
import com.james.tinkersancient.contents.TinkersAncientEffects;
import com.james.tinkersancient.contents.TinkersAncientItems;
import com.james.tinkersancient.contents.TinkersAncientLootModifiers;
import com.james.tinkersancient.events.CastingTableInteractEvent;
import com.james.tinkersancient.events.PiglinBruteInteractEvent;
import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.resource.PathPackResources;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Random;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinkersAncient.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersAncient {
    public static Random RANDOM = new Random();
    public static Random RANDOM_2 = new Random();
    public static Random RANDOM_3 = new Random();
    // Define mod id in a common place for everything to reference
    public static final String MODID = "tinkersancient";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "tinkersancient" namespace
    public TinkersAncient() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        MinecraftForge.EVENT_BUS.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(new PiglinBruteInteractEvent());
        MinecraftForge.EVENT_BUS.register(new CastingTableInteractEvent());
        MinecraftForge.EVENT_BUS.register(this);
        TinkersAncientItems.ITEMS.register(bus);
        TinkersAncientEffects.MOB_EFFECTS.register(bus);
        TinkersAncientLootModifiers.init(bus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }
    @SubscribeEvent
    public static void addWarPickPack(AddPackFindersEvent event) {
        IModFileInfo modFileInfo = ModList.get().getModFileById(MODID);
        if (modFileInfo == null) {
            LOGGER.error("Could not find Create mod file info; built-in resource packs will be missing!");
            return;
        }
        IModFile modFile = modFileInfo.getFile();
        event.addRepositorySource(consumer -> {
            Pack pack = Pack.readMetaAndCreate(new ResourceLocation(MODID, "war_pick_fix").toString(), Component.literal("War Pick Fix"), true, id -> new FilePackResources(id, modFile, "war_pick_fix"), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
            if (pack != null) {
                consumer.accept(pack);
            }
        });
    }

}
