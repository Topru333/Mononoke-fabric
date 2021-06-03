package com.toru.mononoke.common.registry.libs

import com.toru.mononoke.Global
import com.toru.mononoke.api.register.Lib
import com.toru.mononoke.common.enchantment.ShinigamiEnchantment
import net.minecraft.enchantment.Enchantment
import net.minecraft.util.registry.Registry

class LibEnchantments: Lib<Enchantment>(Global.MOD_ID, Registry.ENCHANTMENT) {

    init {
        this.resources = mapOf(
            "shinigami_enchantment" to SHINIGAMI_ENCHANTMENT
        )
    }

    companion object {
        val SHINIGAMI_ENCHANTMENT = ShinigamiEnchantment
    }
}