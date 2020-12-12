package ru.itmo.cafe.model.order

import ru.itmo.cafe.context.GlobalContext
import ru.itmo.cafe.model.item.Product
import ru.itmo.cafe.model.payment.PaymentModel

class Order(val id: Int) {
    private var toGo: Boolean = false
    private var receipt: Boolean = false

    internal var products: List<Product> = listOf()
        private set
    internal var status: Status = Status.CREATED

    private lateinit var paymentModel: PaymentModel

    class Builder {
        private val order = Order(GlobalContext.nextId())

        fun withItems(products: List<Product>): Builder {
            order.products = products
            return this
        }

        fun withPaymentModel(paymentModel: PaymentModel): Builder {
            order.paymentModel = paymentModel
            return this
        }

        fun withReceipt(): Builder {
            order.receipt = true
            return this
        }

        fun withToGo(): Builder {
            order.toGo = true
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