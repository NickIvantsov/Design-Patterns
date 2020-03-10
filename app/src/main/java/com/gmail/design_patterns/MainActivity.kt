package com.gmail.design_patterns

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gmail.design_patterns.observer.NewsAgency
import com.gmail.design_patterns.observer.NewsChannel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun helloWorldPressed(view: View) {
        /*val subject = Subject()

        HexObserver(subject)
        OctalObserver(subject)
        BinaryObserver(subject)

        println("First state change: 15")
        subject.state = 15
        println("Second state change: 10")
        subject.state = 10*/

        val observable = NewsAgency()
        val observer = NewsChannel()
        val observer2 = NewsChannel()

        observable.addObserver(observer)
        observable.addObserver(observer2)
        observable.news = "news"

        observable.news = "news2"

        observable.news = "news3"

    }
}
