package org.theplaceholder.extracrossbow.projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.theplaceholder.extracrossbow.projectile.entity.AmethystShardProjectileEntity;

public class AmethystShardProjectile extends VanillaProjectile {

    public AmethystShardProjectile() {
        super(Items.AMETHYST_SHARD);
    }

    @Override
    public <T extends ThrownItemEntity> T createProjectile(ItemStack itemStack, LivingEntity entity, World world) {
        return (T) new AmethystShardProjectileEntity(entity, world, itemStack);
    }

    @Override
    public @Nullable Identifier getIdentifier() {
        return null;
    }

    @Override
    public int getTexture() {
        return 2;
    }

    @Override
    public float getDrawTime() {
        return 0.75f;
    }
}
