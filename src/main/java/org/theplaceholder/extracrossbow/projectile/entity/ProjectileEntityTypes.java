package org.theplaceholder.extracrossbow.projectile.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.theplaceholder.extracrossbow.ExtraCrossbow;

import java.util.HashMap;
import java.util.Map;

public class ProjectileEntityTypes {
    public static final Map<String, EntityType<? extends ProjectileEntity>> ENTITY_TYPES = new HashMap<>();

    public static final EntityType<EchoShardProjectileEntity> ECHO_SHARD = register("echo_shard", EchoShardProjectileEntity::new);
    public static final EntityType<AmethystShardProjectileEntity> AMETHYST_SHARD = register("amethyst_shard", AmethystShardProjectileEntity::new);

    public static <T extends ThrownItemEntity> EntityType<T> register(String name, EntityType.EntityFactory<T> factory) {
        EntityType<T> entityType = EntityType.Builder.create(factory, SpawnGroup.MISC).dimensions(0.5F, 0.5F).build(null);
        return register(name, entityType);
    }

    public static <T extends ThrownItemEntity> EntityType<T> register(String name, EntityType<T> entityType) {
        ENTITY_TYPES.put(name, entityType);
        return entityType;
    }

    public static void register() {
        for (Map.Entry<String, EntityType<? extends ProjectileEntity>> entry : ENTITY_TYPES.entrySet()) {
            Registry.register(Registries.ENTITY_TYPE, ExtraCrossbow.id(entry.getKey()), entry.getValue());
        }
    }
}
