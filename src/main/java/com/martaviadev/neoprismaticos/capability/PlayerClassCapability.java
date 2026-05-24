package com.martaviadev.neoprismaticos.capability;

import com.martaviadev.neoprismaticos.classes.RPGClass;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PlayerClassCapability {

    private RPGClass selectedClass = RPGClass.NONE;

    public RPGClass getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(RPGClass rpgClass) {
        this.selectedClass = rpgClass;
    }

    public boolean hasChosenClass() {
        return selectedClass != RPGClass.NONE;
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("rpgClass", selectedClass.name());
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        if (tag.contains("rpgClass")) {
            try {
                selectedClass = RPGClass.valueOf(tag.getString("rpgClass"));
            } catch (IllegalArgumentException e) {
                selectedClass = RPGClass.NONE;
            }
        }
    }
}