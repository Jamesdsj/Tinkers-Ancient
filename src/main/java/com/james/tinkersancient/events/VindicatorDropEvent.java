package com.james.tinkersancient.events;

import com.james.tinkersancient.contents.TinkersAncientItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.tools.TinkerTools;

@Mod.EventBusSubscriber(modid = "tinkersancient",
        bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VindicatorDropEvent {
    @SubscribeEvent
    static void addDrop(LivingDropsEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Vindicator && event.getSource().getEntity() instanceof Player)
        {
            for(ItemEntity item : event.getDrops()) {
                if (item.getItem().is(TinkerTools.warPick.get()))
                    event.getDrops().add(entity.spawnAtLocation(TinkersAncientItems.war_pick_limb_cast.get()));
            }
        }
    }
}
