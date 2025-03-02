package com.hyperactvty.blockoffate;

public class SetupCheck {

    private boolean primaryConfigLoaded = false;
    private boolean defaultDropsLoaded = false;
    private boolean customDropsLoaded = false;
    private boolean customConfigLoaded = false;

    public boolean isPrimaryConfigLoaded() { return primaryConfigLoaded; }

    public void setPrimaryConfigLoaded(boolean primaryConfigLoaded) { this.primaryConfigLoaded = primaryConfigLoaded; }

    public boolean isDefaultDropsLoaded() { return defaultDropsLoaded; }

    public void setDefaultDropsLoaded(boolean defaultDropsLoaded) { this.defaultDropsLoaded = defaultDropsLoaded; }

    public boolean isCustomDropsLoaded() { return customDropsLoaded; }

    public void setCustomDropsLoaded(boolean customDropsLoaded) { this.customDropsLoaded = customDropsLoaded; }

    public boolean isCustomConfigLoaded() { return customConfigLoaded; }

    public void setCustomConfigLoaded(boolean customConfigLoaded) { this.customConfigLoaded = customConfigLoaded; }

    public boolean setupComplete() {
        if(!isPrimaryConfigLoaded()) return false;
        if(!isDefaultDropsLoaded()) return false;
        if(!isCustomDropsLoaded()) return false;
        if(!isCustomConfigLoaded()) return false;
        return true;
    }
}
