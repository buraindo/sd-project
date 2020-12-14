package ru.itmo.cafe.manager

import java.lang.Thread.sleep
import org.junit.Test
import ru.itmo.cafe.exceptions.OrderNotFoundException
import ru.itmo.cafe.model.item.SomeTestProduct
import ru.itmo.cafe.model.order.Order
import ru.itmo.cafe.model.order.Status
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OrderManagerTest {
    @Test
    fun testEmptyManager() {
        assertFailsWith(OrderNotFoundException::class) { OrderManager.findOrder(100) }
    }

    @Test
    fun testHasCreatedOrder() {
        val order = Order.Builder().withProducts(listOf(SomeTestProduct)).build()
        val id = order.id
        OrderManager.processOrder(order)
        sleep(SomeTestProduct.preparationTime * 500L)
        assertEquals(order, OrderManager.findOrder(id))
    }

    @Test
    fun testHasReadyOrder() {
        val order = Order.Builder().build()
        val id = order.id
        OrderManager.processOrder(order)
        sleep(SomeTestProduct.preparationTime * 500L)
        assertEquals(Status.READY, order.status)
        assertEquals(order, OrderManager.findOrder(id))
    }
}