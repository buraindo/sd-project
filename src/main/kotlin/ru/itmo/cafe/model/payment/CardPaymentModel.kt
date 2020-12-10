package ru.itmo.cafe.model.payment

class CardPaymentModel : PaymentModel {
    override fun getPrompt(): String {
        return "Прикладывайте карту"
    }
}