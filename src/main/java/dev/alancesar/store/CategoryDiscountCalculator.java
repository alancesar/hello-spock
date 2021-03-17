package dev.alancesar.store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

public class CategoryDiscountCalculator implements DiscountCalculator {
    private final Category category;
    private final BigDecimal minPrice;
    private final BigDecimal maxDiscount;
    private final BigDecimal discountPercentValue;

    public CategoryDiscountCalculator(Category category,
                                      BigDecimal minPrice,
                                      BigDecimal maxDiscount,
                                      BigDecimal discountPercentValue) {
        this.category = category;
        this.minPrice = minPrice;
        this.maxDiscount = maxDiscount;
        this.discountPercentValue = discountPercentValue;
    }

    @Override
    public BigDecimal calculate(Collection<Item> items) {
        var totalPrice = items.stream()
                .filter(item -> item.getCategory().equals(category))
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalPrice.compareTo(minPrice) < 0) {
            return BigDecimal.ZERO;
        }

        var discount = totalPrice.multiply(discountPercentValue)
                .setScale(2, RoundingMode.HALF_EVEN);

        if (discount.compareTo(maxDiscount) > 0) {
            return maxDiscount;
        }

        return discount;
    }
}
