package ru.itmo.cafe.cli.state.menu.drinks

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.menu.MenuState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.model.item.drinks.*

object TeaState : State() {
    override val optionsNames = listOf("Добавить черный чай", "Добавить зеленый чай")

    override val name = "Чай"

    override val back = MenuState()

    override fun forward(option: Int): TeaTemperatureState {
        val teaColor = when (option) {
            1 -> Black
            2 -> Green
            else -> throw NoSuchMenuItemException(option)
        }
        return TeaTemperatureState(teaColor)
    }
}

class TeaTemperatureState(private val teaColor: TeaColor) : State() {
    override val optionsNames = listOf("Горячий", "Холодный")

    override val name = "Температура чая"

    override val back = TeaState

    override fun forward(option: Int): TeaSizeState {
        val teaTemperature = when (option) {
            1 -> Hot
            2 -> Cold
            else -> throw NoSuchMenuItemException(option)
        }

        return TeaSizeState(teaColor, teaTemperature)
    }
}

class TeaSizeState(private val teaColor: TeaColor, private val teaTemperature: TeaTemperature) : State() {
    override val optionsNames = listOf("Маленький", "Средний")

    override val name = "Размер чая"

    override val back = TeaTemperatureState(teaColor)

    override fun forward(option: Int): TeaState {
        val teaSize = when (option) {
            1 -> TeaSmall
            2 -> TeaMedium
            else -> throw NoSuchMenuItemException(option)
        }

        CafeManager.products.add(Tea(teaColor, teaTemperature, teaSize))
        return TeaState
    }
}
