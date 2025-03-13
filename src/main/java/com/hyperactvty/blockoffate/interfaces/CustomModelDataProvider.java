package com.hyperactvty.blockoffate.interfaces;


import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CustomModelDataProvider implements ICapabilityProvider {
    public static final Capability<ICustomModelData> CUSTOM_MODEL_DATA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    private final ICustomModelData instance = new CustomModelDataCapability();
    private final LazyOptional<ICustomModelData> lazyOptional = LazyOptional.of(() -> instance);

//    @Override
//    public <T> @Nullable T getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
//        return cap == CUSTOM_MODEL_DATA_CAPABILITY ? CUSTOM_MODEL_DATA_CAPABILITY.cast(instance) : null;
//    }

//    @Override
//    public <T> @NotNull Optional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
//        if (cap == CustomModelDataProvider.CUSTOM_MODEL_DATA_CAPABILITY) {
//            return Optional.of((T) instance); // Cast the instance to the generic type
//        }
//        return Optional.empty(); // Return an empty Optional if the capability is not matched
//    }



    // Save and load NBT data for persistence
    public void readNBT(CompoundTag nbt) {
        // Deserialize your CustomModelData here
    }

    public CompoundTag writeNBT() {
        // Serialize your CustomModelData here
        return new CompoundTag();
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CustomModelDataProvider.CUSTOM_MODEL_DATA_CAPABILITY) {
            return lazyOptional.cast();
        }
        return LazyOptional.empty();
    }

    /**
     * Release the LazyOptional:
     *  It's important to invalidate the LazyOptional when the item is no longer valid.
     *  This prevents memory leaks.
     */
    public void invalidate() {
        lazyOptional.invalidate();
    }


}