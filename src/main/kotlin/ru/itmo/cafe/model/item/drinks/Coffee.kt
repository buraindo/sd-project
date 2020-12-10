package ru.itmo.cafe.model.item.drinks

import ru.itmo.cafe.model.item.Item
import ru.itmo.cafe.model.item.Product

class Coffee(private val type: CoffeeType, private val size: CoffeeSize) : Product {
    override fun hasDiscount() = false

    override fun getDiscount() = 0.0

    override fun getPreparationTime() = 10

    override fun getName() = "Кофе $type, $size"

    override fun getPrice() = type.getPrice() + size.getPrice()
}

sealed class CoffeeType : Item

object Espresso : CoffeeType() {
    override fun getName() = "эспрессо"
    override fun getPrice() = 10
}

object Cappuccino : CoffeeType() {
    override fun getName() = "капуччино"
    override fun getPrice() = 15
}

object Latte : CoffeeType() {
    override fun getName() = "латте"
    override fun getPrice() = 15
}

sealed class CoffeeSize : Item

object CoffeeSmall : CoffeeSize() {
    override fun getName() = "0.2л"
    override fun getPrice() = 0
}

object CoffeeMedium : CoffeeSize() {
    override fun getName() = "0.33л"
    override fun getPrice() = 10
}
