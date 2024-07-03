package org.theplaceholder.extracrossbow.client;

import net.minecraft.client.item.ClampedModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface UnClampedModelPredicateProvider extends ClampedModelPredicateProvider {

    @Override
    default float call(ItemStack itemStack, @Nullable ClientWorld clientWorld, @Nullable LivingEntity livingEntity, int i) {
        return unclampedCall(itemStack, clientWorld, livingEntity, i);
    }
}
