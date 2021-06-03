package com.toru.mononoke.client.model

import java.util.Collections
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.model.Dilation
import net.minecraft.client.model.ModelPart
import net.minecraft.client.model.ModelPartBuilder
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.client.util.math.MatrixStack

@Environment(EnvType.CLIENT)
class SoluriumModelArmor(private val slot: EquipmentSlot) : BipedEntityModel<LivingEntity>(getRoot(slot)) {

    override fun render(
        ms: MatrixStack,
        buffer: VertexConsumer,
        light: Int,
        overlay: Int,
        r: Float,
        g: Float,
        b: Float,
        a: Float
    ) {
        this.head.visible = slot == EquipmentSlot.HEAD
        this.hat.visible = slot == EquipmentSlot.HEAD
        this.body.visible = slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS
        this.leftArm.visible = slot == EquipmentSlot.CHEST
        this.rightArm.visible = slot == EquipmentSlot.CHEST
        this.rightLeg.visible = slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET
        this.leftLeg.visible = slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET
        super.render(ms, buffer, light, overlay, r, g, b, a)
    }

    companion object {
        fun getRoot(slot: EquipmentSlot): ModelPart {
            val rootChildren = mutableMapOf<String, ModelPart>()

            val textureWidth = 128
            val textureHeight = 128

            // helm
            val helmCuboids = ModelPartBuilder().apply {
                mirrored(false).uv(30, 48).cuboid(-4.0f, -8.01f, -4.99f, 8f, 8f, 9f, Dilation(0.01f))
                mirrored(true).uv(0, 48).cuboid(4.0f, -2.0f, -5.0f, 2f, 2f, 10f, Dilation.NONE)
                mirrored(false).uv(0, 48).cuboid(-6.0f, -2.0f, -5.0f, 2f, 2f, 10f, Dilation.NONE)
                mirrored(false).uv(46, 65).cuboid( -4.0f, -2.0f, 4.0f, 8f, 2f, 1f, Dilation.NONE)
                mirrored(false).uv(35, 66).cuboid( 4.0f, -7.0f, -5.0f, 1f, 5f, 9f, Dilation.NONE)
                mirrored(true).uv(35, 66).cuboid( -5.0f, -7.0f, -5.0f, 1f, 5f, 9f, Dilation.NONE)
                mirrored(false).uv(25, 48).cuboid(-3.0f, -5.0f, -5.0f, 6f, 5f, 1f)
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }

            val emblemCuboids = ModelPartBuilder().apply {
                mirrored(false).uv(56, 51).cuboid(-1.0f, -9.0f, -6.0f, 2.0f, 2.0f, 1.0f, Dilation.NONE)
                mirrored(true).uv(56, 54).cuboid(-3.0f, -10.0f, -6.0f, 2.0f, 2.0f, 1.0f, Dilation.NONE)
                mirrored(false).uv(56, 54).cuboid(1.0f, -10.0f, -6.0f, 2.0f, 2.0f, 1.0f, Dilation.NONE)
                mirrored(false).uv(56, 54).cuboid(-4.0f, -12.0f, -6.0f, 2.0f, 2.0f, 1.0f, Dilation.NONE)
                mirrored(true).uv(56, 54).cuboid(2.0f, -12.0f, -6.0f, 2.0f, 2.0f, 1.0f, Dilation.NONE)
                mirrored(false).uv(56, 54).cuboid(-3.0f, -14.0f, -6.0f, 2.0f, 2.0f, 1.0f, Dilation.NONE)
                mirrored(true).uv(56, 54).cuboid(1.0f, -14.0f, -6.0f, 2.0f, 2.0f, 1.0f, Dilation.NONE)
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }

            val emblem = ModelPart(emblemCuboids, Collections.emptyMap()).apply { setPivot(0.0f, 1.0f, 0.0f) }
            val _hat = ModelPart(helmCuboids, mapOf("emblem" to emblem))


            // headwear
            val headwearCuboids = ModelPartBuilder().apply {
                mirrored(false).uv(0, 63).cuboid(-5.0f, -8.0f, -5.0f, 10.0f, 8.0f, 9.0f, Dilation(0.5f))
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }
            val _head = ModelPart(headwearCuboids, mapOf()).apply { setPivot(0.0f, 0.0f, 0.0f) }


            // chestplate
            val chestplateCuboids = ModelPartBuilder().apply {
                mirrored(false).uv(32, 29).cuboid(-5.0f, -1.0f, -3.0f, 10.0f, 13.0f, 6.0f, Dilation.NONE)
                mirrored(false).uv(26, 16).cuboid(-3.5f, 0.7f, -1.5f, 7.0f, 6.0f, 3.0f, Dilation(1f))
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }
            val _body = ModelPart(chestplateCuboids, mapOf()).apply { setPivot(0.0f, 0.0f, 0.0f) }


            // left arm
            val leftArmCuboids = ModelPartBuilder().apply {
                mirrored(false).uv(12, 31).cuboid(-1.5f, -2.0f, -2.5f, 5.0f, 12.0f, 5.0f, Dilation(0.065f))
                mirrored(false).uv(1, 20).cuboid(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, Dilation(0.05f))
                mirrored(false).uv(48, 23).cuboid(1.5f, -3.0f, -2.5f, 3.0f, 1.0f, 5.0f, Dilation.NONE)
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }
            val _leftArm =  ModelPart(leftArmCuboids, mapOf()).apply { setPivot(5.0f, 2.0f, 0.0f) }


            // right arm
            val rightArmCuboids = ModelPartBuilder().apply {
                mirrored(true).uv(48, 23).cuboid(-4.5f, -3.0f, -2.5f, 3.0f, 1.0f, 5.0f, Dilation.NONE)
                mirrored(true).uv(12, 31).cuboid(-3.5f, -2.0f, -2.5f, 5.0f, 12.0f, 5.0f, Dilation(0.065f))
                mirrored(true).uv(1, 20).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, Dilation(0.05f))
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }
            val _rightArm =  ModelPart(rightArmCuboids, mapOf()).apply { setPivot(-5.0f, 2.0f, 0.0f) }


            // legs
            val leftLegCuboids = ModelPartBuilder().apply {
                mirrored(false).uv(64, 0).cuboid(-2.15f, 0.0f, -2.5f, 5.0f, 12.0f, 5.0f, Dilation(0.01f))
                mirrored(true).uv(112, 0).cuboid(-1.9f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, Dilation(0.2f))
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }
            val _leftLeg =  ModelPart(leftLegCuboids, mapOf()).apply { setPivot(1.9f, 12.0f, 0.0f) }

            val rightLegCuboids = ModelPartBuilder().apply {
                mirrored(true).uv(64, 0).cuboid(-2.85f, 0.0f, -2.5f, 5.0f, 12.0f, 5.0f, Dilation(0.01f))
                mirrored(true).uv(112, 0).cuboid(-2.1f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, Dilation(0.2f))
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }
            val _rightLeg =  ModelPart(rightLegCuboids, mapOf()).apply { setPivot(-1.9f, 12.0f, 0.0f) }

            val corsetCuboids = ModelPartBuilder().apply {
                mirrored(false).uv(95, 18).cuboid(-5.0f, 7.0f, -3.0f, 10.0f, 8.0f, 6.0f, Dilation(-0.01f))
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }
            val _pants =  ModelPart(corsetCuboids, mapOf()).apply { setPivot(0.0f, 0.0f, 0.0f) }

            val rightFeetCuboids = ModelPartBuilder().apply {
                mirrored(true).uv(0, 0).cuboid(-3.07f, 6.0f, -2.5f, 5.0f, 6.0f, 5.0f, Dilation(-0.03f))
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }
            val _rightFeet = ModelPart(rightFeetCuboids, mapOf()).apply { setPivot(-1.9f, 12.0f, 0.0f) }

            val leftFeetCuboids = ModelPartBuilder().apply {
                mirrored(false).uv(0, 0).cuboid(-1.93f, 6.0f, -2.5f, 5.0f, 6.0f, 5.0f, Dilation(-0.03f))
            }.build().map {
                it.createCuboid(textureWidth,textureHeight)
            }
            val _leftFeet = ModelPart(leftFeetCuboids, mapOf()).apply { setPivot(1.9f, 12.0f, 0.0f) }

            rootChildren["head"] = _head
            rootChildren["hat"] = _hat
            rootChildren["body"] = _body
            rootChildren["right_arm"] = _rightArm
            rootChildren["left_arm"] = _leftArm

            if (slot == EquipmentSlot.LEGS) {
                rootChildren["body"] = _pants
                rootChildren["right_leg"] = _rightLeg
                rootChildren["left_leg"] = _leftLeg
            } else {
                rootChildren["right_leg"] = _rightFeet
                rootChildren["left_leg"] = _leftFeet
            }

            return ModelPart(listOf(), rootChildren)
        }
    }
}

