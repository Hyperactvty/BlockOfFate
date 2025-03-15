//package com.hyperactvty.blockoffate.gui;
//
//import com.hyperactvty.blockoffate.MainMod;
//import com.mojang.blaze3d.vertex.PoseStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.Gui;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.RenderGuiOverlayEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
///*
// * This class renders a simple overlay component.
// * It subscribes to the GUI overlay render event and draws text (with a background) on the screen.
// */
//@Mod.EventBusSubscriber(modid = MainMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
//public class ModOverlayRenderer {
//
//    @SubscribeEvent
//    public static void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
//        // We register our overlay above one of the vanilla overlays. In this example,
//        // we'll register it above the chat panel. You can choose a different anchor if you like.
//        event.registerAbove(VanillaGuiOverlay.CHAT_PANEL.id(), "my_overlay", ModGuiOverlayRegistrar::renderOverlay);
//    }
//
//    /**
//     * This method is our overlay renderer.
//     *
//     * @param gui           The GUI instance (not used in this simple example)
//     * @param poseStack     The PoseStack used for rendering (like a MatrixStack)
//     * @param partialTick   The partial tick time
//     * @param screenWidth   The current screen width in GUI-scale
//     * @param screenHeight  The current screen height in GUI-scale
//     */
//    private static void renderOverlay(PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
//        Minecraft mc = Minecraft.getInstance();
//        String text = "Hello, Custom GUI Component!";
//        int textWidth = mc.font.width(text);
//        // Position the text in the center of the screen
//        int x = (screenWidth - textWidth) / 2;
//        int y = screenHeight / 2;
//
//        // Optionally, you might want a background for better readability.
//        int padding = 2;
//        int backgroundColor = 0xAA000000;  // ARGB: semi-transparent black
//
//        // Draw a rectangle behind the text (using the Gui helper method);
//        // Note that Gui.fill is available in modern mappings.
//        net.minecraft.client.gui.Gui.fill(poseStack, x - padding, y - padding, x + textWidth + padding, y + mc.font.lineHeight + padding, backgroundColor);
//
//        // Now draw the text on top in white.
//        mc.font.draw(poseStack, text, x, y, 0xFFFFFF);
//    }
//}
