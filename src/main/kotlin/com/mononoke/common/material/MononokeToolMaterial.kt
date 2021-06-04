package com.mononoke.common.material

import com.mononoke.Global
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

enum class MononokeToolMaterial(
    private val _attackDamage: Float,
    private val _efficiency: Float,
    private val _durability: Int,
    private val _harvestLevel: Int,
    private val _enchantability: Int,
    private val _repairMaterial: Item
) : ToolMaterial {
    solurium(2.5f, 9f, 1561, 3, 18, Registry.ITEM.get(Identifier(Global.MOD_ID, "solurium_ingot"))),
    powered_solurium(6f, 11.0f, 2081, 4, 22, Registry.ITEM.get(Identifier(Global.MOD_ID, "solurium_ingot")));

    override fun getDurability(): Int = _durability

    override fun getMiningSpeedMultiplier(): Float = _efficiency

    override fun getAttackDamage(): Float = _attackDamage

    override fun getMiningLevel(): Int = _harvestLevel

    override fun getEnchantability(): Int = _enchantability

    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(_repairMaterial)
}