//package com.hyperactvty.blockoffate.items;
//
//import com.hyperactvty.blockoffate.MainMod;
//import com.mojang.blaze3d.vertex.PoseStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.block.model.BakedQuad;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.resources.model.BakedModel;
//import net.minecraft.core.Direction;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.client.renderer.block.model.ItemTransforms;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraftforge.client.model.data.ModelData;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.List;
//
//public class KarmaMeterModel implements BakedModel {
//    private final BakedModel defaultModel;
//
//    public KarmaMeterModel(BakedModel defaultModel) {
//        this.defaultModel = defaultModel;
//    }
//
////    @Override
////    public BakedModel handlePerspective(ItemTransforms cameraTransformType, PoseStack poseStack) {
////        return defaultModel.handlePerspective(cameraTransformType, poseStack);
////    }
//
//    @Override
//    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand) {
//        // Custom logic to select the appropriate model based on karma
//        Minecraft minecraft = Minecraft.getInstance();
//        Player player = minecraft.player;
//
//        if (player != null) {
//            int karma = getPlayerKarma(player);
//
//            // Get model based on karma
//            ResourceLocation modelLocation;
//            if (karma <= 0) {
//                modelLocation = new ResourceLocation(MainMod.MODID, "item/karma_meter_00");
//            } else if (karma <= 10) {
//                modelLocation = new ResourceLocation(MainMod.MODID, "item/karma_meter_01");
//            } else if (karma <= 20) {
//                modelLocation = new ResourceLocation(MainMod.MODID, "item/karma_meter_02");
//            } else {
//                modelLocation = new ResourceLocation(MainMod.MODID, "item/karma_meter_default");
//            }
//
//            BakedModel model = Minecraft.getInstance().getModelManager().getModel(modelLocation);
//            return model.getQuads(state, side, rand);
//        }
//
//        // Fallback to the default model
//        return defaultModel.getQuads(state, side, rand);
//    }
//
//    // Other methods to override, delegating to the default model
//    @Override
//    public boolean useAmbientOcclusion() { return defaultModel.useAmbientOcclusion(); }
//    @Override
//    public boolean isGui3d() { return defaultModel.isGui3d(); }
//    @Override
//    public boolean usesBlockLight() { return defaultModel.usesBlockLight(); }
////    @Override
////    public boolean isCustomRenderer() { return defaultModel.isCustomRenderer(); }
//    @Override
//    public TextureAtlasSprite getParticleIcon() { return defaultModel.getParticleIcon(); }
//
//    @Override
//    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData data, @Nullable RenderType renderType) {
//        return BakedModel.super.getQuads(state, side, rand, data, renderType);
//    }
//}
