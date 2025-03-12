package com.hyperactvty.blockoffate.data;

import com.google.gson.JsonObject;
import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.registry.BlockItems;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.properties.numeric.Time;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    protected final ItemModelOutput itemModelOutput;
    protected final BiConsumer<ResourceLocation, ModelInstance> modelOutput;

    public ModItemModelProvider(ItemModelOutput p_375677_, BiConsumer<ResourceLocation, ModelInstance> p_377569_) {
        super(p_375677_);
        this.itemModelOutput = p_375677_;
        this.modelOutput = p_377569_;
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output);
        this.itemModelOutput = itemModelOutput;

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

//    public void generateKarmaMeterItem(Item p_376265_) {
//        List<RangeSelectItemModel.Entry> list = new ArrayList<>();
//        ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.plainModel(this.createFlatItemModel(p_376265_, "_00", ModelTemplates.FLAT_ITEM));
//        list.add(ItemModelUtils.override(itemmodel$unbaked, 0.0F));
//
//        for (int i = 1; i < 32; i++) {
//            ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.plainModel(
//                    this.createFlatItemModel(p_376265_, String.format(Locale.ROOT, "_%02d", i), ModelTemplates.FLAT_ITEM)
//            );
//            list.add(ItemModelUtils.override(itemmodel$unbaked1, (float)i - 0.5F));
//        }
//
//        list.add(ItemModelUtils.override(itemmodel$unbaked, 31.5F/*63.5F*/));
//        this.itemModelOutput
//                .accept(
//                        p_376265_,
//                        ItemModelUtils.inOverworld(
//                                ItemModelUtils.rangeSelect(new Time(true, Time.TimeSource.DAYTIME), 64.0F, list),
//                                ItemModelUtils.rangeSelect(new Time(true, Time.TimeSource.RANDOM), 64.0F, list)
//                        )
//                );
//    }
//
//    protected ResourceLocation createFlatItemModel(Item p_376880_, String p_375748_, ModelTemplate p_375473_) {
//        return p_375473_.create(
//                ModelLocationUtils.getModelLocation(p_376880_, p_375748_), TextureMapping.layer0(TextureMapping.getItemTexture(p_376880_, p_375748_)), this.modelOutput
//        );
//    }
}