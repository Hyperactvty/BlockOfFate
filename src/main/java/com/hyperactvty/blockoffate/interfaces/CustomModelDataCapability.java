package com.hyperactvty.blockoffate.interfaces;

import net.minecraft.world.item.component.CustomModelData;

public class CustomModelDataCapability implements ICustomModelData {
    private CustomModelData customModelData = CustomModelData.EMPTY;

    @Override
    public CustomModelData getCustomModelData() {
        return customModelData;
    }

    @Override
    public void setCustomModelData(CustomModelData customModelData) {
        this.customModelData = customModelData;
    }
}
