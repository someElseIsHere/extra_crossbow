package org.theplaceholder.extracrossbow.projectile;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ProjectileRegistry {
    public static final List<Projectile> PROJECTILES = new ArrayList<>();

    public static final EchoShardProjectile ECHO_SHARD = register(new EchoShardProjectile());
    public static final AmethystShardProjectile AMETHYST_SHARD = register(new AmethystShardProjectile());

    public static <T extends Projectile> T register(T projectile) {
        PROJECTILES.add(projectile);
        return projectile;
    }

    public static void register() {
        for (Projectile projectile : PROJECTILES) {
            if (projectile instanceof Item item) {
                Registry.register(Registries.ITEM, projectile.getIdentifier(), item);
            }
        }
    }

    public static boolean isProjectile(Item item) {
        return item instanceof Projectile || VanillaProjectile.VANILLA_PROJECTILES.containsKey(item);
    }

    public static Projectile getProjectile(Item item) {
        if (item instanceof Projectile) {
            return (Projectile) item;
        } else {
            return VanillaProjectile.VANILLA_PROJECTILES.get(item);
        }
    }
}
