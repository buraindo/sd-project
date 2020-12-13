package ru.itmo.cafe.cli.state

abstract class State {

    companion object {
        const val BACK = 0
    }

    abstract fun optionsNames(): List<String>

    open fun hasBack(): Boolean = true

    abstract fun name(): String

    abstract fun back(): State?

    abstract fun forward(option: Int): State

    final override fun toString(): String = buildString {
        appendLine(name())
        if (hasBack()) {
            appendLine("\t$BACK -> Назад")
        }
        optionsNames().forEachIndexed { i, name ->
            appendLine("\t${i + 1} -> $name")
        }
    }
}

