package com.hyperactvty.blockoffate.data;

import com.hyperactvty.blockoffate.AdvancementsProvider;
import com.hyperactvty.blockoffate.MLootTableProvider;
import com.hyperactvty.blockoffate.data.loot.ModBlockLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

import static com.hyperactvty.blockoffate.MainMod.MODID;

// Your mod's ID should be replaced in the @Mod annotation
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        System.err.println("[ModDataGenerator.java] C: `gatherData`");
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

        // Add our custom advancements provider
        generator.addProvider(event.includeServer(), new AdvancementsProvider(output, registries, existingFileHelper));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(output, registries));
        generator.addProvider(event.includeServer(), new ModItemModelProvider(output, existingFileHelper));
//        generator.addProvider(event.includeServer(), new ModLootTableProvider(output, registries, existingFileHelper));
//        generator.addProvider(event.includeServer(), new MLootTableProvider(output, registries, existingFileHelper));
    }
}