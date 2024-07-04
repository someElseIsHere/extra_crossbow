package org.theplaceholder.extracrossbow.projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.theplaceholder.extracrossbow.ExtraCrossbow;
import org.theplaceholder.extracrossbow.projectile.entity.GlowingAmethystShardProjectileEntity;

public class GlowingAmethystShardProjectile extends Item implements Projectile {
    public GlowingAmethystShardProjectile() {
        super(new Settings());
    }

    @Override
    public <T extends ThrownItemEntity> T createProjectile(ItemStack itemStack, LivingEntity entity, World world) {
        return (T) new GlowingAmethystShardProjectileEntity(entity, world, itemStack);
    }

    @Override
    public @Nullable Identifier getIdentifier() {
        return ExtraCrossbow.id("glowing_amethyst_shard");
    }

    @Override
    public int getTexture() {
        return 3;
    }

    @Override
    public float getDrawTime() {
        return 0;
    }
}
