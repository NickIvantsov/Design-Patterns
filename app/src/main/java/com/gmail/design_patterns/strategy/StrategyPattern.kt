package com.gmail.design_patterns.strategy

import java.math.BigDecimal
//example 1 https://www.baeldung.com/java-strategy-pattern
interface Discounter {
    fun applyDiscount(amount: BigDecimal): BigDecimal
}

class EasterDiscounter : Discounter {
    override fun applyDiscount(amount: BigDecimal): BigDecimal {
        return amount.multiply(BigDecimal.valueOf(0.5))
    }
}

class ChristmasDiscounter : Discounter {
    override fun applyDiscount(amount: BigDecimal): BigDecimal {
        return amount.multiply(BigDecimal.valueOf(0.9))
    }
}

//example 2 https://www.journaldev.com/1754/strategy-design-pattern-in-java-example-tutorial
open interface PaymentStrategy {
    fun pay(amount: Int)
}

class CreditCardStrategy(
    private val name: String,
    private val cardNumber: String,
    private val cvv: String,
    private val dateOfExpiry: String
) :
    PaymentStrategy {
    override fun pay(amount: Int) {
        println("$amount paid with credit/debit card")
    }

}

class PaypalStrategy(private val emailId: String, private val password: String) : PaymentStrategy {
    override fun pay(amount: Int) {
        println("$amount paid using Paypal.")
    }

}

class Item(val upcCode: String, val price: Int)

class ShoppingCart {
    //List of items
    var items: MutableList<Item>
    fun addItem(item: Item) {
        items.add(item)
    }

    fun removeItem(item: Item) {
        items.remove(item)
    }

    fun calculateTotal(): Int {
        var sum = 0
        for (item in items) {
            sum += item.price
        }
        return sum
    }

    fun pay(paymentMethod: PaymentStrategy) {
        val amount = calculateTotal()
        paymentMethod.pay(amount)
    }

    init {
        items = ArrayList()
    }
}