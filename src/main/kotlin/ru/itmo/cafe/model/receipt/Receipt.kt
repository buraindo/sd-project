package ru.itmo.cafe.model.receipt

import ru.itmo.cafe.model.order.Order

class Receipt(private val order: Order) {
    val orderId = order.id

    val price: Int
        get() = order.products.sumBy { it.priceWithDiscount }

    val products = order.products
}