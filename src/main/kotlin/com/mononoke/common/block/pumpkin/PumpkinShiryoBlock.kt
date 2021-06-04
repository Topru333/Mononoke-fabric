package com.mononoke.common.block.pumpkin

import net.minecraft.block.CarvedPumpkinBlock
import net.minecraft.block.Material

object PumpkinShiryoBlock : CarvedPumpkinBlock(Settings
    .of(Material.WOOD)
    .strength(1f,1f)
    .luminance { 10 }
)