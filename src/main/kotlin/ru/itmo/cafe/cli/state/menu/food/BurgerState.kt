package ru.itmo.cafe.cli.state.menu

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.MenuState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.model.item.food.Burger
import ru.itmo.cafe.model.item.food.CheeseBurger
import ru.itmo.cafe.model.item.food.ExtraCutletDecorator
import ru.itmo.cafe.model.item.food.HamBurger

class BurgersState : State() {
    override fun optionsNames(): List<String> = listOf("Добавить гамбургер", "Добавить чизбургер")

    override fun name(): String = "Бургеры"

    override fun back(): State = MenuState()

    override fun forward(option: Int): State {
        val burger = when (option) {
            1 -> {
                HamBurger
            }
            2 -> {
                CheeseBurger
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }

        CafeManager.products.add(burger)

        return ExtraCutletState()
    }
}

class ExtraCutletState: State() {
    override fun optionsNames(): List<String> = listOf("Добавить дополнительную котлету")

    override fun name(): String = "Дополнительная котлета"

    override fun back(): State = BurgersState()

    override fun forward(option: Int): State {
        return when (option) {
            1 -> {
                val lastBurger = CafeManager.products[CafeManager.products.lastIndex]
                CafeManager.products[CafeManager.products.lastIndex] = ExtraCutletDecorator(lastBurger as Burger)
                ExtraCutletState()
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }
    }
}