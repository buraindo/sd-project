package ru.itmo.cafe.action

import org.junit.Before
import org.junit.Test
import ru.itmo.cafe.exceptions.*
import ru.itmo.cafe.manager.OrderManager
import ru.itmo.cafe.model.item.SomeTestProduct
import ru.itmo.cafe.model.order.Order
import ru.itmo.cafe.model.order.Status
import java.lang.Thread.onSpinWait
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ProcessorTest {
    private lateinit var order: Order
    private lateinit var createOrderAction: CreateOrderAction

    @Before
    fun createOrder() {
        order = Order.Builder().withProducts(listOf(SomeTestProduct)).build()
        createOrderAction = CreateOrderAction(order)
        Processor.schedule(createOrderAction)
    }

    @Test
    fun testHasCancelledOrder() {
        Processor.schedule(CancelOrderAction(order.id))
        while (createOrderAction.isActive) {
            onSpinWait()
        }
        assertEquals(order, OrderManager.findOrder(order.id))
        assertEquals(Status.CANCELLED, order.status)
    }

    @Test
    fun testHasPickedOrder() {
        while (createOrderAction.isActive) {
            onSpinWait()
        }
        Processor.schedule(PickUpOrderAction(order.id))
        assertEquals(order, OrderManager.findOrder(order.id))
        assertEquals(Status.PICKED, order.status)
    }

    @Test
    fun testCantPickupNotReadyOrder() {
        assertFailsWith<OrderIsNotReadyException> { Processor.schedule(PickUpOrderAction(order.id)) }
    }

    @Test
    fun testCantPickupPickedOrder() {
        while (createOrderAction.isActive) {
            onSpinWait()
        }
        Processor.schedule(PickUpOrderAction(order.id))
        assertFailsWith<OrderAlreadyPickedException> { Processor.schedule(PickUpOrderAction(order.id)) }
    }

    @Test
    fun testCantPickupCancelledOrder() {
        Processor.schedule(CancelOrderAction(order.id))
        assertFailsWith<OrderWasCancelledException> { Processor.schedule(PickUpOrderAction(order.id)) }
    }

    @Test
    fun testCantCancelReadyOrder() {
        while (createOrderAction.isActive) {
            onSpinWait()
        }
        assertFailsWith<OrderAlreadyReadyException> { Processor.schedule(CancelOrderAction(order.id)) }
    }

    @Test
    fun testCantCancelPickedOrder() {
        while (createOrderAction.isActive) {
            onSpinWait()
        }
        Processor.schedule(PickUpOrderAction(order.id))
        assertFailsWith<OrderAlreadyPickedException> { Processor.schedule(CancelOrderAction(order.id)) }
    }

    @Test
    fun testCantCancelCancelledOrder() {
        Processor.schedule(CancelOrderAction(order.id))
        while (createOrderAction.isActive) {
            onSpinWait()
        }
        assertFailsWith<OrderAlreadyCancelledException> { Processor.schedule(CancelOrderAction(order.id)) }
    }
}