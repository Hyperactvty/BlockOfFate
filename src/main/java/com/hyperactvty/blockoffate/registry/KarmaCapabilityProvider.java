package com.hyperactvty.blockoffate.registry;

import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.interfaces.IKarma;
import com.hyperactvty.blockoffate.interfaces.Karma;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class KarmaCapabilityProvider implements ICapabilityProvider/*, INBTSerializable<CompoundTag>*/ {
    public static final ResourceLocation KARMA_CAP_ID = ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "karma");

    private final LazyOptional<IKarma> instance = LazyOptional.of(Karma::new);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == CapabilityRegistry.KARMA ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return ICapabilityProvider.super.getCapability(cap);
    }

//    @Override
//    public CompoundTag serializeNBT() {
//        return CapabilityRegistry.KARMA.writeNBT(instance.orElseThrow(IllegalStateException::new), null);
//    }
//
//    @Override
//    public void deserializeNBT(CompoundTag nbt) {
//        CapabilityRegistry.KARMA.readNBT(instance.orElseThrow(IllegalStateException::new), null, nbt);
//    }

//    @Override
//    public CompoundTag serializeNBT(HolderLookup.Provider registryAccess) {
//        return null;
//    }
//
//    @Override
//    public void deserializeNBT(HolderLookup.Provider registryAccess, CompoundTag nbt) {
//
//    }
}
