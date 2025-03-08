package com.hyperactvty.blockoffate.data;

import com.google.gson.JsonObject;
import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.registry.BlockItems;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
//import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.BiConsumer;
//import net.minecraftforge.client.model.generators.ItemModelBuilder;


//public class ModItemModelProvider extends ItemModelGenerators {
public class ModItemModelProvider extends ModelProvider {
//    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
//        super(output, /*MainMod.MODID,*/ existingFileHelper);
//    }

//    public ModItemModelProvider(ItemModelOutput output, BiConsumer<ResourceLocation, ModelInstance> consumer) {
//        super(output, consumer);
//    }

    private final BiConsumer<ResourceLocation, ModelInstance> modelOutput;

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output);

        System.out.println("ModItemModelProvider Constructor called.");

        this.modelOutput = (resourceLocation, modelInstance) -> {
            try {
                System.out.println("ModItemModelProvider resourceLocation > "+resourceLocation);
                System.out.println("ModItemModelProvider resourceLocation > "+resourceLocation.getPath());
                System.out.println("ModItemModelProvider resourceLocation > "+resourceLocation.getNamespace());
//                System.out.println("ModItemModelProvider modelInstance > "+modelInstance);
                Path path = output.getOutputFolder().resolve("assets/" + resourceLocation.getNamespace() + "/models/" + resourceLocation.getPath() + ".json");

//                System.out.println("ModItemModelProvider output.getOutputFolder() > "+output.getOutputFolder());
//                System.out.println("ModItemModelProvider path.getParent() > "+path.getParent());
                Files.createDirectories(path.getParent()); // Ensures that the parent directories exist

                // Creates a BufferedWriter to write the file
                try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                    JsonObject jsonObject = getJsonObject(resourceLocation);
                    System.out.println("jsonObject > "+jsonObject);

                    // Writes the JSON to the file
                    writer.write(jsonObject.toString());
                }
                catch (Exception e) {
                    System.out.println("writer location ["+resourceLocation.getPath()+"] already exists! Skipping...");
//            throw new RuntimeException(e);
                }
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println("Exception > "+e);
            }
        };

        generate();
    }

    private static @NotNull JsonObject getJsonObject(ResourceLocation resourceLocation) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("parent", "item/generated");
//                    jsonObject.addProperty("textures", "layer0", resourceLocation.getNamespace() + ":item/" + resourceLocation.getPath());
        JsonObject textureProps = new JsonObject();
        textureProps.addProperty("layer0", String.valueOf(resourceLocation));
//                    jsonObject.addProperty("textures", String.valueOf(textureProps));
        jsonObject.add("textures", textureProps);
        return jsonObject;
    }

    void declareCustomModelItem() { // Maybe the right one?

    }

//    @Override
//    protected void registerModels() {
//        simpleItem(BlockItems.BoF_GENERIC_ITEM);
//        simpleItem(BlockItems.BoF_LUCKY_HAM_ITEM);
//
////        simpleItem(ModItems.METAL_DETECTOR);
////        simpleItem(ModItems.PINE_CONE);
////        simpleItem(ModItems.STRAWBERRY);
//    }

//    @Override
    protected void generate() {
        simpleFlatItem(BlockItems.BoF_GENERIC_ITEM, ModelTemplates.FLAT_ITEM);
        simpleCubeAll(BlockItems.BoF_SLAB_OF_HAM_BLOCK, ModelTemplates.CUBE_ALL);
        simpleFlatItem(BlockItems.BoF_LUCKY_HAM_ITEM, ModelTemplates.FLAT_ITEM);
    }

//    private void simpleFlatItem(RegistryObject<Item> item, ModelTemplate template) {
//        template.create(
//                item.getId(),   // ResourceLocation of the item
//                /*this.*/ItemModelOutput::accept  // Registers the model
//        );
//
//        generateFlatItem(item.get(), template);
//    }

    private void simpleCubeAll(RegistryObject<Block> block, ModelTemplate template) {
        template.create(
                block.get(), // The block itself
                TextureMapping.cube(block.getId()), // The texture mapping
                this.modelOutput // The model output consumer
        );

//        return ItemModelGenerators.generateFlatItem(item.getId().getPath(), ResourceLocation.parse("item/generated"))
//                .texture("layer0",ResourceLocation.parse(MainMod.MODID+":item/" + item.getId().getPath()));
    }

    private void simpleFlatItem(RegistryObject<Item> item, ModelTemplate template) {
        template.create(
                item.get(), // The item itself
                TextureMapping.layer0(item.getId()), // The texture mapping
                this.modelOutput // The model output consumer
        );

//        return ItemModelGenerators.generateFlatItem(item.getId().getPath(), ResourceLocation.parse("item/generated"))
//                .texture("layer0",ResourceLocation.parse(MainMod.MODID+":item/" + item.getId().getPath()));
    }

//    private ModelProvider simpleItem(RegistryObject<Item> item) {
//        return ItemModelGenerators.generateFlatItem(item.getId().getPath(), ResourceLocation.parse("item/generated"))
//                .texture("layer0",ResourceLocation.parse(MainMod.MODID+":item/" + item.getId().getPath()));
////        return ModelProvider.SimpleModelCollector.accept(param0, param1)
////                .texture("layer0",ResourceLocation.parse(MainMod.MODID+":item/" + item.getId().getPath()));
////        return withExistingParent(item.getId().getPath(),ResourceLocation.parse("item/generated"))
////                .texture("layer0",ResourceLocation.parse(MainMod.MODID+":item/" + item.getId().getPath()));
//    }
}