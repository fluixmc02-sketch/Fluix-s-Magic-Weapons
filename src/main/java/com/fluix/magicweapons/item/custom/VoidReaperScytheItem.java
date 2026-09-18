package com.fluix.magicweapons.item.custom;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

/**
 * A scythe that tears at the soul, withering the target and feeding the wielder its stolen life.
 */
public class VoidReaperScytheItem extends Item {
	private static final float LIFESTEAL_AMOUNT = 2.0F;

	public VoidReaperScytheItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0));
		attacker.heal(LIFESTEAL_AMOUNT);

		Level level = target.level();

		if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(ParticleTypes.SOUL,
					target.getX(), target.getY() + target.getBbHeight() * 0.5, target.getZ(),
					16, 0.3, 0.4, 0.3, 0.03);
		}

		return super.hurtEnemy(stack, target, attacker);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
		textConsumer.accept(Component.translatable("item.fluixmagicweapons.void_reaper_scythe.tooltip").withStyle(ChatFormatting.DARK_PURPLE));
	}
}
