package ru.itmo.cafe.model.receipt.format

import ru.itmo.cafe.model.receipt.Receipt

class PlainTextReceiptFormatter : ReceiptFormatter {
    override fun format(receipt: Receipt): String {
        val products =
            receipt.products.map { "${it.name}: ${it.priceWithDiscount}" }.joinToString { "\n" }
        return """
            Заказ №${receipt.orderId}
            $products
            Итого: ${receipt.price} (с учетом скидки и НДС)
        """.trimIndent()
    }
}