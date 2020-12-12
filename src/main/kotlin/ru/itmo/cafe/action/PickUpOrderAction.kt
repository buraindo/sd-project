package ru.itmo.cafe.action

import ru.itmo.cafe.manager.OrderManager
import ru.itmo.cafe.model.order.Status

class PickUpOrderAction(private val orderId: Int) : Action() {
    override fun execute() {
        val order = OrderManager.findOrder(orderId)
        when (order.status) {
            Status.CREATED -> println("Подождите, заказ ещё не готов")
            Status.PICKED -> println("Заказ уже забрали")
            Status.CANCELLED -> println("Заказ был отменен")
            Status.READY -> println("Приятного аппетита!").also { order.status = Status.PICKED }
        }
    }
}