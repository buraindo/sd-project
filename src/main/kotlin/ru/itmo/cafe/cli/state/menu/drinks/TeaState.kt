package ru.itmo.cafe.cli.state.menu.drinks

import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.MenuState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.model.item.drinks.*

class TeaState: State() {
    override fun optionsNames(): List<String> = listOf("Добавить черный чай", "Добавить зеленый чай")

    override fun name(): String = "Чай"

    override fun back(): State = MenuState()

    override fun forward(option: Int): State {
        val teaColor = when (option) {
            1 -> {
                Black
            }
            2 -> {
                Green
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }

        return TeaTemperatureState(teaColor)
    }
}

class TeaTemperatureState(private val teaColor: TeaColor): State() {
    override fun optionsNames(): List<String> = listOf("Горячий", "Холодный")

    override fun name(): String = "Температура чая"

    override fun back(): State = TeaState()

    override fun forward(option: Int): State {
        val teaTemperature = when (option) {
            1 -> {
                Hot
            }
            2 -> {
                Cold
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }

        return TeaSizeState(teaColor, teaTemperature)
    }
}

class TeaSizeState(private val teaColor: TeaColor, private val teaTemperature: TeaTemperature) : State() {
    override fun optionsNames(): List<String> = listOf("Маленький", "Средний")

    override fun name(): String = "Размер чая"

    override fun back(): State = TeaTemperatureState(teaColor)

    override fun forward(option: Int): State {
        val teaSize = when (option) {
            1 -> {
                TeaSmall
            }
            2 -> {
                TeaMedium
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }

        CafeManager.products.add(Tea(teaColor, teaTemperature, teaSize))
        return TeaState()
    }
}
