package com.hyperactvty.blockoffate.interfaces;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IKarma {
    int getKarma(); // Get the current karma value
    void setKarma(int karma);
}
