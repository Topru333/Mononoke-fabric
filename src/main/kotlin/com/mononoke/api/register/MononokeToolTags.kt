package com.mononoke.api.register

import com.mononoke.Global
import net.fabricmc.fabric.api.tag.TagRegistry
import net.minecraft.item.Item
import net.minecraft.tag.Tag
import net.minecraft.util.Identifier

class MononokeToolTags private constructor() {
    companion object {
        val KATANAS = register("katana")
        val WAKIZASHIS = register("wakizashis")
        val TANTOS = register("tantos")

        private fun register(id: String): Tag<Item> {
            return TagRegistry.item(Identifier(Global.MOD_ID, id))
        }
    }
}