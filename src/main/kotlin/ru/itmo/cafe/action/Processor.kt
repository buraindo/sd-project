package ru.itmo.cafe.action

import ru.itmo.cafe.exceptions.ActionNotFoundException

object Processor {
    private val actions = hashMapOf<Int, Action>()

    fun schedule(action: Action) {
        val id = action.id
        if (id in actions) return
        actions[id] = action
        action.execute()
    }

    fun safeSchedule(action: Action) {
        runCatching { schedule(action) }.onFailure { println(it.message) }
    }

    fun findAction(actionId: Int) = actions[actionId] ?: throw ActionNotFoundException(actionId)
}