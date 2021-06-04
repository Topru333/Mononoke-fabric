package com.mononoke.common.enchantment

import com.mononoke.Global
import com.mononoke.api.event.KillMobCallback
import com.mononoke.common.registry.libs.LibEnchantments.Companion.SHINIGAMI_ENCHANTMENT
import com.mononoke.common.registry.libs.LibItems.Companion.HITODAMA_ARTHROPOD_ITEM
import com.mononoke.common.registry.libs.LibItems.Companion.HITODAMA_ILLAGER_ITEM
import com.mononoke.common.registry.libs.LibItems.Companion.HITODAMA_ORDINARY_ITEM
import com.mononoke.common.registry.libs.LibItems.Companion.HITODAMA_UNDEAD_ITEM
import com.mononoke.common.registry.libs.LibItems.Companion.HITODAMA_WATER_ITEM
import net.minecraft.enchantment.DamageEnchantment
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.text.TranslatableText
import net.minecraft.util.ActionResult
import net.minecraft.util.Formatting
import net.minecraft.item.ItemStack
import net.minecraft.entity.player.PlayerEntity


object ShinigamiEnchantment : DamageEnchantment(Rarity.RARE, 1, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND) {

    init {
        KillMobCallback.EVENT.register(this::onMobKill)
    }

    private fun onMobKill(source : DamageSource, target: LivingEntity) : ActionResult {
        val player = source.attacker as PlayerEntity
        val mainHandStack = player.inventory.mainHandStack

        if (mainHandStack == ItemStack.EMPTY || EnchantmentHelper.getLevel(SHINIGAMI_ENCHANTMENT, mainHandStack) != maxLevel) {
            return ActionResult.PASS
        }

        when (target.group) {
            EntityGroup.UNDEAD -> target.dropItem(HITODAMA_UNDEAD_ITEM)
            EntityGroup.AQUATIC -> target.dropItem(HITODAMA_WATER_ITEM)
            EntityGroup.ARTHROPOD -> target.dropItem(HITODAMA_ARTHROPOD_ITEM)
            EntityGroup.ILLAGER -> target.dropItem(HITODAMA_ILLAGER_ITEM)
            EntityGroup.DEFAULT -> target.dropItem(HITODAMA_ORDINARY_ITEM)
            else -> return ActionResult.PASS
        }

        return ActionResult.PASS
    }

    override fun getAttackDamage(level: Int, group: EntityGroup): Float {
        return if (group === EntityGroup.UNDEAD) {
            0.coerceAtLeast(level).toFloat() * 1.5f
        } else {
            1.0f + 0.coerceAtLeast(level).toFloat() * 0.5f
        }
    }

    override fun getMaxLevel(): Int = 3

    override fun canAccept(other: Enchantment?): Boolean = true

    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (target is LivingEntity && level == 3) {
            target.addStatusEffect(StatusEffectInstance(StatusEffects.HUNGER, 20 * 4, 2))
            target.addStatusEffect(StatusEffectInstance(StatusEffects.WITHER, 20 * 4, 2))
        }
    }

    override fun getName(level: Int): Text {
        if (level == maxLevel) return TranslatableText("${getTranslationKey()}.max").apply {
            style = Style.EMPTY.withColor(
                TextColor.fromRgb(Global.Colors.Legendary)
            )
        }
        return TranslatableText(getTranslationKey()).apply {
            formatted(Formatting.GRAY)
            if (level != 1 || maxLevel != 1) {
                append(" ").append(TranslatableText("enchantment.level.$level") as Text)
            }
        }
    }

}