package ru.itmo.cafe.model.order

import org.junit.Test
import ru.itmo.cafe.context.GlobalContext
import ru.itmo.cafe.model.item.SomeTestProduct
import kotlin.test.assertEquals

class OrderTest {
    @Test
    fun testSimpleOrder() {
        val order = Order.Builder().build()
        assertEquals(GlobalContext.nextId() - 1, order.id)
        assertEquals(Status.CREATED, order.status)
    }

    @Test
    fun testOrderWithProducts() {
        val products = mutableListOf(SomeTestProduct)
        val order = Order.Builder().withProducts(products).build()
        products += SomeTestProduct
        assertEquals(1, order.products.size)
        assertEquals(SomeTestProduct, order.products[0])
    }
}