package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.PinBuilder;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.PinType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemInputPin extends ResourceInputPin<ItemOutputPin> {
//    private static final int BUFFER_SIZE = 64;
//    private ItemStack buffer = ItemStack.EMPTY;

    public ItemInputPin(PinType<?> type, String id, String translationKey, int maxConnections) {
        super(type, id, translationKey, maxConnections);
    }

//    public ItemStack insertIntoOutputBuffer(ItemStack stack, boolean simulate) {
//        if (stack.isEmpty()) {
//            return ItemStack.EMPTY;
//        }
//
//        int m;
//        if (!this.buffer.isEmpty()) {
//            if (this.buffer.getCount() >= Math.min(this.buffer.getMaxStackSize(), BUFFER_SIZE))
//                return stack;
//
//            if (!ItemHandlerHelper.canItemStacksStack(stack, this.buffer))
//                return stack;
//
////            if (!getInv().canPlaceItem(slot, stack))
////                return stack;
//
//            m = Math.min(stack.getMaxStackSize(), BUFFER_SIZE) - this.buffer.getCount();
//
//            if (stack.getCount() <= m) {
//                if (!simulate) {
//                    ItemStack copy = stack.copy();
//                    copy.grow(this.buffer.getCount());
//                    this.buffer = copy;
//                }
//
//                return ItemStack.EMPTY;
//            } else {
//                // copy the stack to not modify the original one
//                stack = stack.copy();
//                if (!simulate) {
//                    ItemStack copy = stack.split(m);
//                    copy.grow(this.buffer.getCount());
//                    this.buffer = copy;
//                    return stack;
//                } else {
//                    stack.shrink(m);
//                    return stack;
//                }
//            }
//        } else {
////            if (!getInv().canPlaceItem(slot, stack))
////                return stack;
//
//            m = Math.min(stack.getMaxStackSize(), BUFFER_SIZE);
//            if (m < stack.getCount()) {
//                // copy the stack to not modify the original one
//                stack = stack.copy();
//                if (!simulate) {
//                    this.buffer = stack.split(m);
//                    return stack;
//                } else {
//                    stack.shrink(m);
//                    return stack;
//                }
//            } else {
//                if (!simulate) {
//                    this.buffer = stack;
//                }
//                return ItemStack.EMPTY;
//            }
//        }
//    }

    public static PinBuilder<ItemInputPin> create(PinType<?> pinType, String id, String translationKey, int maxConnections) {
        return new PinBuilder<>(pinType, id, translationKey, maxConnections) {
            @Override
            public ItemInputPin build() {
                return new ItemInputPin(this.type, this.id, this.translationKey, this.maxConnections);
            }
        };
    }
}
