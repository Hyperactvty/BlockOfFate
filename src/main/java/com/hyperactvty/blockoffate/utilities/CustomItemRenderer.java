package com.hyperactvty.blockoffate.utilities;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CustomItemRenderer extends ItemRenderer {
    private final ModItemModelResolver customResolver;

    public CustomItemRenderer(ModItemModelResolver resolver) {
        super(resolver);
        this.customResolver = resolver; // Use your custom resolver
    }

    @Override
    public void renderStatic(
            @Nullable LivingEntity entity,
            ItemStack stack,
            ItemDisplayContext context,
            boolean leftHanded,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            @Nullable Level level,
            int light,
            int overlay,
            int seed
    ) {
        // Prepare the render state
        ItemStackRenderState renderState = new ItemStackRenderState();
        this.customResolver.appendItemLayers(renderState, stack, context, level, entity, seed);

        // Render the resolved state
        renderState.render(poseStack, bufferSource, light, overlay);
    }
}
