package com.hyperactvty.blockoffate.data;

import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.registry.BlockItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelGenerators {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MainMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(BlockItems.BoF_GENERIC_ITEM);
        simpleItem(BlockItems.BoF_LUCKY_HAM_ITEM);

//        simpleItem(ModItems.METAL_DETECTOR);
//        simpleItem(ModItems.PINE_CONE);
//        simpleItem(ModItems.STRAWBERRY);
    }

    private ModelProvider simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TutorialMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}