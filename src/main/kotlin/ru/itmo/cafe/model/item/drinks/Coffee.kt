package ru.itmo.cafe.model.item.drinks

import ru.itmo.cafe.model.item.Item
import ru.itmo.cafe.model.item.Product

class Coffee(type: CoffeeType, size: CoffeeSize) : Product {
    override val discount = 0.0

    override val preparationTime = 10

    override val name = "Кофе ${type.name}, ${size.name}"

    override val price = type.price + size.price
}

sealed class CoffeeType : Item

object Espresso : CoffeeType() {
    override val name = "эспрессо"
    override val price = 10
}

object Cappuccino : CoffeeType() {
    override val name = "капуччино"
    override val price = 15
}

object Latte : CoffeeType() {
    override val name = "латте"
    override val price = 15
}

sealed class CoffeeSize : Item

object CoffeeSmall : CoffeeSize() {
    override val name = "0.2л"
    override val price = 0
}

object CoffeeMedium : CoffeeSize() {
    override val name = "0.33л"
    override val price = 10
}
