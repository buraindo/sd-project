package ru.itmo.cafe.cli.state

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.menu.MenuState
import ru.itmo.cafe.cli.state.payment.PaymentState
import ru.itmo.cafe.cli.state.status.OrderStatusState

object HomeState : State() {

    override val optionsNames = listOf("Меню", "Корзина", "Статус")

    override val hasBack = false

    override val name = "Главное меню"

    override val back: State? = null

    override fun forward(option: Int) = when (option) {
        1 -> MenuState()
        2 -> PaymentState.also {
            val products = CafeManager.products
            if (products.isEmpty()) {
                println("Корзина пуста")
            }

            products.forEach { it.print() }
        }
        3 -> OrderStatusState
        else -> throw NoSuchMenuItemException(option)
    }
}