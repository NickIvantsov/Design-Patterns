package com.gmail.design_patterns

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gmail.design_patterns.adapter.*
import com.gmail.design_patterns.bridge.Blue
import com.gmail.design_patterns.bridge.Shape
import com.gmail.design_patterns.bridge.Square
import com.gmail.design_patterns.builder.FoodOrder
import com.gmail.design_patterns.builder.Person
import com.gmail.design_patterns.factoryPattern.AnimalFactory
import com.gmail.design_patterns.factoryPattern.AnimalType
import com.gmail.design_patterns.factoryPattern.DoughnutFactory
import com.gmail.design_patterns.factoryPattern.DoughnutTypes
import com.gmail.design_patterns.observer.NewsAgency
import com.gmail.design_patterns.observer.NewsChannel
import com.gmail.design_patterns.proxy.CommandExecutor
import com.gmail.design_patterns.proxy.CommandExecutorProxy
import com.gmail.design_patterns.proxy.ExpensiveObject
import com.gmail.design_patterns.proxy.ExpensiveObjectProxy
import com.gmail.design_patterns.strategy.*
import java.math.BigDecimal


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun helloWorldPressed(view: View) {
        val factory = AnimalFactory()
        val animalTypes =
            listOf(
                AnimalType.DOG,
                AnimalType.DOG,
                AnimalType.CAT,
                AnimalType.DOG,
                AnimalType.CAT,
                AnimalType.CAT
            )
        for (t in animalTypes) {
            val c = factory.createAnimal(t)
            println("${c.id}	-	${c.name}")
        }


    }

    private fun strategy() {
         val easterDiscounter: Discounter = EasterDiscounter()

        val discountedValue: BigDecimal = easterDiscounter
            .applyDiscount(BigDecimal.valueOf(100))

        val easterDiscounter2: Discounter = object : Discounter {
            override fun applyDiscount(amount: BigDecimal): BigDecimal {
                return amount.multiply(BigDecimal.valueOf(0.5))
            }
        }

        val easterDiscounter3 = { amount: BigDecimal -> amount.multiply(BigDecimal.valueOf(0.5)) }

        println("discountedValue = $discountedValue")
        println("easterDiscounter2 = ${easterDiscounter2.applyDiscount(BigDecimal.valueOf(200))}")
        println("easterDiscounter3 = ${easterDiscounter3(BigDecimal.valueOf(300))}")

        val cart = ShoppingCart()

        val item1 = Item("1234", 10)
        val item2 = Item("5678", 40)

        cart.addItem(item1)
        cart.addItem(item2)

        //pay by paypal

        //pay by paypal
        cart.pay(PaypalStrategy("myemail@example.com", "mypwd"))

        //pay by credit card

        //pay by credit card
        cart.pay(CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15"))
    }

    private fun bridge() {
        //a square with red color
        val square: Shape = Square(Blue())
        println(square.draw())
    }

    private fun adapter() {
        val bugattiVeyron: Movable = BugattiVeyron()
        val bugattiVeyronAdapter: MovableAdapter = MovableAdapterImpl(bugattiVeyron)
        println("km speed = ${bugattiVeyronAdapter.speed}")

        testClassAdapter()
        testObjectAdapter()
    }

    private fun testObjectAdapter() {
        val sockAdapter: SocketAdapter = SocketObjectAdapterImpl()
        val v3 = getVolt(sockAdapter, 3)
        val v12 = getVolt(sockAdapter, 12)
        val v120 = getVolt(sockAdapter, 120)
        println("v3 volts using Object Adapter=" + v3!!.getVolts())
        println("v12 volts using Object Adapter=" + v12!!.getVolts())
        println("v120 volts using Object Adapter=" + v120!!.getVolts())
    }

    private fun testClassAdapter() {
        val sockAdapter: SocketAdapter = SocketClassAdapterImpl()
        val v3 = getVolt(sockAdapter, 3)
        val v12 = getVolt(sockAdapter, 12)
        val v120 = getVolt(sockAdapter, 120)
        println("v3 volts using Class Adapter=" + v3!!.getVolts())
        println("v12 volts using Class Adapter=" + v12!!.getVolts())
        println("v120 volts using Class Adapter=" + v120!!.getVolts())
    }

    private fun getVolt(sockAdapter: SocketAdapter, i: Int): Volt? {
        return when (i) {
            3 -> sockAdapter.get3Volt()
            12 -> sockAdapter.get12Volt()
            120 -> sockAdapter.get120Volt()
            else -> sockAdapter.get120Volt()
        }
    }

    private fun proxy() {
        val `object`: ExpensiveObject = ExpensiveObjectProxy()
        `object`.process()
        `object`.process()

        val executor: CommandExecutor = CommandExecutorProxy("Pankaj", "wrong_pwd")
        try {
            executor.runCommand("ls -ltr")
            executor.runCommand(" rm -rf abc.pdf")
        } catch (e: Exception) {
            println("Exception Message::" + e.message)
        }
    }

    private fun builder() {
        val person = Person.weight(23).age(25).surname("Surname")
        person.height(45).build()
        println(person.toString())

        val foodOrder = FoodOrder.Builder().bread("bread")
        foodOrder.fish("fish").build()
        println(foodOrder.toString())
    }

    private fun factory() {
        val factory = DoughnutFactory()

        val cherry = factory.getDoughnut(DoughnutTypes.CHERRY)
        val chocolate = factory.getDoughnut(DoughnutTypes.CHOCOLATE)
        val almond = factory.getDoughnut(DoughnutTypes.ALMOND)

        cherry.eat()
        chocolate.eat()
        almond.eat()
    }

    private fun observer() {
        val observable = NewsAgency()
        val observer = NewsChannel()
        val observer2 = NewsChannel()

        observable.addObserver(observer)
        observable.addObserver(observer2)
        observable.news = "news"

        observable.news = "news2"

        observable.news = "news3"
        /*val subject = Subject()

        HexObserver(subject)
        OctalObserver(subject)
        BinaryObserver(subject)

        println("First state change: 15")
        subject.state = 15
        println("Second state change: 10")
        subject.state = 10*/
    }
}
