package ru.itmo.cafe.model.receipt.format

import org.junit.Test
import ru.itmo.cafe.model.item.drinks.Black
import ru.itmo.cafe.model.item.drinks.Cold
import ru.itmo.cafe.model.item.drinks.Tea
import ru.itmo.cafe.model.item.drinks.TeaSmall
import ru.itmo.cafe.model.item.food.CheeseBurger
import ru.itmo.cafe.model.item.food.HamBurger
import ru.itmo.cafe.model.order.Order
import ru.itmo.cafe.model.receipt.Receipt
import kotlin.test.assertEquals

class PlainTestReceiptFormatterTest {
    @Test
    fun format() {
        val order = Order.Builder().withProducts(
            listOf(
                CheeseBurger,
                HamBurger,
                Tea(Black, Cold, TeaSmall)
            )
        ).build()
        val receipt = Receipt(order)
        val text = "Заказ №${order.id}\n" +
                "Чизбургер: ${CheeseBurger.priceWithDiscount}\n" +
                "Гамбургер: ${HamBurger.priceWithDiscount}\n" +
                "Чай черный, холодный, 0.2л: ${Tea(Black, Cold, TeaSmall).priceWithDiscount}\n" +
                "Итого: ${order.products.sumBy { it.priceWithDiscount }} (с учетом скидки и НДС)"
        assertEquals(text, PlainTextReceiptFormatter().format(receipt))
    }
}