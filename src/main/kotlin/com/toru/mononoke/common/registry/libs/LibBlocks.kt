package com.toru.mononoke.common.registry.libs

import com.toru.mononoke.Global
import com.toru.mononoke.api.register.Lib
import com.toru.mononoke.common.block.pumpkin.PumpkinShiryoBlock
import com.toru.mononoke.common.block.statue.GotaiStatueBlock
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry

class LibBlocks : Lib<Block>(Global.MOD_ID, Registry.BLOCK) {

    init {
        this.resources = mapOf(
            "gotai_statue" to GOTAI_STATUE,
            "pumpkin_shiryo" to PUMPKIN_SHIRYO
        )
    }

    companion object {
        val GOTAI_STATUE = GotaiStatueBlock
        val PUMPKIN_SHIRYO = PumpkinShiryoBlock
    }

}

