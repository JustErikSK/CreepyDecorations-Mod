package net.withrage.creepydecorations.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WoodenCross extends Block {
    private static final VoxelShape SHAPE_NORTH = Block.box(1, 0, 10, 15, 16, 15);
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    public WoodenCross(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        Direction dir = state.getValue(FACING);
        return rotateShape(dir, SHAPE_NORTH);
    }

    private static VoxelShape rotateShape(Direction direction, VoxelShape shape) {
        int times = switch (direction) {
            case SOUTH -> 2;
            case WEST  -> 1;
            case EAST  -> 3;
            default    -> 0;
        };

        VoxelShape result = shape;
        for (int i = 0; i < times; i++) {
            VoxelShape[] buffer = new VoxelShape[]{Shapes.empty()};
            result.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
                buffer[0] = Shapes.or(
                        buffer[0],
                        Shapes.box(
                                1.0 - maxZ, minY, minX,
                                1.0 - minZ, maxY, maxX
                        )
                );
            });
            result = buffer[0];
        }
        return result;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
