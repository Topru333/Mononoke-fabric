package com.mononoke.api.block

import net.minecraft.block.Material
import net.minecraft.sound.BlockSoundGroup

abstract class BaseStatueBlock : BaseMononokeFacingBlock(
    Settings.of(Material.STONE)
    .dropsNothing()
    .sounds(BlockSoundGroup.STONE)
    .requiresTool()
    .strength(-1f,-1f))