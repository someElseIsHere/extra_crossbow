package org.theplaceholder.extracrossbow.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import org.theplaceholder.extracrossbow.ExtraCrossbow;

public class ECStatusEffects {
    public static final RegistryEntry<StatusEffect> DAZZLED = register("dazzled", new StatusEffect(StatusEffectCategory.HARMFUL, 9740385));

    public static <T extends StatusEffect> RegistryEntry<T> register(String name, T statusEffect) {
        return (RegistryEntry<T>) Registry.registerReference(Registries.STATUS_EFFECT, ExtraCrossbow.id(name), statusEffect);
    }

    public static void init() {}
}
