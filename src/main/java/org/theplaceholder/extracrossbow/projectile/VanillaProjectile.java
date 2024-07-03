package org.theplaceholder.extracrossbow.projectile;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public abstract class VanillaProjectile implements Projectile {
    public static final Map<Item, Projectile> VANILLA_PROJECTILES = new HashMap<>();

    private final Item item;

    public VanillaProjectile(Item item) {
        this.item = item;
        VANILLA_PROJECTILES.put(item, this);
    }

    @Override
    public Item item() {
        return item;
    }
}
