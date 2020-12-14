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
import ru.itmo.cafe.model.receipt.format.PlainTextReceiptFormatter

object PaymentState : State() {

    override val optionsNames = listOf("Наличные", "Карта")

    override val name = "Оплатить заказ"

    override val back = HomeState

    override fun forward(option: Int): HomeState {
        val paymentModel = when (option) {
            1 -> CashPaymentModel()
            2 -> CardPaymentModel()
            else -> throw NoSuchMenuItemException(option)
        }

        // TODO (damtev), если чек нужен, вызвать withReceipt от нужного формата
        val order =
            Order.Builder().withProducts(CafeManager.products).withToGo(CafeManager.toGo).withPaymentModel(paymentModel)
                .build()
        // TODO (damtev) спросить нужен ли чек, и в каком формате печатать
        val receiptAction = PrintReceiptAction(order, PlainTextReceiptFormatter())
        Processor.schedule(receiptAction)
        CafeManager.clear()

        Processor.schedule(PaymentAction(paymentModel))
        Processor.schedule(CreateOrderAction(order))

        return HomeState
    }
}