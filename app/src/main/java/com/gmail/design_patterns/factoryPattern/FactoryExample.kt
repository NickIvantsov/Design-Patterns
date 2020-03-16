package com.gmail.design_patterns.factoryPattern

import com.gmail.design_patterns.factoryPattern.AnimalType.CAT
import com.gmail.design_patterns.factoryPattern.AnimalType.DOG
import com.gmail.design_patterns.factoryPattern.DoughnutTypes.*

//res url: https://vertex-academy.com/tutorials/ru/pattern-factory-java/
//Step 1
class DoughnutFactory {

    fun getDoughnut(type: DoughnutTypes): Doughnut {
        return when (type) {
            CHERRY -> CherryDoughnut()
            CHOCOLATE -> ChocolateDoughnut()
            ALMOND -> AlmondDoughnut()
        }
    }
}

//Step 2

interface Doughnut {
    fun eat()
}

//Step 3
class CherryDoughnut : Doughnut {
    override fun eat() {
        println("You are eating Cherry doughnut!")
    }
}

class ChocolateDoughnut : Doughnut {
    override fun eat() {
        println("You are eating Chocolate doughnut!")
    }
}

class AlmondDoughnut : Doughnut {
    override fun eat() {
        println("You are eating Almond doughnut!")
    }
}

//Step 4
enum class DoughnutTypes {
    CHERRY, CHOCOLATE, ALMOND
}

//example 2
class Cat(override val id: Int) : Animal {
    override val name: String = "Cat"
}

enum class AnimalType {
    CAT, DOG
}

class AnimalFactory {
    var counter = 0
    fun createAnimal(animalType: AnimalType): Animal {
        return when (animalType) {
            CAT -> Cat(++counter)
            DOG -> Dog(++counter)
            else -> throw    RuntimeException("Unknown	animal	$animalType")
        }
    }
}


interface Animal {
    val id: Int
    val name: String
}

class Dog(override val id: Int) : Animal {
    override val name = "Dog"
}

//Static Factory Method
class NumberMaster {
    companion object {
        fun valueOf(hopefullyNumber: String): Long {
            return hopefullyNumber.toLong()
        }
    }
}