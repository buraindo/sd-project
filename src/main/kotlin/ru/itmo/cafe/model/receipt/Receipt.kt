package ru.itmo.cafe.model.receipt

import ru.itmo.cafe.model.order.Order

class Receipt(private val order: Order) {
    fun getOrderId() = order.id

    fun getPrice() = order.products.sumBy { it.getPriceWithDiscount() }

    fun getProducts() = order.products
}