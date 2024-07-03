package org.theplaceholder.extracrossbow.projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.theplaceholder.extracrossbow.projectile.entity.EchoShardProjectileEntity;

public class EchoShardProjectile extends VanillaProjectile{
    public EchoShardProjectile() {
        super(Items.ECHO_SHARD);
    }

    @Override
    public <T extends ThrownItemEntity> T createProjectile(ItemStack stack, LivingEntity entity, World world) {
        return (T) new EchoShardProjectileEntity(entity, world);
    }

    @Override
    public @Nullable Identifier getIdentifier() {
        return null;
    }

    @Override
    public int getTexture() {
        return 1;
    }

    @Override
    public float getDrawTime() {
        return 2f;
    }
}
