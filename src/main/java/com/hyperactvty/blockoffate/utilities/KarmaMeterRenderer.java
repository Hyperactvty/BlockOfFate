package com.hyperactvty.blockoffate.utilities;

import com.hyperactvty.blockoffate.items.KarmaMeter_Item;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.MatrixUtil;
import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class KarmaMeterRenderer extends ItemRenderer {
    public KarmaMeterRenderer(ItemModelResolver p_377469_) {
        super(p_377469_);
//        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
//                Minecraft.getInstance().getEntityModels());
    }


    @Override
    public void renderStatic(ItemStack stack, ItemDisplayContext p_270648_, int p_270410_, int p_270894_, PoseStack poseStack, MultiBufferSource bufferSource, @Nullable Level p_270149_, int p_270509_) {
        super.renderStatic(stack, p_270648_, p_270410_, p_270894_, poseStack, bufferSource, p_270149_, p_270509_);

    }

//    @Override
//    public void renderItem(ItemStack stack, ItemTransforms.TransformType transformType,
//                             PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
//        // Retrieve the ResourceLocation dynamically
//        ResourceLocation texture = ((KarmaMeter_Item) stack.getItem()).getCustomModel(/*optional params*/);
//        BakedModel model = Minecraft.getInstance().getModelManager().getModel(texture);
//
//        // Render the model
//        Minecraft.getInstance().getItemRenderer().renderModelLists(model, stack, combinedLight, combinedOverlay, poseStack, bufferSource);
//    }
}
