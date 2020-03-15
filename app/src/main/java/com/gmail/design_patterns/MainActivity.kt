package com.gmail.design_patterns

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gmail.design_patterns.builder.FoodOrder
import com.gmail.design_patterns.builder.Person
import com.gmail.design_patterns.factoryPattern.DoughnutFactory
import com.gmail.design_patterns.factoryPattern.DoughnutTypes
import com.gmail.design_patterns.observer.NewsAgency
import com.gmail.design_patterns.observer.NewsChannel
import com.gmail.design_patterns.proxy.CommandExecutor
import com.gmail.design_patterns.proxy.CommandExecutorProxy
import com.gmail.design_patterns.proxy.ExpensiveObject
import com.gmail.design_patterns.proxy.ExpensiveObjectProxy


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun helloWorldPressed(view: View) {

    }

    private fun proxy() {
        /*val `object`: ExpensiveObject = ExpensiveObjectProxy()
        `object`.process()
        `object`.process()*/

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
