package ru.itmo.cafe.action

import ru.itmo.cafe.model.order.Order
import ru.itmo.cafe.model.receipt.Receipt
import ru.itmo.cafe.model.receipt.format.ReceiptFormatter

class PrintReceiptAction(private val order: Order, private val formatter: ReceiptFormatter) : Action() {
    override fun execute() = println(formatter.format(Receipt(order)))
}