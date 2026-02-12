package net.withrage.creepydecorations.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.withrage.creepydecorations.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final CreativeModeTab CREEPY_DECORATIONS_TAB = new CreativeModeTab("creepydecorations") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.HAUNTED_PUMPKIN.get());
        }
    };
}
