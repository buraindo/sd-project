package ru.itmo.cafe.model.item

interface Product : Item {
    val hasDiscount: Boolean
        get() = discount > 0.0

    val discount: Double

    val preparationTime: Int

    val priceWithDiscount: Int
        get() = (price * (1 - discount)).toInt()

    fun print() = println("$name: $price")
}