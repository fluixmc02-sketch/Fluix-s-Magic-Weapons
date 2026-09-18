package com.fluix.magicweapons;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fluix.magicweapons.item.ModItems;

public class FluixMagicWeapons implements ModInitializer {
	public static final String MOD_ID = "fluixmagicweapons";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Igniting the forge - Fluix's Magic Weapons is loading!");

		ModItems.initialize();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
