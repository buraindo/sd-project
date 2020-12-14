package ru.itmo.cafe.action

import kotlinx.coroutines.Job
import ru.itmo.cafe.manager.OrderManager
import ru.itmo.cafe.model.order.Order

class CreateOrderAction(val order: Order) : CancellableAction() {
    private var job: Job = Job()

    override val id: Int
        get() = order.id

    override fun cancel() = job.cancel()

    override fun execute() {
        job = OrderManager.processOrder(order)
        println("Спасибо за заказ! Номер заказа: $id")
    }
}