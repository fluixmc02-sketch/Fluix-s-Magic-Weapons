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
 * A frost-forged blade that chills whatever it cuts, slowing enemies and fouling their reflexes.
 */
public class FrostbiteBladeItem extends Item {
	public FrostbiteBladeItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
		target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));

		Level level = target.level();

		if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
					target.getX(), target.getY() + target.getBbHeight() * 0.5, target.getZ(),
					24, 0.35, 0.45, 0.35, 0.02);
		}

		return super.hurtEnemy(stack, target, attacker);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
		textConsumer.accept(Component.translatable("item.fluixmagicweapons.frostbite_blade.tooltip").withStyle(ChatFormatting.AQUA));
	}
}
