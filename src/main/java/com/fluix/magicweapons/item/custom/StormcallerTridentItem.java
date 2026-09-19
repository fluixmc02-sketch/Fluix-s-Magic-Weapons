package com.fluix.magicweapons.item.custom;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

/**
 * A storm-touched trident with a chance to call down a bolt of lightning on whatever it strikes.
 */
public class StormcallerTridentItem extends Item {
	private static final float LIGHTNING_CHANCE = 0.3F;

	public StormcallerTridentItem(Properties properties) {
		super(properties);
	}

	@Override
	public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 0));

		Level level = target.level();

		if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
			if (serverLevel.getRandom().nextFloat() < LIGHTNING_CHANCE) {
				LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, serverLevel);
				lightningBolt.setPos(target.position());
				serverLevel.addFreshEntity(lightningBolt);
			}
		}

		super.hurtEnemy(stack, target, attacker);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
		textConsumer.accept(Component.translatable("item.fluixmagicweapons.stormcaller_trident.tooltip").withStyle(ChatFormatting.BLUE));
	}
}
