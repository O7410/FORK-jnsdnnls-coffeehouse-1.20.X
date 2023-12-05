package net.jdonthatrack.coffeehouse.world;

import net.jdonthatrack.coffeehouse.CoffeeHouse;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

import static net.jdonthatrack.coffeehouse.world.ModOrePlacement.modifiersWithCount;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> UNDEFINIUM_ORE_PLACED_KEY = registerKey("ruby_ore_placed");
    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, UNDEFINIUM_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.UNDEFINIUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(6, // Veins per Chunk
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(CoffeeHouse.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
