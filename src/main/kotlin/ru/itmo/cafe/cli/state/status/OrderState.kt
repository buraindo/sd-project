package ru.itmo.cafe.cli.state.status

import ru.itmo.cafe.action.CancelOrderAction
import ru.itmo.cafe.action.PickUpOrderAction
import ru.itmo.cafe.action.Processor
import ru.itmo.cafe.action.ViewOrderAction
import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.state.HomeState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.manager.OrderManager

object OrderStatusState : State() {

    override val optionsNames = emptyList<String>()

    override val name = "Введите номер заказа"

    override val back = HomeState

    override fun forward(option: Int): State {
        var failed = false

        runCatching {
            OrderManager.findOrder(option)
        }.onFailure { failed = true }

        Processor.safeSchedule(ViewOrderAction(option))

        return if (failed) this else OrderOptionState(option)
    }
}

class OrderOptionState(private val orderId: Int) : State() {

    override val name = "Что сделать с заказом?"

    override val back = OrderStatusState

    override val optionsNames = listOf("Забрать", "Отменить")

    override fun forward(option: Int): HomeState {
        val action = when (option) {
            1 -> PickUpOrderAction(orderId)
            2 -> CancelOrderAction(orderId)
            else -> throw NoSuchMenuItemException(option)
        }

        Processor.safeSchedule(action)

        return HomeState
    }
}