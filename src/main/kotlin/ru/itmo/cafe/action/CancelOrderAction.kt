package ru.itmo.cafe.action

import ru.itmo.cafe.exceptions.OrderNotFoundException
import ru.itmo.cafe.model.order.Status

class CancelOrderAction(private val orderId: Int) : Action() {
    override fun execute() {
        val action = Processor.getAction(orderId)
        if (action is CreateOrderAction) {
            action.cancel()
            action.order.status = Status.CANCELLED
            println("Заказ $orderId отменен")
        } else {
            throw OrderNotFoundException(id)
        }
    }
}