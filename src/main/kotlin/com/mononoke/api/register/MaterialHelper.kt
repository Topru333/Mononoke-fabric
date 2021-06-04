package com.mononoke.api.register

import com.mononoke.Global
import com.mononoke.common.itemgroup.BaseGroup
import com.mononoke.common.tooltypes.KatanaItem
import com.mononoke.common.tooltypes.MononokeSwordItem
import com.mononoke.common.tooltypes.TantoItem
import com.mononoke.common.tooltypes.WakizashiItem
import net.fabricmc.api.EnvType
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl
import net.fabricmc.fabric.impl.tool.attribute.handlers.ModdedToolsVanillaBlocksToolHandler
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.AxeItem
import net.minecraft.item.BlockItem
import net.minecraft.item.HoeItem
import net.minecraft.item.Item
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ShovelItem
import net.minecraft.item.ToolMaterial
import net.minecraft.tag.Tag
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class MaterialHelper(private val id: String, private val block: MaterialHelper.() -> Unit) {

    fun withItems(vararg variants: String): MaterialHelper {

        variants.forEach { variant ->
            val identifier = identifier("${id}_$variant")
            map[identifier] = { Registry.register(Registry.ITEM, identifier, Item(itemSettings())) }
        }
        return this
    }

    fun withItem(): MaterialHelper {
        Registry.register(Registry.ITEM, identifier(id), Item(itemSettings()))
        return this
    }

    fun withOre(supplier: (FabricBlockSettings) -> Block = { Block(it) }, miningLevel: Int): MaterialHelper {
        val ore = supplier(
            FabricBlockSettings.of(Material.STONE).requiresTool().breakByTool(FabricToolTags.PICKAXES, miningLevel)
                .strength(3f, 3f)
        )
        val identifier = identifier("${id}_ore")
        map[identifier] = {
            Registry.register(Registry.BLOCK, identifier, ore)
            Registry.register(Registry.ITEM, identifier, BlockItem(ore, itemSettings()))
        }
        if (FabricLoader.getInstance().environmentType == EnvType.CLIENT) {
            BlockRenderLayerMap.INSTANCE.putBlock(ore, RenderLayer.getCutout())
        }
        return this
    }

    fun withStandardTools(material: ToolMaterial) {
        object : PickaxeItem(material, 1, -2.8f, Settings().group(BaseGroup)) {}.run {
            mapping("pickaxe", this, FabricToolTags.PICKAXES)
        }

        object : AxeItem(material, 5f, -3f, Settings().group(BaseGroup)) {}.run {
            mapping("axe", this, FabricToolTags.AXES)
        }

        object : ShovelItem(material, 1.5f, -3.0f, Settings().group(BaseGroup)) {}.run {
            mapping("shovel", this, FabricToolTags.SHOVELS)
        }

        object : MononokeSwordItem(material, 3f, -2.4f, Settings().group(BaseGroup)) {}.run {
            mapping("sword", this, FabricToolTags.SWORDS)
        }

        object : HoeItem(material, -2, -1f, Settings().group(BaseGroup)) {}.run {
            mapping("hoe", this, FabricToolTags.HOES)
        }
    }

    fun withKatana(material: ToolMaterial) {
        object : KatanaItem(material, 2.5f, -2.1f, Settings().group(BaseGroup)) {}.run {
            mapping("katana", this, MononokeToolTags.KATANAS)
        }

        object : WakizashiItem(material,2f, -1.8f, Settings().group(BaseGroup)) {}.run {
            mapping("wakizashi", this, MononokeToolTags.WAKIZASHIS)
        }

        object : TantoItem(material,1.5f, -1.5f, Settings().group(BaseGroup)) {}.run {
            mapping("tanto", this, MononokeToolTags.TANTOS)
        }
    }

    fun withArmor(material: ArmorMaterial) {
        mapping("helmet", ArmorItem(material, EquipmentSlot.HEAD, itemSettings()))
        mapping("chestplate", ArmorItem(material, EquipmentSlot.CHEST, itemSettings()))
        mapping("leggings", ArmorItem(material, EquipmentSlot.LEGS, itemSettings()))
        mapping("boots", ArmorItem(material, EquipmentSlot.FEET, itemSettings()))
    }

    fun withBlock(): MaterialHelper {
        val block = Block(
            FabricBlockSettings.of(Material.METAL).requiresTool().breakByTool(FabricToolTags.PICKAXES, 2)
                .strength(5f, 6f)
        )
        val id = identifier("${id}_block")
        map[id] = {
            Registry.register(Registry.BLOCK, id, block)
            Registry.register(Registry.ITEM, id, BlockItem(block, itemSettings()))
        }
        if (FabricLoader.getInstance().environmentType == EnvType.CLIENT) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())
        }
        return this
    }

    fun register() = block()

    companion object {
        val map = HashMap<Identifier, () -> Unit>()
        val tool_map = mapOf<Tag<Item>, MutableList<Item>>(
            FabricToolTags.SHEARS to mutableListOf(),
            FabricToolTags.AXES to mutableListOf(),
            FabricToolTags.HOES to mutableListOf(),
            FabricToolTags.PICKAXES to mutableListOf(),
            FabricToolTags.SHOVELS to mutableListOf(),
            FabricToolTags.SWORDS to mutableListOf(),
            MononokeToolTags.KATANAS to mutableListOf(),
            MononokeToolTags.WAKIZASHIS to mutableListOf(),
            MononokeToolTags.TANTOS to mutableListOf()
        )

        fun register() {
            map.entries.sortedWith(
                compareBy<MutableMap.MutableEntry<Identifier, () -> Unit>> { id ->
                    id.key.path.contains("ore") && !id.key.path.contains(
                        "purified"
                    )
                }
                    .then(compareBy { id -> id.key.path.contains("block") })
                    .then(compareBy { id -> id.key.path.contains("raw") })
                    .then(compareBy { id -> id.key.path.contains("ingot") })
                    .then(compareBy { id -> id.key.path.contains("nugget") })
                    .then(compareBy { id -> id.key.path.contains("rod") })
                    .then(compareBy<MutableMap.MutableEntry<Identifier, () -> Unit>> { id ->
                        id.key.path.substring(
                            0,
                            id.key.path.indexOf("_")
                        )
                    }
                        .then(compareBy { id -> id.key.path.contains("sword") })
                        .then(compareBy { id -> id.key.path.contains("katana") })
                        .then(compareBy { id -> id.key.path.contains("wakizashi") })
                        .then(compareBy { id -> id.key.path.contains("tanto") })
                        .then(compareBy { id -> id.key.path.contains("pickaxe") })
                        .then(compareBy { id -> id.key.path.contains("axe") && !id.key.path.contains("pickaxe") })
                        .then(compareBy { id -> id.key.path.contains("shovel") })
                        .then(compareBy { id -> id.key.path.contains("hoe") })
                        .then(compareBy { id -> id.key.path.contains("helmet") })
                        .then(compareBy { id -> id.key.path.contains("chestplate") })
                        .then(compareBy { id -> id.key.path.contains("leggings") })
                        .then(compareBy { id -> id.key.path.contains("boots") })
                    )
            ).asReversed().forEach { it.value() }

            tool_map.forEach { (tag, list) -> ToolManagerImpl.tag(tag).register(ModdedToolsVanillaBlocksToolHandler(list)) }
        }
    }

    private fun identifier(id: String) = Identifier(Global.MOD_ID, id)

    private fun itemSettings(): Item.Settings = FabricItemSettings().group(BaseGroup)

    private fun mapping(type: String, item: Item, tag: Tag<Item>? = null) {
        map[identifier("${id}_$type")] = {
            Registry.register(
                Registry.ITEM,
                identifier("${id}_$type"),
                item
            )
        }
        tag?.let {
            tool_map[tag]!!.add(item)
        }
    }

}