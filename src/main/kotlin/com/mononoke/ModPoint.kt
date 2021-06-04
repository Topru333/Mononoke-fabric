package com.mononoke

import com.mononoke.api.register.Lib
import com.mononoke.api.register.MaterialHelper
import com.mononoke.common.material.MononokeArmorMaterial
import com.mononoke.common.material.MononokeToolMaterial
import com.mononoke.common.registry.libs.LibBlocks
import com.mononoke.common.registry.libs.LibEnchantments
import com.mononoke.common.registry.libs.LibItems
import net.fabricmc.api.ModInitializer
import net.minecraft.block.OreBlock
import net.minecraft.item.ToolMaterials

class ModPoint : ModInitializer {

    override fun onInitialize() {
        listOf<Lib<*>>(
            LibBlocks(),
            LibItems(),
            LibEnchantments()
        ).forEach {
            it.register()
        }

        MaterialHelper("solurium") {
            withItems("ingot", "raw", "nugget", "rod")
            withBlock()
            withOre({ settings -> OreBlock(settings) }, 2)
            withArmor(MononokeArmorMaterial.solurium)
            withStandardTools(MononokeToolMaterial.solurium)
            withKatana(MononokeToolMaterial.solurium)
        }.register()

        MaterialHelper("diamond") {
            withKatana(ToolMaterials.DIAMOND)
        }.register()

        MaterialHelper("netherite") {
            withKatana(ToolMaterials.NETHERITE)
        }.register()

        MaterialHelper("iron") {
            withKatana(ToolMaterials.IRON)
        }.register()

        MaterialHelper("gold") {
            withKatana(ToolMaterials.GOLD)
        }.register()

        MaterialHelper("stone") {
            withKatana(ToolMaterials.STONE)
        }.register()

        MaterialHelper("wood") {
            withKatana(ToolMaterials.WOOD)
        }.register()

        MaterialHelper.register()

        println("Mononoke loaded!")
    }
}



