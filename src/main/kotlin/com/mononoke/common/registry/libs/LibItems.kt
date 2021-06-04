package com.mononoke.common.registry.libs

import com.mononoke.Global
import com.mononoke.api.item.BaseMononokeBlockItem
import com.mononoke.api.item.BaseMononokeItem
import com.mononoke.api.register.Lib
import com.mononoke.common.item.Hitodama
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

class LibItems : Lib<Item>(Global.MOD_ID, Registry.ITEM) {

    init {
        this.resources = mapOf(
            "gotai_statue" to GOTAI_STATUE_ITEM,
            "pumpkin_shiryo" to PUMPKIN_SHIRYO_ITEM,
            "hitodama_ordinary" to HITODAMA_ORDINARY_ITEM,
            "hitodama_illager" to HITODAMA_ILLAGER_ITEM,
            "hitodama_arthropod" to HITODAMA_ARTHROPOD_ITEM,
            "hitodama_water" to HITODAMA_WATER_ITEM,
            "hitodama_undead" to HITODAMA_UNDEAD_ITEM,
        )
    }

    companion object {
        val GOTAI_STATUE_ITEM = BaseMononokeBlockItem(LibBlocks.GOTAI_STATUE)
        val PUMPKIN_SHIRYO_ITEM = BaseMononokeBlockItem(LibBlocks.PUMPKIN_SHIRYO)

        val HITODAMA_ORDINARY_ITEM = Hitodama()
        val HITODAMA_ILLAGER_ITEM = Hitodama()
        val HITODAMA_ARTHROPOD_ITEM = Hitodama()
        val HITODAMA_WATER_ITEM = Hitodama()
        val HITODAMA_UNDEAD_ITEM = Hitodama()
    }

}