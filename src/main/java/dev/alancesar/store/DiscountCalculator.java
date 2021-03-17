package dev.alancesar.store;

import java.math.BigDecimal;
import java.util.Collection;

public interface DiscountCalculator {
    BigDecimal calculate(Collection<Item> items);
}
