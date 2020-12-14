package ru.itmo.cafe.action

import ru.itmo.cafe.exceptions.OrderNotFoundException
import ru.itmo.cafe.model.order.Status

class CancelOrderAction(private val orderId: Int) : Action() {
    override fun execute() {
        val action = Processor.findAction(orderId)
        if (action !is CreateOrderAction) throw OrderNotFoundException(orderId)

        if (action.order.status == Status.CANCELLED) {
            println("Заказ уже отменен")
            return
        }

        action.cancel()
        action.order.status = Status.CANCELLED
        println("Заказ $orderId отменен")
    }
}