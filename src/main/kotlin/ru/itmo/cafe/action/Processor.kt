package ru.itmo.cafe.action

import ru.itmo.cafe.exceptions.ActionNotFoundException
import ru.itmo.cafe.exceptions.OrderNotFoundException

object Processor {
    private val actions: HashMap<Int, Action> = hashMapOf()

    fun schedule(action: Action) {
        val id = action.id
        if (actions.containsKey(id)) {
            return
        }
        actions[id] = action
        try {
            action.execute()
        } catch (e: Exception) {
            when (e) {
                is ActionNotFoundException -> println("Операции ${e.id} не существует")
                is OrderNotFoundException -> println("Заказ ${e.id} не найден")
                else -> throw e
            }
        }
    }

    fun getAction(actionId: Int) = actions[actionId] ?: throw ActionNotFoundException(actionId)
}