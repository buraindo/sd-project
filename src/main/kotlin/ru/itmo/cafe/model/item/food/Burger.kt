package ru.itmo.cafe.model.item.food

import ru.itmo.cafe.model.item.Product

sealed class Burger : Product

object HamBurger : Burger() {
    override val hasDiscount = false

    override val discount = 0.0

    override val preparationTime = 7

    override val name = "Гамбургер"

    override val price = 50
}

object CheeseBurger : Burger() {
    override val hasDiscount = true

    override val discount = 0.2

    override val preparationTime = 6

    override val name = "Чизбургер"

    override val price = 45
}

class ExtraCutletDecorator(private val burger: Burger) : Burger() {
    override val hasDiscount = burger.hasDiscount

    override val discount = burger.discount

    override val preparationTime = burger.preparationTime + 1

    override val name = burger.name + ", доп. котлета"

    override val price = burger.price + 1
}