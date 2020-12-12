package ru.itmo.cafe.model.product

import org.junit.Test
import ru.itmo.cafe.model.item.Product
import ru.itmo.cafe.model.item.drinks.*
import ru.itmo.cafe.model.item.food.CheeseBurger
import ru.itmo.cafe.model.item.food.HamBurger
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProductTest {
    @Test
    fun testPricesWithDiscount() {
        val hamburger: Product = HamBurger
        val cheeseBurger: Product = CheeseBurger
        val tea: Product = Tea(Black, Cold, TeaMedium)
        val coffee: Coffee = Coffee(Espresso, CoffeeMedium)

        assertEquals((hamburger.price - hamburger.price * hamburger.discount).toInt(), hamburger.priceWithDiscount)
        assertEquals(
            (cheeseBurger.price - cheeseBurger.price * cheeseBurger.discount).toInt(),
            cheeseBurger.priceWithDiscount
        )
        assertEquals((tea.price - tea.price * tea.discount).toInt(), tea.priceWithDiscount)
        assertEquals((coffee.price - coffee.price * coffee.discount).toInt(), coffee.priceWithDiscount)
    }

    @Test
    fun testHasDiscount() {
        assertFalse { HamBurger.hasDiscount }
        assertTrue { CheeseBurger.hasDiscount }
        assertFalse { Tea(Green, Hot, TeaSmall).hasDiscount }
        assertFalse { Coffee(Espresso, CoffeeMedium).hasDiscount }
    }
}