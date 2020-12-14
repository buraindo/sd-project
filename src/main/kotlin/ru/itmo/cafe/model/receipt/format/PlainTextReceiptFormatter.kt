package ru.itmo.cafe.model.receipt.format

import ru.itmo.cafe.model.receipt.Receipt

object PlainTextReceiptFormatter : ReceiptFormatter {
    override fun format(receipt: Receipt) = buildString {
        append("Заказ №${receipt.orderId}")
        append(receipt.products.joinToString("\n", "\n", "\n") { "${it.name}: ${it.priceWithDiscount}" })
        append("Итого: ${receipt.price} (с учетом скидки и НДС)")
    }
}