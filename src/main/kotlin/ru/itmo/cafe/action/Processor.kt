package ru.itmo.cafe.action

import ru.itmo.cafe.exceptions.ActionNotFoundException
import ru.itmo.cafe.exceptions.OrderNotFoundException

object Processor {
    private val actions = hashMapOf<Int, Action>()

    fun schedule(action: Action) {
        val id = action.id

        if (id in actions) return

        actions[id] = action

        runCatching {
            action.execute()
        }.onFailure {
            when (it) {
                is ActionNotFoundException -> println("Операции ${it.id} не существует")
                is OrderNotFoundException -> println("Заказ ${it.id} не найден")
                else -> throw it
            }
        }
    }

    fun findAction(actionId: Int) = actions[actionId] ?: throw ActionNotFoundException(actionId)
}