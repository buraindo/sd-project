package ru.itmo.cafe.model.receipt.format

import com.fasterxml.jackson.databind.ObjectMapper
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

class JsonReceiptFormatterTest {
    @Test
    fun format() {
        val order = Order.Builder().withItems(
            listOf(
                CheeseBurger,
                HamBurger,
                Tea(Black, Cold, TeaSmall)
            )
        ).build()
        val receipt = Receipt(order)
        assertEquals(JsonReceiptFormatter().format(receipt), ObjectMapper().writeValueAsString(receipt))
    }
}