package org.theplaceholder.extracrossbow.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.theplaceholder.extracrossbow.projectile.Projectiles;

import java.util.function.Predicate;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin extends RangedWeaponItem {
    private CrossbowItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "getProjectiles", at = @At("HEAD"), cancellable = true)
    private void getProjectiles(CallbackInfoReturnable<Predicate<ItemStack>> cir) {
        cir.setReturnValue((stack) -> BOW_PROJECTILES.test(stack) || Projectiles.isProjectile(stack.getItem()));
    }

    @Inject(method = "createArrowEntity", at = @At("HEAD"), cancellable = true)
    private void createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical, CallbackInfoReturnable<ProjectileEntity> cir) {
        if (Projectiles.isProjectile(projectileStack.getItem())) {
            cir.setReturnValue(Projectiles.getProjectile(projectileStack.getItem()).createProjectile(weaponStack, shooter, world));
        }
    }

    @Inject(method = "getPullTime", at = @At("HEAD"), cancellable = true)
    private static void getPullTime(ItemStack crossbow, LivingEntity user, CallbackInfoReturnable<Integer> cir) {
        ItemStack projectileStack = user.getProjectileType(crossbow);
        if (Projectiles.isProjectile(projectileStack.getItem())) {
            float f = Projectiles.getProjectile(projectileStack.getItem()).getDrawTime();
            f = EnchantmentHelper.getCrossbowChargeTime(crossbow, user, f);
            cir.setReturnValue(MathHelper.floor(f * 20.0F));
        }
    }
}