package com.fluix.magicweapons.item;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import com.fluix.magicweapons.FluixMagicWeapons;
import com.fluix.magicweapons.item.custom.FrostbiteBladeItem;
import com.fluix.magicweapons.item.custom.InfernalReaverItem;
import com.fluix.magicweapons.item.custom.StormcallerTridentItem;
import com.fluix.magicweapons.item.custom.VoidReaperScytheItem;
import com.fluix.magicweapons.item.custom.VoltaicHammerItem;

public class ModItems {
	// A shared repair tag: any of our weapons can be repaired on an anvil with an Arcane Crystal.
	public static final TagKey<Item> REPAIRS_FLUIX_WEAPONS = TagKey.create(
			BuiltInRegistries.ITEM.key(), FluixMagicWeapons.id("repairs_fluix_weapons"));

	public static final ToolMaterial FROSTBITE_TOOL_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1200, 8.0F, 3.0F, 20, REPAIRS_FLUIX_WEAPONS);
	public static final ToolMaterial INFERNAL_TOOL_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1300, 8.0F, 3.5F, 20, REPAIRS_FLUIX_WEAPONS);
	public static final ToolMaterial VOLTAIC_TOOL_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1600, 6.0F, 4.5F, 15, REPAIRS_FLUIX_WEAPONS);
	public static final ToolMaterial VOID_REAPER_TOOL_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1100, 8.0F, 3.0F, 25, REPAIRS_FLUIX_WEAPONS);
	public static final ToolMaterial STORMCALLER_TOOL_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1400, 8.0F, 3.0F, 22, REPAIRS_FLUIX_WEAPONS);

	// The magic crafting ingredient shared by every weapon recipe.
	public static final Item ARCANE_CRYSTAL = register("arcane_crystal", Item::new, new Item.Properties());

	public static final Item FROSTBITE_BLADE = register(
			"frostbite_blade",
			FrostbiteBladeItem::new,
			new Item.Properties().sword(FROSTBITE_TOOL_MATERIAL, 4.0F, -2.4F)
	);

	public static final Item INFERNAL_REAVER = register(
			"infernal_reaver",
			InfernalReaverItem::new,
			new Item.Properties().sword(INFERNAL_TOOL_MATERIAL, 4.0F, -2.4F)
	);

	public static final Item VOLTAIC_HAMMER = register(
			"voltaic_hammer",
			VoltaicHammerItem::new,
			new Item.Properties().sword(VOLTAIC_TOOL_MATERIAL, 6.0F, -3.2F)
	);

	public static final Item VOID_REAPER_SCYTHE = register(
			"void_reaper_scythe",
			VoidReaperScytheItem::new,
			new Item.Properties().sword(VOID_REAPER_TOOL_MATERIAL, 4.0F, -2.0F)
	);

	public static final Item STORMCALLER_TRIDENT = register(
			"stormcaller_trident",
			StormcallerTridentItem::new,
			new Item.Properties().sword(STORMCALLER_TOOL_MATERIAL, 3.5F, -2.6F)
	);

	public static final ResourceKey<CreativeModeTab> CREATIVE_TAB_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(), FluixMagicWeapons.id("magic_weapons")
	);

	public static final CreativeModeTab CREATIVE_TAB = FabricItemGroup.builder()
			.icon(() -> new ItemStack(ModItems.VOID_REAPER_SCYTHE))
			.title(Component.translatable("itemGroup.fluixmagicweapons.magic_weapons"))
			.displayItems((params, output) -> {
				output.accept(ModItems.ARCANE_CRYSTAL);
				output.accept(ModItems.FROSTBITE_BLADE);
				output.accept(ModItems.INFERNAL_REAVER);
				output.accept(ModItems.VOLTAIC_HAMMER);
				output.accept(ModItems.VOID_REAPER_SCYTHE);
				output.accept(ModItems.STORMCALLER_TRIDENT);
			})
			.build();

	public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, FluixMagicWeapons.id(name));

		T item = itemFactory.apply(settings.setId(itemKey));

		Registry.register(BuiltInRegistries.ITEM, itemKey, item);

		return item;
	}

	public static void initialize() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, CREATIVE_TAB);

		// Also list our weapons in the vanilla Combat tab so they're easy to find.
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(itemGroup -> {
			itemGroup.accept(ModItems.FROSTBITE_BLADE);
			itemGroup.accept(ModItems.INFERNAL_REAVER);
			itemGroup.accept(ModItems.VOLTAIC_HAMMER);
			itemGroup.accept(ModItems.VOID_REAPER_SCYTHE);
			itemGroup.accept(ModItems.STORMCALLER_TRIDENT);
		});

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
				.register(itemGroup -> itemGroup.accept(ModItems.ARCANE_CRYSTAL));
	}
}
