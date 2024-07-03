package org.theplaceholder.extracrossbow.projectile.entity;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EchoShardProjectileEntity extends ThrownItemEntity {
    public EchoShardProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public EchoShardProjectileEntity(LivingEntity owner, World world) {
        super(ProjectileEntityTypes.ECHO_SHARD, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.ECHO_SHARD;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        if (!this.getWorld().isClient()){
            World world = this.getWorld();
            DamageSource magicExplosion = world.getDamageSources().explosion(this.getOwner(), this.getOwner());
            Vec3d pos = hitResult.getPos();
            world.getEntitiesByClass(LivingEntity.class,
                    new Box(pos.getX() - 3.5f, pos.getY(), pos.getZ() - 3.5f, pos.getX() + 3.5f, pos.getY() + 0.75f, pos.getZ() + 3.5f),
                    LivingEntity::isAlive).forEach((entity -> entity.damage(magicExplosion, 16))
            );

            AreaEffectCloudEntity areaEffectCloud = new AreaEffectCloudEntity(this.getWorld(), pos.getX(), pos.getY() + 0.25f, pos.getZ());
            areaEffectCloud.setOwner((LivingEntity) this.getOwner());
            areaEffectCloud.setParticleType(ParticleTypes.SONIC_BOOM);
            areaEffectCloud.setRadius(3.5f);
            areaEffectCloud.setDuration(0);
            this.getWorld().spawnEntity(areaEffectCloud);
            this.getWorld().playSound(this, this.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, this.getSoundCategory(), 1.0F, 1.0F);
            this.getWorld().createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 3.5f, World.ExplosionSourceType.MOB);

            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            this.getWorld().addParticle(ParticleTypes.SONIC_BOOM, false, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }
}
