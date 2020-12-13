package ru.itmo.cafe.cli.state.menu.drinks

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.menu.MenuState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.model.item.drinks.*

object CoffeeState : State() {
    override val optionsNames = listOf("Добавить эспрессо", "Добавить капучино", "Добавить латте")

    override val name = "Кофе"

    override val back = MenuState()

    override fun forward(option: Int): CoffeeSizeState {
        val coffeeType = when (option) {
            1 -> Espresso
            2 -> Cappuccino
            3 -> Latte
            else -> throw NoSuchMenuItemException(option)
        }

        return CoffeeSizeState(coffeeType)
    }
}

class CoffeeSizeState(private val coffeeType: CoffeeType) : State() {
    override val optionsNames = listOf("Маленький", "Средний")

    override val name = "Размер кофе"

    override val back = CoffeeState

    override fun forward(option: Int): State {
        val coffeeSize = when (option) {
            1 -> CoffeeSmall
            2 -> CoffeeMedium
            else -> throw NoSuchMenuItemException(option)
        }

        CafeManager.products.add(Coffee(coffeeType, coffeeSize))
        return CoffeeState
    }
}
