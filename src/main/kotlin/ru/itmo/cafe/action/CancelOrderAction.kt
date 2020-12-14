package ru.itmo.cafe.action

import ru.itmo.cafe.exceptions.*
import ru.itmo.cafe.model.order.Status

class CancelOrderAction(private val orderId: Int) : Action() {
    override fun execute() {
        val action = Processor.findAction(orderId)
        if (action !is CreateOrderAction) throw OrderNotFoundException(orderId)

        val order = action.order
        when (order.status) {
            Status.CREATED -> {
                action.cancel()
                order.status = Status.CANCELLED
                println("Заказ $orderId отменен")
            }
            Status.PICKED -> throw OrderAlreadyPickedException()
            Status.CANCELLED -> throw OrderAlreadyCancelledException()
            Status.READY -> throw OrderAlreadyReadyException()
        }
    }
}