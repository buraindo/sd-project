package ru.itmo.cafe.cli.state

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.payment.PaymentState

class HomeState : State() {

    override fun optionsNames(): List<String> = listOf("Меню", "Корзина", "Статус")

    override fun hasBack(): Boolean = false

    override fun name(): String = "Главное меню"

    override fun back(): State? = null

    override fun forward(option: Int): State {
        return when(option) {
            1 -> {
                MenuState()
            }
            2 -> {
                CafeManager.products.also { products ->
                    if (products.isEmpty()) {
                        println("Корзина пуста")
                        return this
                    }

                    products.forEach { it.print() }
                }

                PaymentState()
            }
            3 -> {
                OrderStatusState()
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }
    }
}