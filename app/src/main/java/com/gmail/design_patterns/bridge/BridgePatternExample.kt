package com.gmail.design_patterns.bridge

interface Color {
    fun fill(): String?
}

class Blue : Color {
    override fun fill(): String? {
        return "Color is Blue"
    }
}

abstract class Shape(protected var color: Color) {

    //standard constructors
    abstract fun draw(): String?
}

class Square(color: Color) :
    Shape(color) {
    override fun draw(): String? {
        return "Square drawn. " + color.fill()
    }
}