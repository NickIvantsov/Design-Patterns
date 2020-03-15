package com.gmail.design_patterns.adapter

interface Movable {
    // returns speed in MPH
    val speed: Double
}

class BugattiVeyron : Movable {
    override val speed: Double
        get() = 268.00
}

interface MovableAdapter {
    // returns speed in KM/H
    val speed: Double
}

class MovableAdapterImpl(private val luxuryCars: Movable) : MovableAdapter {


    // standard constructors
    override val speed: Double
        get() = convertMPHtoKMPH(luxuryCars.speed)

    private fun convertMPHtoKMPH(mph: Double): Double {
        return mph * 1.60934
    }
}

//Example 2 https://www.journaldev.com/1487/adapter-design-pattern-java
class Volt(v: Int) {
    private var volts: Int
    fun getVolts(): Int {
        return volts
    }

    fun setVolts(volts: Int) {
        this.volts = volts
    }

    init {
        volts = v
    }
}

open class Socket {
    val volt: Volt
        get() = Volt(120)
}

interface SocketAdapter {
    fun get120Volt(): Volt?
    fun get12Volt(): Volt?
    fun get3Volt(): Volt?
}
//Шаблон проектирования адаптера - адаптер класса
//Using inheritance for adapter pattern
class SocketClassAdapterImpl : Socket(),
    SocketAdapter {
    override fun get120Volt(): Volt {
        return volt
    }

    override fun get12Volt(): Volt {
        val v: Volt = volt
        return convertVolt(v, 10)
    }

    override fun get3Volt(): Volt {
        val v: Volt = volt
        return convertVolt(v, 40)
    }

    private fun convertVolt(v: Volt, i: Int): Volt {
        return Volt(v.getVolts() / i)
    }
}
//Шаблон проектирования адаптера - реализация адаптера объекта
class SocketObjectAdapterImpl : SocketAdapter {
    //Using Composition for adapter pattern
    private val sock = Socket()

    override fun get120Volt(): Volt {
        return sock.volt
    }

    override fun get12Volt(): Volt {
        val v = sock.volt
        return convertVolt(v, 10)
    }

    override fun get3Volt(): Volt {
        val v = sock.volt
        return convertVolt(v, 40)
    }

    private fun convertVolt(v: Volt, i: Int): Volt {
        return Volt(v.getVolts() / i)
    }
}