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
import ru.itmo.cafe.model.receipt.format.JsonReceiptFormatter
import ru.itmo.cafe.model.receipt.format.PlainTextReceiptFormatter

open class PaymentState : State() {

    override val optionsNames = listOf("Наличные", "Карта")

    override val name = "Оплатить заказ"

    override val back = HomeState

    override fun forward(option: Int): State {
        val paymentModel = when (option) {
            1 -> CashPaymentModel
            2 -> CardPaymentModel
            else -> throw NoSuchMenuItemException(option)
        }

        val order =
            Order.Builder().withProducts(CafeManager.products).withToGo(CafeManager.toGo).withPaymentModel(paymentModel)
                .build()
        CafeManager.clear()

        Processor.safeSchedule(PaymentAction(paymentModel))
        Processor.safeSchedule(CreateOrderAction(order))

        return ReceiptState(order)
    }
}

class ReceiptState(val order: Order) : State() {
    override val optionsNames = listOf("Распечатать чек")

    override val back: State = HomeState

    override val name: String = "Чек"

    override fun forward(option: Int): State = when (option) {
        1 -> ChooseReceiptTypeState(order)
        else -> throw NoSuchMenuItemException(option)
    }
}

class ChooseReceiptTypeState(val order: Order) : State() {
    override val optionsNames = listOf("Текст", "JSON")

    override val back: State = HomeState

    override val name: String = "Вид чека"

    override fun forward(option: Int): State {
        val receiptFormatter = when (option) {
            1 -> PlainTextReceiptFormatter
            2 -> JsonReceiptFormatter
            else -> throw NoSuchMenuItemException(option)
        }

        PrintReceiptAction(order, receiptFormatter).also {
            Processor.safeSchedule(it)
        }

        return HomeState
    }
}