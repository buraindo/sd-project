package ru.itmo.cafe.context

object GlobalContext {
    private var id: Int = 1

    fun nextId() = id++
}