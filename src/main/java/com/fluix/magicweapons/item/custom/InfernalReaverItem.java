package com.fluix.magicweapons.item.custom;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

/**
 * A blade quenched in nether fire that sets enemies ablaze on every strike.
 */
public class InfernalReaverItem extends Item {
	private static final int FIRE_DURATION_TICKS = 8 * 20;

	public InfernalReaverItem(Properties properties) {
		super(properties);
	}

	@Override
	public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.setRemainingFireTicks(Math.max(target.getRemainingFireTicks(), FIRE_DURATION_TICKS));

		Level level = target.level();

		if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(ParticleTypes.FLAME,
					target.getX(), target.getY() + target.getBbHeight() * 0.5, target.getZ(),
					20, 0.3, 0.4, 0.3, 0.05);
		}

		super.hurtEnemy(stack, target, attacker);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
		textConsumer.accept(Component.translatable("item.fluixmagicweapons.infernal_reaver.tooltip").withStyle(ChatFormatting.RED));
	}
}
