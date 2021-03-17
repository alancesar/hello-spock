package dev.alancesar.store;

import java.math.BigDecimal;
import java.util.Collection;

public class NoDiscount implements DiscountCalculator {
    @Override
    public BigDecimal calculate(Collection<Item> items) {
        return BigDecimal.ZERO;
    }
}
