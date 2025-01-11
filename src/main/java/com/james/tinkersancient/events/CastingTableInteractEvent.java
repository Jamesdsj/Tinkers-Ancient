package com.james.tinkersancient.events;

import com.james.tinkersancient.contents.TinkersAncientItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.memory.ExpirableValue;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.mantle.block.entity.InventoryBlockEntity;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.Optional;

public class CastingTableInteractEvent {

    @SubscribeEvent
    public void onInteractTable(PlayerInteractEvent.RightClickBlock event) {
        ItemStack itemStack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
        BlockState state = event.getEntity().getCommandSenderWorld().getBlockState(event.getPos());
        BlockEntity blockEntity = event.getEntity().getCommandSenderWorld().getBlockEntity(event.getPos());
        if(state.getBlock() == TinkerSmeltery.searedTable.get() && blockEntity != null){
            if(((InventoryBlockEntity)blockEntity).getItem(0).is(TinkersAncientItems.melting_pan_handle_cast_fragment_1.get()) && itemStack.is(TinkersAncientItems.melting_pan_handle_cast_fragment_2.get()))
            {
                ((InventoryBlockEntity)blockEntity).setItem(0, new ItemStack(TinkersAncientItems.melting_pan_handle_cast_incomplete.get()));
                event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            }
            if(((InventoryBlockEntity)blockEntity).getItem(0).is(TinkersAncientItems.melting_pan_handle_cast_fragment_2.get()) && itemStack.is(TinkersAncientItems.melting_pan_handle_cast_fragment_1.get()))
            {
                ((InventoryBlockEntity)blockEntity).setItem(0, new ItemStack(TinkersAncientItems.melting_pan_handle_cast_incomplete.get()));
                event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            }
            if(((InventoryBlockEntity)blockEntity).getItem(0).is(TinkersAncientItems.melting_pan_handle_cast_incomplete.get()) && itemStack.isEmpty())
            {
                ((InventoryBlockEntity)blockEntity).setItem(0, new ItemStack(TinkersAncientItems.melting_pan_handle_cast_fragment_1.get()));
                event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(TinkersAncientItems.melting_pan_handle_cast_fragment_2.get()));
            }
        }
    }

}

