package ru.itmo.cafe.model.receipt.format

import com.fasterxml.jackson.databind.ObjectMapper
import ru.itmo.cafe.model.receipt.Receipt

class JsonReceiptFormatter : ReceiptFormatter {
    override fun format(receipt: Receipt): String = JsonUtils.objectMapper.writeValueAsString(receipt)
}

object JsonUtils {
    val objectMapper: ObjectMapper = ObjectMapper()
}