//package com.hyperactvty.blockoffate.mixin;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.Gui;
//import net.minecraft.client.gui.hud.InGameHud;
//import net.minecraft.client.gui.screens.InGameHud;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(InGameHud.class)
//public class InGameHudMixin {
//
//    /**
//     * Inject our overlay-rendering code at the end (TAIL) of InGameHud.render.
//     */
//    @Inject(method = "render", at = @At("TAIL"))
//    private void renderCustomOverlay(PoseStack poseStack, float tickDelta, CallbackInfo ci) {
//        Minecraft mc = Minecraft.getInstance();
//
//        // The text to display
//        String message = "Hello, Custom GUI Component!";
//
//        // Get screen dimensions from the current window settings.
//        int screenWidth = mc.getWindow().getGuiScaledWidth();
//        int screenHeight = mc.getWindow().getGuiScaledHeight();
//
//        // Calculate text width for centering.
//        int textWidth = mc.font.width(message);
//        int x = (screenWidth - textWidth) / 2;
//        int y = screenHeight / 2; // Adjust to change vertical position
//
//        // Optionally draw a background rectangle for better readability.
//        int padding = 2;
//        int backgroundColor = 0xAA000000; // ARGB: semi-transparent black
//        Gui.fill(poseStack, x - padding, y - padding, x + textWidth + padding, y + mc.font.lineHeight + padding, backgroundColor);
//
//        // Draw the text in white.
//        mc.font.draw(poseStack, message, x, y, 0xFFFFFF);
//    }
//}
