package dev.alancesar.store;

import java.math.BigDecimal;

public interface DiscountCalculator {
    BigDecimal calculate(Basket basket);
}
