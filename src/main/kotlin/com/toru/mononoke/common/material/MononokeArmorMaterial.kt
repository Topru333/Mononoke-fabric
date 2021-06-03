package com.toru.mononoke.common.material

import com.toru.mononoke.Global
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.sound.SoundEvent
import net.minecraft.item.Item
import net.minecraft.sound.SoundEvents
import net.minecraft.recipe.Ingredient
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


enum class MononokeArmorMaterial(val settings: Settings) : ArmorMaterial {
    solurium(
        Settings(
            "solurium",
            39,
            arrayOf(4, 7, 9, 4),
            18,
            Registry.ITEM.get(Identifier(Global.MOD_ID, "solurium_ingot")),
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            2.0f,
            0.05f
        )
    ),
    powered_solurium(
        Settings(
            "powered_solurium",
            48,
            arrayOf(5, 8, 10, 5),
            18,
            Registry.ITEM.get(Identifier(Global.MOD_ID, "solurium_ingot")),
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            3.5f,
            0.05f
        )
    );

    private val maxDamageArray = intArrayOf(13, 15, 16, 11)

    override fun getDurability(slot: EquipmentSlot): Int = maxDamageArray[slot.entitySlotId] * settings.maxDamageFactor

    override fun getProtectionAmount(slot: EquipmentSlot): Int = settings.protectionAmount[slot.entitySlotId]

    override fun getEnchantability(): Int = settings.enchantability

    override fun getEquipSound(): SoundEvent = settings.equipSoundIn

    override fun getRepairIngredient(): Ingredient = settings.repairIngredientSupplier.value

    override fun getName(): String = name

    override fun getToughness(): Float = settings.toughness

    override fun getKnockbackResistance(): Float = settings.knockbackRes

    class Settings(
        val name: String,
        val maxDamageFactor: Int,
        val protectionAmount: Array<Int>,
        val enchantability: Int,
        repairItem: Item,
        val equipSoundIn: SoundEvent,
        val toughness: Float,
        val knockbackRes: Float
    ) {
        val repairIngredientSupplier : Lazy<Ingredient> = lazy { Ingredient.ofItems(repairItem) }
    }
}

