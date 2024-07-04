package org.theplaceholder.extracrossbow.projectile.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class GlowingAmethystShardProjectileEntity extends AmethystShardProjectileEntity {
    public GlowingAmethystShardProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public GlowingAmethystShardProjectileEntity(LivingEntity owner, World world, ItemStack stack) {
        super(ProjectileEntityTypes.GLOWING_AMETHYST_SHARD, owner, world, stack);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }
}
