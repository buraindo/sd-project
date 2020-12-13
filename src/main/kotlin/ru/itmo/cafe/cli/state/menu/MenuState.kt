package ru.itmo.cafe.cli.state

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.menu.BurgersState
import ru.itmo.cafe.cli.state.menu.drinks.CoffeeState
import ru.itmo.cafe.cli.state.menu.drinks.TeaState

class MenuState : State() {

    override fun optionsNames(): List<String> = listOf("Бургеры", "Чай", "Кофе", "С собой", "В ресторане")

    override fun name(): String = "Меню"

    override fun back(): State = HomeState()

    override fun forward(option: Int): State {
        return when (option) {
            1 -> {
                BurgersState()
            }
            2 -> {
                TeaState()
            }
            3 -> {
                CoffeeState()
            }
            4, 5 -> {
                CafeManager.toGo = option == 3
                return this
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }
    }
}

