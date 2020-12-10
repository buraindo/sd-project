package ru.itmo.cafe.model.receipt.format

import ru.itmo.cafe.model.receipt.Receipt

class PlainTextReceiptFormatter : ReceiptFormatter {
    override fun format(receipt: Receipt): String {
        val products =
            receipt.getProducts().map { "${it.getName()}: ${it.getPriceWithDiscount()}" }.joinToString { "\n" }
        return """
            Заказ №${receipt.getOrderId()}
            $products
            Итого: ${receipt.getPrice()} (с учетом скидки и НДС)
        """.trimIndent()
    }
}