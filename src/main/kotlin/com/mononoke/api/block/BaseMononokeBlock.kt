package com.mononoke.api.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.util.ActionResult
import net.minecraft.text.LiteralText
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.Hand
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class BaseMononokeBlock protected constructor(settings: Settings) : Block(settings) {

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult?
    ): ActionResult? {
        if (!world.isClient) {
            player.sendMessage(LiteralText("Hello, world!"), false)
        }
        return ActionResult.SUCCESS
    }

}