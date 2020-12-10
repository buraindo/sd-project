package ru.itmo.cafe.model.item.food

import ru.itmo.cafe.model.item.Product

sealed class Burger : Product

object HamBurger : Burger() {
    override fun hasDiscount() = false

    override fun getDiscount() = 0.0

    override fun getPreparationTime() = 7

    override fun getName() = "Гамбургер"

    override fun getPrice() = 50
}

object CheeseBurger : Burger() {
    override fun hasDiscount() = true

    override fun getDiscount() = 0.2

    override fun getPreparationTime() = 6

    override fun getName() = "Чизбургер"

    override fun getPrice() = 45
}

class ExtraCutletDecorator(private val burger: Burger) : Burger() {
    override fun hasDiscount() = burger.hasDiscount()

    override fun getDiscount() = burger.getDiscount()

    override fun getPreparationTime() = burger.getPreparationTime() + 1

    override fun getName() = burger.getName() + ", доп. котлета"

    override fun getPrice() = burger.getPrice() + 1
}