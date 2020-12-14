package ru.itmo.cafe.profiler.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import ru.itmo.cafe.cli.state.payment.ReceiptState
import ru.itmo.cafe.model.item.Product
import ru.itmo.cafe.model.item.drinks.Coffee
import ru.itmo.cafe.model.item.drinks.Tea
import ru.itmo.cafe.model.item.food.Burger
import ru.itmo.cafe.model.order.Order
import ru.itmo.cafe.model.order.Status
import java.io.File
import java.io.FileOutputStream

@Aspect
object Profiler {
    private val orders = mutableListOf<Order>()

    private val profit: Int
        get() = orders
            .filterNot { it.status != Status.CANCELLED }
            .flatMap { it.products }
            .sumBy { it.priceWithDiscount }

    private val ordersCount: Int
        get() = orders.size

    private val products: List<Product>
        get() = orders.flatMap { it.products }

    private val cancelledOrders: Int
        get() = orders.filter { it.status == Status.CANCELLED }.size

    private val burgers: List<Burger>
        get() = products.filterIsInstance<Burger>()

    private val coffee: List<Coffee>
        get() = products.filterIsInstance<Coffee>()

    private val tea: List<Tea>
        get() = products.filterIsInstance<Tea>()

    private val theMostPopularProduct: Product?
        get() = products
            .groupBy { it }
            .mapValues { it.value.size }
            .toList()
            .maxByOrNull { it.second }?.first

    private val statistics: String
        get() = buildString {
            appendLine("Orders: $ordersCount")
            appendLine("Orders cancelled: $cancelledOrders")
            appendLine("Bought: ${burgers.size} burgers, ${coffee.size} coffees, ${tea.size} teas")
            appendLine("The most popular meal: ${theMostPopularProduct?.name ?: "doesn't exist"}")
            appendLine("Total profit: $profit")
        }

    @Around("execution(* ru.itmo.cafe.cli.state.payment.PaymentState.forward(..))")
    fun processPayment(joinPoint: ProceedingJoinPoint) =
        (joinPoint.proceed(joinPoint.args) as ReceiptState).also { receipt ->
            val order = receipt.order
            orders += order
            val text = buildString {
                appendLine("Order number: ${order.id}")
                appendLine(order.products.joinToString(separator = System.lineSeparator()) {
                    "${it.name}: ${it.priceWithDiscount}"
                })
                appendLine("Price: ${order.products.sumBy { it.priceWithDiscount }}")
            }
            File("orders").let { file ->
                FileOutputStream(file, true).bufferedWriter().use {
                    it.write(text)
                }
            }
        }

    fun writeSessionResult() {
        File("statistic").bufferedWriter().use {
            it.write(statistics)
        }
    }
}