package com.gmail.design_patterns.observer

//region example 1
//Step 1
class Subject {
    var state: Int = 0
        set(value) {
            field = value
            notifyAllObservers()
        }
    private val observers = ArrayList<Observer>()

    fun attach(observer: Observer) {
        observers.add(observer)
    }

    fun notifyAllObservers() {
        observers.forEach { it.update() }
    }
}

//Step 2
abstract class Observer {
    protected lateinit var subject: Subject
    abstract fun update()
}

//Step 3
class BinaryObserver(subject: Subject) : Observer() {
    override fun update() {
        println("Binary String: " + Integer.toBinaryString(subject.state))
    }

    init {
        this.subject = subject
        this.subject.attach(this)
    }
}

class OctalObserver(subject: Subject) : Observer() {
    override fun update() {
        println("Octal String: " + Integer.toOctalString(subject.state))
    }

    init {
        this.subject = subject
        this.subject.attach(this)
    }
}

class HexObserver(subject: Subject) : Observer() {
    override fun update() {
        println("Hex String: " + Integer.toHexString(subject.state).toUpperCase())
    }

    init {
        this.subject = subject
        this.subject.attach(this)
    }
}
//endregion

//region Example 2
class NewsAgency {
    val channels = ArrayList<Channel>()
    var news = ""
        set(value) {
            field = value
            channels.forEach { it.update(field) }
        }

    fun removeObserver(channel: Channel) {
        channels.remove(channel)
    }

    fun addObserver(channel: Channel) {
        this.channels.add(channel)
    }
}

interface Channel {
    fun update(o: Any)
}

class NewsChannel : Channel {
    private var news = ""
    override fun update(o: Any) {
        news = o.toString()
        println("news = $news")
    }

}
//endregion
