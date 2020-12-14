package ru.itmo.cafe.model.order

import ru.itmo.cafe.context.GlobalContext
import ru.itmo.cafe.model.item.Product
import ru.itmo.cafe.model.payment.PaymentModel

class Order private constructor(val id: Int) {
    private var toGo: Boolean = false

    internal var products: List<Product> = mutableListOf()
        private set
    internal var status: Status = Status.CREATED

    private lateinit var paymentModel: PaymentModel

    class Builder {
        private val order = Order(GlobalContext.nextId())

        fun withProducts(products: List<Product>): Builder {
            order.products = ArrayList(products)
            return this
        }

        fun withPaymentModel(paymentModel: PaymentModel): Builder {
            order.paymentModel = paymentModel
            return this
        }

        fun withToGo(toGo: Boolean): Builder {
            order.toGo = toGo
            return this
        }

        fun build() = order
    }

    fun print() {
        println("Заказ №$id:")
        products.forEach { it.print() }
    }
}

enum class Status {
    CREATED, READY, PICKED, CANCELLED
}