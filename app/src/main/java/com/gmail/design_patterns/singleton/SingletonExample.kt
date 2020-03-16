package com.gmail.design_patterns.singleton

import java.util.concurrent.atomic.AtomicInteger

object SingletonExample {
    init {
        println("I	was	accessed	for	the	first	time")
    }

    private val counter = AtomicInteger(0)
    fun increment() = counter.incrementAndGet()
}