package com.hyperactvty.blockoffate.records;

import java.util.function.Function;

public record CustomFate(String fateID, Function<?, Object[]> method) {
}
