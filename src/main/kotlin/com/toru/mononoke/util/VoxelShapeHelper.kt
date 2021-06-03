package com.toru.mononoke.util

import net.minecraft.util.function.BooleanBiFunction
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.util.shape.VoxelShape

class VoxelShapeHelper {

    companion object {
        fun combineAll(vararg shapes: VoxelShape): VoxelShape {
            var result = VoxelShapes.empty()
            for (shape in shapes) {
                result = VoxelShapes.combineAndSimplify(result, shape, BooleanBiFunction.OR)
            }
            return result.simplify()
        }
    }


}