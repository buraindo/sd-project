package ru.itmo.cafe.cli.manager

import ru.itmo.cafe.cli.exceptions.CliException
import ru.itmo.cafe.cli.state.HomeState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.cli.state.State.Companion.BACK
import ru.itmo.cafe.model.item.Product

class CafeManager: Runnable {

    companion object {
        val products = mutableListOf<Product>()
        var toGo = false

        fun clear() {
            products.clear()
            toGo = false
        }
    }

    var curState: State = HomeState()

    override fun run() {
        while (true) {
            process()
        }
    }

    private fun process() {
        println(curState)
        val option = readLine()!!.toInt()
        if (option == BACK) {
            val backState = curState.back()
            backState?.also {
                curState = backState
            }
        } else {
            try {
                curState = curState.forward(option)
            } catch (cliException: CliException) {
                println("Нет такого пункта меню, введите еще раз")
            }
        }
    }
}