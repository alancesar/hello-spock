package dev.alancesar.store

import spock.lang.Specification

class BasketSpec extends Specification {

    Basket gamerBasket

    def setup() {
        def items = [
                new Item("Call of Duty", new BigDecimal(200), Category.GAME),
                new Item("PlayStation 5", new BigDecimal(4400), Category.CONSOLE),
                new Item("Dualsense", new BigDecimal(400), Category.ACCESSORY)
        ]

        gamerBasket = new Basket(items)
        System.out.println('just created a gamer basket!')
    }

    def "Should apply discount to the basket properly"() {
        given:
        def basket = gamerBasket

        def discount = new CategoryDiscountCalculator(
                Category.GAME,
                new BigDecimal(200),  // minPrice
                new BigDecimal(50),   // maxDiscount
                new BigDecimal(0.10)  // discountPercentValue
        )

        when:
        basket.applyDiscount(discount)

        then:
        with(basket) {
            it.discount == 20
            it.totalItems == 3
            it.totalPrice == (200 + 4400 + 400) - 20
        }
    }

    def "Should not apply any discount to the basket"() {
        given:
        def basket = gamerBasket

        when:
        basket.removeDiscount()

        then:
        with(basket) {
            discount == 0
            totalItems == 3
            totalPrice == 200 + 4400 + 400
        }
    }
}
