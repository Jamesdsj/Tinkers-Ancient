package com.james.tinkersancient.events;

import com.google.common.collect.ImmutableList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.GoToWantedItem;
import net.minecraft.world.entity.ai.memory.ExpirableValue;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.Optional;

public class PiglinBruteInteractEvent {

    @SubscribeEvent
    public void onInteractBrute(PlayerInteractEvent.EntityInteract event) {
        ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
        if (event.getTarget() instanceof PiglinBrute piglin && canAdmire(piglin, itemstack)) {
            ItemStack itemStack;
            Player player = event.getEntity();
            if(player.isCreative())
            {
                itemStack = itemstack.copy();
                itemStack.setCount(1);
            }
            else {
                itemStack = itemstack.split(1);
            }
            holdInOffhand(piglin, itemStack);
            piglin.getBrain().getMemories().put(MemoryModuleType.ADMIRING_ITEM, Optional.of(ExpirableValue.of(true, 300L)));
            piglin.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
            piglin.getNavigation().stop();
            piglin.playSound(SoundEvents.PIGLIN_ADMIRING_ITEM);
            piglin.gameEvent(GameEvent.ENTITY_INTERACT);
            event.getEntity().swing(InteractionHand.MAIN_HAND);
            int i = 0;
            i += isGoldArmor(player, EquipmentSlot.HEAD);
            i += isGoldArmor(player, EquipmentSlot.CHEST);
            i += isGoldArmor(player, EquipmentSlot.LEGS);
            i += isGoldArmor(player, EquipmentSlot.FEET);
            event.getTarget().getPersistentData().putInt("golden_armor_count", i);
            event.setResult(Event.Result.ALLOW);
        }
        event.setResult(Event.Result.DENY);
    }

    private static boolean canAdmire(PiglinBrute p_34910_, ItemStack p_34911_) {
        return !isAdmiringItem(p_34910_) && p_34911_.is(Items.GOLD_BLOCK);
    }

    private static boolean isAdmiringItem(PiglinBrute p_35021_) {
        return p_35021_.getBrain().hasMemoryValue(MemoryModuleType.ADMIRING_ITEM);
    }

    private static void holdInOffhand(PiglinBrute p_34933_, ItemStack p_34934_) {

        p_34933_.setItemSlot(EquipmentSlot.OFFHAND, p_34934_);
        p_34933_.setGuaranteedDrop(EquipmentSlot.OFFHAND);
    }

    public static int isGoldArmor(Player player, EquipmentSlot slot)
    {
        if (player.getItemBySlot(slot).getItem() instanceof ArmorItem && ((ArmorItem)player.getItemBySlot(slot).getItem()).getMaterial() == ArmorMaterials.GOLD)
        {
            return 1;
        }
        else if(player.getItemBySlot(slot).getItem() instanceof IToolStackView tool && tool.getModifier(TinkerModifiers.golden.get()).getLevel() > 0)
        {
            return 1;
        }
        return 0;
    }
}

