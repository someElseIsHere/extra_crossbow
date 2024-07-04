package org.theplaceholder.extracrossbow.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.theplaceholder.extracrossbow.effect.ECStatusEffects;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    @Inject(method = "renderOverlays", at = @At(value = "TAIL"))
    private static void renderOverlays(MinecraftClient client, MatrixStack matrices, CallbackInfo ci){
        if (client.player.hasStatusEffect(ECStatusEffects.DAZZLED)){
            StatusEffectInstance effect = client.player.getStatusEffect(ECStatusEffects.DAZZLED);
            float alpha = effect.getDuration();
            RenderSystem.clearColor(1f, 1f, 1f, alpha);
            System.out.println(alpha);
        }
    }
}
