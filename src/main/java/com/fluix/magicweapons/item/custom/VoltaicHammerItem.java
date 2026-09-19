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
 * A crackling warhammer that short-circuits its target with every heavy blow.
 */
public class VoltaicHammerItem extends Item {
	public VoltaicHammerItem(Properties properties) {
		super(properties);
	}

	@Override
	public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 3));
		target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
		target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60, 0));

		Level level = target.level();

		if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
					target.getX(), target.getY() + target.getBbHeight() * 0.5, target.getZ(),
					30, 0.4, 0.5, 0.4, 0.1);
		}

		super.hurtEnemy(stack, target, attacker);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
		textConsumer.accept(Component.translatable("item.fluixmagicweapons.voltaic_hammer.tooltip").withStyle(ChatFormatting.YELLOW));
	}
}
