package ru.itmo.cafe.cli.state

import ru.itmo.cafe.action.*
import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.exceptions.OrderNotFoundException
import ru.itmo.cafe.manager.OrderManager

class OrderStatusState : State() {

    override fun optionsNames(): List<String> = emptyList()

    override fun name(): String = "Введите номер заказа"

    override fun back(): State = HomeState()

    override fun forward(option: Int): State {
        val orderId = option
        val viewOrderAction = ViewOrderAction(orderId)
        var failed = false
        try {
            OrderManager.findOrder(orderId)
        } catch (e: OrderNotFoundException) {
            failed = true
        }
        Processor.schedule(viewOrderAction)
        if (failed) {
            return this
        }

        return OrderOptionState(orderId)
    }
}

class OrderOptionState(private val orderId: Int) : State() {

    override fun name(): String = "Что сделать с заказом?"

    override fun back(): State = OrderStatusState()

    override fun optionsNames(): List<String> = listOf("Забрать", "Отменить")

    override fun forward(option: Int): State {
        val action: Action = when (option) {
            1 -> {
                PickUpOrderAction(orderId)
            }
            2 -> {
                CancelOrderAction(orderId)
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }
        Processor.schedule(action)

        return HomeState()
    }
}