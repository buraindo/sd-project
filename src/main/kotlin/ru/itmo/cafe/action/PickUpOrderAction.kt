package ru.itmo.cafe.action

import ru.itmo.cafe.exceptions.OrderAlreadyPickedException
import ru.itmo.cafe.exceptions.OrderIsNotReadyException
import ru.itmo.cafe.exceptions.OrderWasCancelledException
import ru.itmo.cafe.manager.OrderManager
import ru.itmo.cafe.model.order.Status

class PickUpOrderAction(private val orderId: Int) : Action() {
    override fun execute() {
        val order = OrderManager.findOrder(orderId)
        when (order.status) {
            Status.CREATED -> throw OrderIsNotReadyException()
            Status.PICKED -> throw OrderAlreadyPickedException()
            Status.CANCELLED -> throw OrderWasCancelledException()
            Status.READY -> println("Приятного аппетита!").also { order.status = Status.PICKED }
        }
    }
}