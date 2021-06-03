package com.toru.mononoke.common.tooltypes

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial

open class MononokeSwordItem(toolMaterial: ToolMaterial,
                        damage: Float,
                        attackSpeed: Float,
                        settings: Settings
) : SwordItem(
    toolMaterial,
    damage.toInt(),
    attackSpeed,
    settings
) {
    protected val swordDamage: Float = damage + toolMaterial.attackDamage
    protected val attributeModifiers: Multimap<EntityAttribute?, EntityAttributeModifier?>

    init {
        val builder = ImmutableMultimap.builder<EntityAttribute, EntityAttributeModifier>()
        builder.put(
            EntityAttributes.GENERIC_ATTACK_DAMAGE, EntityAttributeModifier(
                ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier",
                this.swordDamage.toDouble(), EntityAttributeModifier.Operation.ADDITION
            )
        )
        builder.put(
            EntityAttributes.GENERIC_ATTACK_SPEED, EntityAttributeModifier(
                ATTACK_SPEED_MODIFIER_ID, "Weapon modifier",
                attackSpeed.toDouble(), EntityAttributeModifier.Operation.ADDITION
            )
        )
        this.attributeModifiers = builder.build()
    }

    override fun getAttackDamage(): Float {
        return this.swordDamage
    }

    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<EntityAttribute?, EntityAttributeModifier?>? {
        return if (slot == EquipmentSlot.MAINHAND) this.attributeModifiers else super.getAttributeModifiers(slot)
    }
}