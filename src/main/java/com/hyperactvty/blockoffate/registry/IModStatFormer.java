package com.hyperactvty.blockoffate.registry;

import net.minecraft.stats.StatFormatter;

public interface IModStatFormer extends StatFormatter {
    public StatFormatter DIVIDE_BY_THOUSAND = p_12885_ -> DECIMAL_FORMAT.format((double)p_12885_ * 0.001);

//    @Override
//    public String format(int p_12887_) {
//        return "";
//    }
}
