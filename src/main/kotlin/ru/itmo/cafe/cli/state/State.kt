package ru.itmo.cafe.cli.state

abstract class State {
    abstract val optionsNames: List<String>

    open val hasBack: Boolean = true

    abstract val name: String

    abstract val back: State?

    abstract fun forward(option: Int): State

    final override fun toString() = buildString {
        appendLine(name)
        if (hasBack) {
            appendLine("\t$BACK -> Назад")

        }
        optionsNames.forEachIndexed { i, name ->
            appendLine("\t${i + 1} -> $name")
        }
    }

    companion object {
        const val BACK = 0
    }
}

