package com.toru.mononoke.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface KillMobCallback {
    Event<KillMobCallback> EVENT = EventFactory.createArrayBacked(KillMobCallback.class,
            (listeners) -> (source, livingEntity) -> {
                for (KillMobCallback listener : listeners) {
                    ActionResult result = listener.interact(source, livingEntity);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(DamageSource source, LivingEntity livingEntity);
}
