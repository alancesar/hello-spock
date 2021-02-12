package dev.alancesar.store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Basket {
    private final Collection<Item> items;
    private DiscountCalculator discount = new NoDiscount();

    public Basket() {
        items = new ArrayList<>();
    }

    public Basket(Collection<Item> items) {
        this.items = items;
    }

    public Basket addItem(Item item) {
        items.add(item);
        return this;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void applyDiscount(DiscountCalculator discount) {
        if (discount == null) {
            removeDiscount();
            return;
        }

        this.discount = discount;
    }

    public void removeDiscount() {
        this.discount = new NoDiscount();
    }

    public BigDecimal getDiscount() {
        return discount.calculate(this);
    }

    public int getTotalItems() {
        return items.size();
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(discount.calculate(this));
    }
}
