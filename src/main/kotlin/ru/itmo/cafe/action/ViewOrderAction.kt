package ru.itmo.cafe.action

import ru.itmo.cafe.manager.OrderManager

class ViewOrderAction(private val orderId: Int) : Action() {
    override fun execute() = OrderManager.findOrder(orderId).print()
}