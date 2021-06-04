package com.mononoke

//import com.toru.mononoke.client.provider.SoluriumModelProvider
import net.fabricmc.api.ClientModInitializer
//import net.fabricmc.fabric.impl.client.rendering.ArmorRenderingRegistryImpl
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class MononokeClient : ClientModInitializer {

    override fun onInitializeClient() {
        //ArmorRenderingRegistryImpl.registerModel(SoluriumModelProvider(),  listOf(Registry.ITEM.get(Identifier(Global.MOD_ID, "solurium_boots")), Registry.ITEM.get(Identifier(Global.MOD_ID, "solurium_chestplate")), Registry.ITEM.get(Identifier(Global.MOD_ID, "solurium_helmet")), Registry.ITEM.get(Identifier(Global.MOD_ID, "solurium_leggings"))))
    }

}