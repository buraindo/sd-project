package ru.itmo.cafe.model.payment

class CashPaymentModel : PaymentModel {
    override fun getPrompt(): String {
        return "Оплата наличными..."
    }
}