package dev.alancesar.store

import spock.lang.Specification

class DiscountCalculatorSpec extends Specification {

    def "Should calculate discount value properly"(float price, Category category, float expectedDiscount) {

        given: "a list with one item"
        def items = [new Item("Test Item", new BigDecimal(price), category)]

        when: "create a discount"
        def calculator = new CategoryDiscountCalculator(
                Category.GAME,
                new BigDecimal(200),  // minPrice
                new BigDecimal(50),   // maxDiscount
                new BigDecimal(0.10)  // discountPercentValue
        )

        and: "calculate the discount for these items"
        def discount = calculator.calculate(items)

        then: "discount should be as expected"
        discount == new BigDecimal(expectedDiscount)

        where: "the values are"
        price | category           | expectedDiscount
        100   | Category.GAME      | 0
        200   | Category.GAME      | 20
        2000  | Category.GAME      | 50
        100   | Category.CONSOLE   | 0
        200   | Category.CONSOLE   | 0
        2000  | Category.CONSOLE   | 0
        100   | Category.ACCESSORY | 0
        200   | Category.ACCESSORY | 0
        2000  | Category.ACCESSORY | 0
    }
}
