package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.resource;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.ModPins;

public class ItemInputPin extends ResourceInputPin {
//    private static final int BUFFER_SIZE = 64;
//    private ItemStack buffer = ItemStack.EMPTY;

    public ItemInputPin(String id, String translationKey, int maxConnections) {
        super(ModPins.ITEM_INPUT.get(), id, translationKey, maxConnections);
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
}
