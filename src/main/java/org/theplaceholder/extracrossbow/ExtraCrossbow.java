package org.theplaceholder.extracrossbow;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.theplaceholder.extracrossbow.projectile.ProjectileRegistry;
import org.theplaceholder.extracrossbow.projectile.entity.ProjectileEntityTypes;

public class ExtraCrossbow implements ModInitializer {
    public static final String MODID = "extra_crossbow";

    @Override
    public void onInitialize() {
        ProjectileRegistry.register();
        ProjectileEntityTypes.register();
    }

    public static Identifier id(String path) {
        return Identifier.of(MODID, path);
    }
}
