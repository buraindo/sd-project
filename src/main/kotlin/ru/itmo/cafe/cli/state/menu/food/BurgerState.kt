package ru.itmo.cafe.cli.state.menu.food

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.menu.MenuState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.model.item.food.Burger
import ru.itmo.cafe.model.item.food.CheeseBurger
import ru.itmo.cafe.model.item.food.ExtraCutletDecorator
import ru.itmo.cafe.model.item.food.HamBurger

object BurgersState : State() {
    override val optionsNames = listOf("Добавить гамбургер", "Добавить чизбургер")

    override val name = "Бургеры"

    override val back = MenuState()

    override fun forward(option: Int): ExtraCutletState {
        val burger = when (option) {
            1 -> HamBurger
            2 -> CheeseBurger
            else -> throw NoSuchMenuItemException(option)
        }

        CafeManager.products += burger

        return ExtraCutletState
    }
}

object ExtraCutletState : State() {
    override val optionsNames = listOf("Добавить дополнительную котлету")

    override val name = "Дополнительная котлета"

    override val back = BurgersState

    override fun forward(option: Int): ExtraCutletState {
        return when (option) {
            1 -> {
                val products = CafeManager.products
                val lastBurger = products.last()
                products[products.lastIndex] = ExtraCutletDecorator(lastBurger as Burger)
                ExtraCutletState
            }
            else -> throw NoSuchMenuItemException(option)
        }
    }
}