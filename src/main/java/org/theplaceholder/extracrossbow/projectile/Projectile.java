package org.theplaceholder.extracrossbow.projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public interface Projectile {
    default Item item(){
        return (Item) this;
    }
    <T extends ThrownItemEntity> T createProjectile(ItemStack itemStack, LivingEntity entity, World world);
    @Nullable Identifier getIdentifier();
    int getTexture();
    float getDrawTime();
}
