package com.toru.mononoke.common.tooltypes

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.Material
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.tag.BlockTags

open class KatanaItem(toolMaterial: ToolMaterial,
                      katanaDamage: Float,
                      attackSpeed: Float,
                      settings: Settings) : MononokeSwordItem(
    toolMaterial,
    katanaDamage,
    attackSpeed,
    settings
) {

    protected var cobwebEfficiency: Float = 16.0f
    protected var leavesEfficiency: Float = 1.75f
    protected var plantEfficiency: Float = 1.5f

    override fun getMiningSpeedMultiplier(stack: ItemStack, state: BlockState): Float {
        return if (state.isOf(Blocks.COBWEB)) {
            cobwebEfficiency
        } else {
            if (state.isIn(BlockTags.LEAVES)) return leavesEfficiency
            return when ( state.material) {
                Material.PLANT, Material.REPLACEABLE_PLANT, Material.UNDERWATER_PLANT, Material.GOURD -> plantEfficiency
                else -> 1f
            }
        }
    }

    override fun isSuitableFor(state: BlockState): Boolean {
        return state.isOf(Blocks.COBWEB)
    }

}