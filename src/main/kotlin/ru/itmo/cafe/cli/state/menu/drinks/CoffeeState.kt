package ru.itmo.cafe.cli.state.menu.drinks

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.MenuState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.model.item.drinks.*

class CoffeeState: State() {
    override fun optionsNames(): List<String> = listOf("Добавить эспрессо", "Добавить капучино", "Добавить латте")

    override fun name(): String = "Кофе"

    override fun back(): State = MenuState()

    override fun forward(option: Int): State {
        val coffeeType = when (option) {
            1 -> {
                Espresso
            }
            2 -> {
                Cappuccino
            }
            3 -> {
                Latte
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }

        return CoffeeSizeState(coffeeType)
    }
}

class CoffeeSizeState(private val coffeeType: CoffeeType) : State() {
    override fun optionsNames(): List<String> = listOf("Маленький", "Средний")

    override fun name(): String = "Размер кофе"

    override fun back(): State = CoffeeState()

    override fun forward(option: Int): State {
        val coffeeSize = when (option) {
            1 -> {
                CoffeeSmall
            }
            2 -> {
                CoffeeMedium
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }

        CafeManager.products.add(Coffee(coffeeType, coffeeSize))
        return CoffeeState()
    }
}
