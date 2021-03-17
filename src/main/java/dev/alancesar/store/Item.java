package dev.alancesar.store;

import java.math.BigDecimal;

public class Item {

    private final String name;
    private final BigDecimal price;
    private final Category category;

    public Item(String name, BigDecimal price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
