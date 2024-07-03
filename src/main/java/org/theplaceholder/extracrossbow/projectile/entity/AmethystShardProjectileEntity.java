package org.theplaceholder.extracrossbow.projectile.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AmethystShardProjectileEntity extends ThrownItemEntity {
    private ItemStack weaponStack;
    public AmethystShardProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AMETHYST_SHARD;
    }

    public AmethystShardProjectileEntity(LivingEntity owner, World world, ItemStack stack) {
        super(ProjectileEntityTypes.AMETHYST_SHARD, owner, world);
        this.weaponStack = stack;
    }
    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        this.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, 1.0F, 1.0F);
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            Vec3d vec3d = this.getVelocity();
            double e = vec3d.x;
            double f = vec3d.y;
            double g = vec3d.z;
            for(int i = 0; i < 4; ++i) {
                this.getWorld().addParticle(ParticleTypes.CRIT, this.getX() + e * (double)i / 4.0, this.getY() + f * (double)i / 4.0, this.getZ() + g * (double)i / 4.0, -e, -f + 0.2, -g);
            }
        }
    }
    @Override
    public ItemStack getWeaponStack() {
        return this.weaponStack;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("weapon", 10)) {
            this.weaponStack = ItemStack.fromNbt(this.getRegistryManager(), nbt.getCompound("weapon")).orElse(null);
        } else {
            this.weaponStack = null;
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.weaponStack != null) {
            nbt.put("weapon", this.weaponStack.encode(this.getRegistryManager(), new NbtCompound()));
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient()) {
            float d = 2.0f;
            DamageSource damageSource = this.getDamageSources().thrown(this, this.getOwner() != null ? this.getOwner() : this);
            if (this.getWeaponStack() != null){
                d = EnchantmentHelper.getDamage((ServerWorld) this.getWorld(), this.getWeaponStack(), entityHitResult.getEntity(), damageSource, d);
            }
            entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), d);
        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 3) {
            for(int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08);
            }
        }

    }
}
