package ru.itmo.cafe.model.receipt

import org.junit.Test
import ru.itmo.cafe.model.item.drinks.Black
import ru.itmo.cafe.model.item.drinks.Cold
import ru.itmo.cafe.model.item.drinks.Tea
import ru.itmo.cafe.model.item.drinks.TeaSmall
import ru.itmo.cafe.model.item.food.CheeseBurger
import ru.itmo.cafe.model.item.food.HamBurger
import ru.itmo.cafe.model.order.Order
import kotlin.test.assertEquals

class ReceiptTest {
    @Test
    fun testSimpleReceipt() {
        val order = Order.Builder().build()
        val receipt = Receipt(order)
        assertEquals(order.id, receipt.orderId)
        assertEquals(order.products, receipt.products)
    }

    @Test
    fun testPrice() {
        val order = Order.Builder().withProducts(
            listOf(
                CheeseBurger,
                HamBurger,
                Tea(Black, Cold, TeaSmall)
            )
        ).build()
        val receipt = Receipt(order)
        assertEquals(
            receipt.price,
            CheeseBurger.priceWithDiscount + HamBurger.priceWithDiscount + Tea(Black, Cold, TeaSmall).priceWithDiscount
        )
    }
}