package ru.itmo.cafe.model.item

interface Product : Item {
    fun hasDiscount(): Boolean

    fun getDiscount(): Double

    fun getPreparationTime(): Int

    fun print() = println(getName() + ": ${getPrice()}")

    fun getPriceWithDiscount(): Int = if (hasDiscount()) (getPrice() * (1 - getDiscount())).toInt() else getPrice()
}