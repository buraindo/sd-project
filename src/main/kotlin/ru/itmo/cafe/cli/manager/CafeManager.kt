package ru.itmo.cafe.cli.manager

import ru.itmo.cafe.cli.state.HomeState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.cli.state.State.Companion.BACK
import ru.itmo.cafe.model.item.Product

class CafeManager : Runnable {
    private var curState: State = HomeState

    override fun run() {
        while (true) {
            process()
        }
    }

    private fun process() {
        println(curState)
        val option = readLine()?.toInt() ?: error("An error occurred while reading the option")
        if (option == BACK) {
            curState.back?.let { curState = it }
            return
        }

        runCatching { curState = curState.forward(option) }
            .onFailure { println("Нет такого пункта меню, введите еще раз") }
    }

    companion object {
        val products = mutableListOf<Product>()
        var toGo = false

        fun clear() {
            products.clear()
            toGo = false
        }
    }
}