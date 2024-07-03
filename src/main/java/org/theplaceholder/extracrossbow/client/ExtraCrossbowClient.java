package org.theplaceholder.extracrossbow.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.Items;
import org.theplaceholder.extracrossbow.ExtraCrossbow;
import org.theplaceholder.extracrossbow.projectile.ProjectileRegistry;
import org.theplaceholder.extracrossbow.projectile.entity.ProjectileEntityTypes;

public class ExtraCrossbowClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ProjectileEntityTypes.ECHO_SHARD, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ProjectileEntityTypes.AMETHYST_SHARD, FlyingItemEntityRenderer::new);

        ModelPredicateProviderRegistry.register(Items.CROSSBOW, ExtraCrossbow.id("projectile"), (UnClampedModelPredicateProvider) (stack, world, entity, seed) -> {
            ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
            if (!chargedProjectilesComponent.getProjectiles().isEmpty() && ProjectileRegistry.isProjectile(chargedProjectilesComponent.getProjectiles().getFirst().getItem())){
                return ProjectileRegistry.getProjectile(chargedProjectilesComponent.getProjectiles().getFirst().getItem()).getTexture();
            } else {
                return 0;
            }
        });
    }
}
