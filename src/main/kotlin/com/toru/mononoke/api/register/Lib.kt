package com.toru.mononoke.api.register

import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

abstract class Lib<T>(private val modid: String, private val registryType: Registry<T>) {

    lateinit var resources : Map<String, T>

    fun register() {
        registerResources(resources)
    }

    private fun registerResources(registries: Map<String, T>) {
        registries.forEach {
            Registry.register(
                registryType,
                Identifier(modid, it.key),
                it.value
            )
        }
    }
}




