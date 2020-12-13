package ru.itmo.cafe.cli.state.menu

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.HomeState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.cli.state.menu.drinks.CoffeeState
import ru.itmo.cafe.cli.state.menu.drinks.TeaState
import ru.itmo.cafe.cli.state.menu.food.BurgersState

class MenuState : State() {

    override val optionsNames = listOf("Бургеры", "Чай", "Кофе", "С собой", "В ресторане")

    override val name = "Меню"

    override val back = HomeState

    override fun forward(option: Int): State {
        return when (option) {
            1 -> BurgersState
            2 -> TeaState
            3 -> CoffeeState
            4, 5 -> this.also { CafeManager.toGo = option == 3 }
            else -> throw NoSuchMenuItemException(option)
        }
    }
}

