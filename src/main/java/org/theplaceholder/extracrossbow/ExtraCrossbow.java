package org.theplaceholder.extracrossbow;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.theplaceholder.extracrossbow.effect.ECStatusEffects;
import org.theplaceholder.extracrossbow.projectile.Projectiles;
import org.theplaceholder.extracrossbow.projectile.entity.ProjectileEntityTypes;

public class ExtraCrossbow implements ModInitializer {
    public static final String MODID = "extra_crossbow";

    @Override
    public void onInitialize() {
        Projectiles.register();
        ProjectileEntityTypes.init();
        ECStatusEffects.init();
    }

    public static Identifier id(String path) {
        return Identifier.of(MODID, path);
    }
}
