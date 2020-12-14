package ru.itmo.cafe.action

abstract class CancellableAction : Action() {
    abstract fun cancel()

    abstract val isActive: Boolean
}