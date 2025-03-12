package com.hyperactvty.blockoffate.interfaces;

public class Karma implements IKarma {
    private int karma = 0; // Default karma value

    @Override
    public int getKarma() {
        return karma;
    }

    @Override
    public void setKarma(int karma) {
        this.karma = karma;
    }
}
