package com.toru.mononoke.common.tooltypes

import net.minecraft.item.ToolMaterial

open class WakizashiItem(toolMaterial: ToolMaterial,
                         attackDamage: Float,
                         attackSpeed: Float,
                         settings: Settings
) : KatanaItem(
    toolMaterial,
    attackDamage,
    attackSpeed,
    settings
) {
    init {
        this.cobwebEfficiency = 17f
        this.plantEfficiency = 1.75f
    }
}