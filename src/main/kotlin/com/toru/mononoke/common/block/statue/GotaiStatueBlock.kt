package com.toru.mononoke.common.block.statue

import com.toru.mononoke.api.block.BaseStatueBlock
import com.toru.mononoke.util.VoxelShapeHelper
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

object GotaiStatueBlock : BaseStatueBlock() {

    private val shape: VoxelShape = VoxelShapeHelper.combineAll(
        createCuboidShape(4.0, 0.0, 4.0, 12.0, 9.0, 12.0),
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0)
    )

    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return shape
    }

}