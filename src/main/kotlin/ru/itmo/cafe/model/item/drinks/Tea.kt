package ru.itmo.cafe.model.item.drinks

import ru.itmo.cafe.model.item.Item
import ru.itmo.cafe.model.item.Product

class Tea(private val color: TeaColor, private val temperature: TeaTemperature, private val size: TeaSize) : Product {
    override val hasDiscount = false

    override val discount = 0.0

    override val preparationTime = when (temperature) {
        Cold -> 0
        Hot -> 10
    }

    override val name = "Чай $color, $temperature, $size"

    override val price = color.price + temperature.price + size.price
}

sealed class TeaColor : Item

object Black : TeaColor() {
    override val name = "черный"
    override val price = 10
}

object Green : TeaColor() {
    override val name = "зеленый"
    override val price = 15
}

sealed class TeaTemperature : Item

object Cold : TeaTemperature() {
    override val name = "холодный"
    override val price = 55
}

object Hot : TeaTemperature() {
    override val name = "горячий"
    override val price = 10
}

sealed class TeaSize : Item

object TeaSmall : TeaSize() {
    override val name = "0.2л"
    override val price = 0
}

object TeaMedium : TeaSize() {
    override val name = "0.33л"
    override val price = 10
}