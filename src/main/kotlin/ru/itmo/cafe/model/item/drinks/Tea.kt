package ru.itmo.cafe.model.item.drinks

import ru.itmo.cafe.model.item.Item
import ru.itmo.cafe.model.item.Product

class Tea(private val color: TeaColor, private val temperature: TeaTemperature, private val size: TeaSize) : Product {
    override fun hasDiscount() = false

    override fun getDiscount() = 0.0

    override fun getPreparationTime() = when (temperature) {
        Cold -> 0
        Hot -> 10
    }

    override fun getName() = "Чай $color, $temperature, $size"

    override fun getPrice() = color.getPrice() + temperature.getPrice() + size.getPrice()
}

sealed class TeaColor : Item

object Black : TeaColor() {
    override fun getName() = "черный"
    override fun getPrice() = 10
}

object Green : TeaColor() {
    override fun getName() = "зеленый"
    override fun getPrice() = 15
}

sealed class TeaTemperature : Item

object Cold : TeaTemperature() {
    override fun getName() = "холодный"
    override fun getPrice() = 55
}

object Hot : TeaTemperature() {
    override fun getName() = "горячий"
    override fun getPrice() = 10
}

sealed class TeaSize : Item

object TeaSmall : TeaSize() {
    override fun getName() = "0.2л"
    override fun getPrice() = 0
}

object TeaMedium : TeaSize() {
    override fun getName() = "0.33л"
    override fun getPrice() = 10
}