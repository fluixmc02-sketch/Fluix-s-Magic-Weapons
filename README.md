# Fluix's Magic Weapons

A Fabric mod for Minecraft **1.21.11** that adds five magic weapons, each with
a unique crafting recipe and a unique on-hit special effect, plus a shared
"Arcane Crystal" crafting ingredient.

## Weapons

| Weapon | Effect on hit | Crafted from |
| --- | --- | --- |
| **Frostbite Blade** | Slowness II + Mining Fatigue (5s), snowflake particles | Arcane Crystal, Prismarine Shard, Stick |
| **Infernal Reaver** | Sets the target on fire for 8s, flame particles | Arcane Crystal, Blaze Rod, Stick |
| **Voltaic Hammer** | Slowness IV, Weakness, Glowing, electric spark particles | Arcane Crystal, Copper Ingot, Iron Ingot, Stick |
| **Void Reaper Scythe** | Wither I on target, heals the wielder 1 heart, soul particles | Arcane Crystal, Bone, Ghast Tear, Stick |
| **Stormcaller Trident** | Glowing, 30% chance to call down a lightning bolt | Arcane Crystal, Quartz, Stick |

**Arcane Crystal** (the shared magic ingredient) is crafted from 4 Amethyst
Shards + 1 Glowstone Dust (shapeless, yields 2).

All five weapons can be repaired on an anvil with an Arcane Crystal.

Recipes live in `src/main/resources/data/fluixmagicweapons/recipe/`.

## Project layout

- `src/main/java/com/fluix/magicweapons/FluixMagicWeapons.java` - mod entrypoint (`ModInitializer`)
- `src/main/java/com/fluix/magicweapons/item/ModItems.java` - item/tool-material registration, creative tab
- `src/main/java/com/fluix/magicweapons/item/custom/` - one `Item` subclass per weapon, each overriding `hurtEnemy` for its special effect
- `src/main/resources/fabric.mod.json` - mod metadata
- `src/main/resources/assets/fluixmagicweapons/` - textures, item models, and language file
- `src/main/resources/data/fluixmagicweapons/` - recipes and the repair-item tag

## Building

This project uses the standard [Fabric development toolchain](https://fabricmc.net/develop/)
(Loom + Mojang mappings) targeting Minecraft 1.21.11 / Fabric Loader 0.19.5 /
Fabric API 0.141.2. To build:

```sh
./gradlew build
```

The first run will download Minecraft, Mojang's official mappings, and
Fabric API from `maven.fabricmc.net` / `maven.minecraftforge.net` /
Mojang's servers, so it needs normal internet access and may take a few
minutes. The built jar will be in `build/libs/`.

> **Note:** this mod was developed in a sandboxed environment without
> outbound access to `maven.fabricmc.net`, so the Gradle wrapper jar
> could not be generated and the build could not be run/verified here.
> Run `gradle wrapper --gradle-version 8.14` once (or use a local Gradle
> install) to generate `gradlew`/`gradlew.bat` before building, then run
> `./gradlew build` as above. All source and resources were written by
> hand against the real 1.21.11 Fabric API/Mojang-mappings reference
> sources, but you should do a local build + in-game smoke test before
> relying on it.

## Running in a dev client

```sh
./gradlew runClient
```
