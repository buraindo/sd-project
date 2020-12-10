package ru.itmo.cafe.action

import ru.itmo.cafe.context.GlobalContext

abstract class Action {
    internal open val id = GlobalContext.nextId()

    abstract fun execute()
}