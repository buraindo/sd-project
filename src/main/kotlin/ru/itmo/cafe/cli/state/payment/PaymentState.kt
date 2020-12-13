package ru.itmo.cafe.cli.state.payment

import ru.itmo.cafe.action.CreateOrderAction
import ru.itmo.cafe.action.PaymentAction
import ru.itmo.cafe.action.PrintReceiptAction
import ru.itmo.cafe.action.Processor
import ru.itmo.cafe.cli.exceptions.NoSuchMenuItemException
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.cli.state.HomeState
import ru.itmo.cafe.cli.state.State
import ru.itmo.cafe.model.order.Order
import ru.itmo.cafe.model.payment.CardPaymentModel
import ru.itmo.cafe.model.payment.CashPaymentModel
import ru.itmo.cafe.model.payment.PaymentModel
import ru.itmo.cafe.model.receipt.format.PlainTextReceiptFormatter

class PaymentState : State() {

    override fun optionsNames(): List<String> = listOf("Наличные", "Карта")

    override fun name(): String = "Оплатить заказ"

    override fun back(): State = HomeState()

    override fun forward(option: Int): State {
        val curOrder = Order.Builder().withItems(CafeManager.products).withToGo(CafeManager.toGo).build()
        val receiptAction = PrintReceiptAction(curOrder, PlainTextReceiptFormatter())
        Processor.schedule(receiptAction)
        CafeManager.clear()

        val paymentModel: PaymentModel = when (option) {
            1 -> {
                CashPaymentModel()
            }
            2 -> {
                CardPaymentModel()
            }
            else -> {
                throw NoSuchMenuItemException(option)
            }
        }

        Processor.schedule(PaymentAction(paymentModel))
        Processor.schedule(CreateOrderAction(curOrder))

        return HomeState()
    }
}