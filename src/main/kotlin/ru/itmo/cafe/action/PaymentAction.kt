package ru.itmo.cafe.action

import ru.itmo.cafe.model.payment.PaymentModel

class PaymentAction(private val paymentModel: PaymentModel) : Action() {
    override fun execute() = println(paymentModel.prompt)
}