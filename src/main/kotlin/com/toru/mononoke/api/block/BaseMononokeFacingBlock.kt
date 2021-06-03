package com.toru.mononoke.api.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.Direction

abstract class BaseMononokeFacingBlock protected constructor(settings: Settings) : HorizontalFacingBlock(settings) {

    init {
        defaultState = this.stateManager.defaultState.with(Properties.HORIZONTAL_FACING, Direction.NORTH)
    }

    override fun appendProperties(stateManager: StateManager.Builder<Block, BlockState>) {
        stateManager.add(Properties.HORIZONTAL_FACING)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        return defaultState.with(Properties.HORIZONTAL_FACING, ctx.playerFacing.opposite)
    }

}