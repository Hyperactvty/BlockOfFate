package com.hyperactvty.blockoffate.utilities;

import com.hyperactvty.blockoffate.MainMod;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class ModItemModelResolver extends ItemModelResolver {

    private final Function<ResourceLocation, ItemModel> modelGetter;

    public ModItemModelResolver(ModelManager p_377509_) {
        super(p_377509_);
        this.modelGetter = p_377509_::getItemModel;

    }
/**
    @Override
    public void appendItemLayers(
            ItemStackRenderState renderState,
            ItemStack stack,
            ItemDisplayContext context,
            @Nullable Level level,
            @Nullable LivingEntity entity,
            int seed
    ) {

        ResourceLocation modelLocation = ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_default");//stack.get(DataComponents.ITEM_MODEL);

//        System.out.println("ItemStack Model Location: " + modelLocation);

        if (modelLocation != null) {
            this.modelGetter
                    .apply(modelLocation)
                    .update(renderState, stack, this, context,
                            level instanceof ClientLevel clientLevel ? clientLevel : null,
                            entity, seed);
        }

        System.out.println("appendItemLayers ARGS > " + renderState+" | "+stack+" | "+ this+" | "+ context+" | "+
                (level instanceof ClientLevel clientLevel ? clientLevel : null)+" | "+
                entity+" | "+seed);
        System.out.println("appendItemLayers > " + this.modelGetter);
    }
*/

//    public void appendItemLayers(
//            ItemStackRenderState renderState,
//            ItemStack stack,
//            ItemDisplayContext context,
//            @Nullable Level level,
//            @Nullable LivingEntity entity,
//            int seed
//    ) {
////        fixupSkullProfile(stack);
//        ResourceLocation modelLocation = stack.get(DataComponents.ITEM_MODEL);
//
//        // Debugging
//        System.out.println("ItemStack Model Location: " + modelLocation);
//
//        if (modelLocation != null) {
//            this.modelGetter
//                    .apply(modelLocation)
//                    .update(renderState, stack, this, context,
//                            level instanceof ClientLevel clientLevel ? clientLevel : null,
//                            entity, seed);
//        }
//    }

}
