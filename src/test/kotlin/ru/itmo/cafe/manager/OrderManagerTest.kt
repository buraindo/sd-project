package ru.itmo.cafe.manager

import org.junit.Test
import ru.itmo.cafe.exceptions.OrderNotFoundException
import ru.itmo.cafe.model.order.Order
import ru.itmo.cafe.model.order.Status
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OrderManagerTest {
    @Test
    fun emptyManager() {
        assertFailsWith(OrderNotFoundException::class) { OrderManager.findOrder(100) }
    }

    @Test
    fun findExistingOrder() {
        val order = Order(1)
        val job = OrderManager.processOrder(order)
        while (!job.isCompleted) { }
        assertEquals(order, OrderManager.findOrder(order.id))
        assertEquals(Status.READY, order.status)
    }
}