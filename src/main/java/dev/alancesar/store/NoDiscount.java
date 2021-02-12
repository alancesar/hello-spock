package dev.alancesar.store;

import java.math.BigDecimal;

public class NoDiscount implements DiscountCalculator {
    @Override
    public BigDecimal calculate(Basket basket) {
        return BigDecimal.ZERO;
    }
}
