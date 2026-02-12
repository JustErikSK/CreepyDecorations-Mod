package net.withrage.creepydecorations.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.withrage.creepydecorations.CreepyDecorations;
import net.withrage.creepydecorations.item.ModCreativeModeTabs;
import net.withrage.creepydecorations.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CreepyDecorations.MOD_ID);

    public static final RegistryObject<Block> HAUNTED_PUMPKIN = registerBlock("haunted_pumpkin", () -> new HauntedPumpkin(BlockBehaviour.Properties.of(Material.VEGETABLE)
            .noOcclusion().strength(1.3f).sound(SoundType.STEM).lightLevel(state -> 10)));

    public static final RegistryObject<Block> BROKEN_DOLL = registerBlock("broken_doll", () -> new BrokenDoll(BlockBehaviour.Properties.of(Material.WOOL)
            .noOcclusion().strength(0.5f).sound(SoundType.WOOL)));

    public static final RegistryObject<Block> MOSSY_GRAVESTONE = registerBlock("mossy_gravestone", () -> new MossyGravestone(BlockBehaviour.Properties.of(Material.STONE)
            .noOcclusion().strength(2.2f).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> WOODEN_CROSS = registerBlock("wooden_cross", () -> new WoodenCross(BlockBehaviour.Properties.of(Material.WOOD)
            .noOcclusion().strength(1.8f).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> LEG_SHACKLE = registerBlock("leg_shackle", () -> new LegShackle(BlockBehaviour.Properties.of(Material.METAL)
            .noOcclusion().strength(3f).sound(SoundType.CHAIN)));

    public static final RegistryObject<Block> EMERGING_HAND = registerBlock("emerging_hand", () -> new EmergingHand(BlockBehaviour.Properties.of(Material.DIRT)
            .noOcclusion().strength(0.5f).sound(SoundType.MUD)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier) {
        RegistryObject<T> block = ModBlocks.BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(
                block.get(),
                new Item.Properties().tab(ModCreativeModeTabs.CREEPY_DECORATIONS_TAB)
        ));
        return block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
