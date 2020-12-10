package ru.itmo.cafe.model.receipt.format

import ru.itmo.cafe.model.receipt.Receipt

interface ReceiptFormatter {
    fun format(receipt: Receipt): String
}