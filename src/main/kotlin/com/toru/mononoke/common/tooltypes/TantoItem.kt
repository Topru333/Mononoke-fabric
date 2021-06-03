package com.toru.mononoke.common.tooltypes

import net.fabricmc.fabric.api.tool.attribute.v1.DynamicAttributeTool
import net.minecraft.item.ToolMaterial


open class TantoItem(toolMaterial: ToolMaterial,
                     attackDamage: Float,
                     attackSpeed: Float,
                     settings: Settings
) : KatanaItem(
    toolMaterial,
    attackDamage,
    attackSpeed,
    settings
), DynamicAttributeTool {
    init {
        this.cobwebEfficiency = 18f
        this.leavesEfficiency = 2f
        this.plantEfficiency = 2f
    }

}