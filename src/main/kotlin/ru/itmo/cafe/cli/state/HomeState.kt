package ru.itmo.cafe.cli.state

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.menu.MenuState
import ru.itmo.cafe.cli.state.payment.PaymentState
import ru.itmo.cafe.cli.state.status.OrderStatusState
import ru.itmo.cafe.profiler.configuration.ApplicationContextProvider

object HomeState : State() {

    override val optionsNames = listOf("Меню", "Корзина", "Статус")

    override val hasBack = false

    override val name = "Главное меню"

    override val back: State? = null

    override fun forward(option: Int): State = when (option) {
        1 -> MenuState()
        2 -> {
            CafeManager.products.also { products ->
                if (products.isEmpty()) {
                    println("Корзина пуста")
                    return this
                }

                products.forEach { it.print() }
            }

            ApplicationContextProvider.context.getBean(PaymentState::class.java)
        }
        3 -> OrderStatusState
        else -> throw NoSuchMenuItemException(option)
    }
}