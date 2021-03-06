package ru.itmo.cafe.manager

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import ru.itmo.cafe.exceptions.OrderNotFoundException
import ru.itmo.cafe.model.order.Order
import ru.itmo.cafe.model.order.Status

object OrderManager {
    private val orders = mutableListOf<Order>()
    private val workers = Semaphore(10)

    fun findOrder(id: Int) = orders.find { o -> o.id == id } ?: throw OrderNotFoundException(id)

    fun processOrder(order: Order) = GlobalScope.launch {
        workers.acquire()
        orders.add(order)
        val time = order.products.sumBy { it.preparationTime }
        delay(time * 1000L)
        workers.release()
        order.status = Status.READY
        println("Заказ ${order.id} готов!")
    }
}