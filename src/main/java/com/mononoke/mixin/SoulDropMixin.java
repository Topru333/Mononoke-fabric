package com.mononoke.mixin;

import com.mononoke.api.event.KillMobCallback;
import com.mononoke.api.event.KillMobCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class SoulDropMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;dropLoot(Lnet/minecraft/entity/damage/DamageSource;Z)V"), method = "dropLoot", cancellable = true)
    private void dropLoot(DamageSource source, boolean causedByPlayer, CallbackInfo info) {
        if(!causedByPlayer|| source.getAttacker() == null) {
            return;
        }

        ActionResult result = KillMobCallback.EVENT.invoker().interact(source, (LivingEntity) (Object) this);

        if (result.equals(ActionResult.FAIL)) {
            info.cancel();
        }
    }
}
