package com.hyperactvty.blockoffate.gui;

import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
//import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.joml.Matrix4f;

public class ModRenderGUI extends Gui {
    private Component overlayMessageString;
    private int overlayMessageTime;
    private boolean animateOverlayMessageColor;
    private static final Minecraft minecraft = Minecraft.getInstance();

    public ModRenderGUI(Minecraft minecraft) {
        super(minecraft);
//        this.minecraft = minecraft;
    }

    // Call this method to display a message above the hotbar
    public void displayOverlayMessage(String message) {
        // Create a Component from the String message
        Component textComponent = Component.literal(message).withStyle(ChatFormatting.BOLD, ChatFormatting.YELLOW);
        this.overlayMessageTime = 100; // Display for longer than 3 seconds

        // Set the overlay message (this will show above the hotbar)
        this.setOverlayMessage(textComponent, false);
        this.setOverlayMessage(textComponent, true);
        this.setTitle(textComponent);
        System.err.println("displayOverlayMessage > " + textComponent.getString() + " | " + textComponent.getStyle());
    }

    public void renderOverlayText(PoseStack poseStack) {
        String message = "Karma Meter Active!";

        // Determine screen dimensions
        int screenWidth = this.minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = this.minecraft.getWindow().getGuiScaledHeight();

        // Position the text dynamically (centered horizontally, just above the hotbar)
        float x = screenWidth / 2.0f - this.minecraft.font.width(message) / 2.0f;
        float y = screenHeight - 50.0f; // Adjust Y-position as needed

        // Prepare to render
//        Matrix4f matrix4f = poseStack.last().pose();
//        MultiBufferSource.BufferSource bufferSource = MultiBufferSource.immediate(Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, VertexFormat.builder().build()));

        // Get Tesselator instance
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        ByteBufferBuilder bbb = new ByteBufferBuilder(256);
        // Use MultiBufferSource with immediate from BufferBuilder
        MultiBufferSource.BufferSource bufferSource = MultiBufferSource.immediate(bbb);

        // Use the matrix for rendering
        Matrix4f matrix4f = poseStack.last().pose();

        // Render the text
        this.minecraft.font.drawInBatch(
                Component.literal(message), // Text to render
                x, // X-position
                y, // Y-position
                0xFFFFFF, // White color
                false, // No shadow
                matrix4f, // Transformation matrix
                bufferSource, // Buffer source for rendering
                Font.DisplayMode.NORMAL, // Normal display mode
                0, // Background color (none in this case)
                15728880 // Light level (default for items)
        );

        // Finish rendering
        bufferSource.endBatch();
    }
}

//        @Override
//    public void setTitle(Component p_168715_) {
//        super.setTitle(p_168715_);
//    }

//    @Override
//    public void setOverlayMessage(Component p_93064_, boolean p_93065_) {
//        super.setOverlayMessage(p_93064_, p_93065_);
//        this.setChatDisabledByPlayerShown(false);
//        this.overlayMessageString = p_93064_;
//        this.overlayMessageTime = 60;
//        this.animateOverlayMessageColor = p_93065_;
//    }



// Event Listener for Custom GUI Rendering
//public class ModRenderGUI {
//    private static final Minecraft minecraft = Minecraft.getInstance();
//
//    // Subscribe to RenderGameOverlayEvent
//    @SubscribeEvent
//    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
//        // Only render during the HOTBAR phase
//        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
//            renderTextAboveHotbar(event.getMatrixStack());
//        }
//    }
//
//    // Method to draw text above the player's hotbar
//    private void renderTextAboveHotbar(PoseStack poseStack) {
//        String message = "Karma Meter Active!";
//        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
//        int screenHeight = minecraft.getWindow().getGuiScaledHeight();
//
//        // Position the text slightly above the hotbar (centered horizontally)
//        int x = screenWidth / 2 - minecraft.font.width(message) / 2;
//        int y = screenHeight - 50; // Adjust Y to position above the hotbar
//
//        // Draw the text
//        minecraft.font.draw(poseStack, message, x, y, 0xFFFFFF); // White color
//    }
//}
