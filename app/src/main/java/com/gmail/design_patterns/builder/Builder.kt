package com.gmail.design_patterns.builder


class Person private constructor(builder: Builder) {

    val name: String?
    val surname: String?
    val age: Int?
    val height: Int?
    val weight: Int?
    val parents: Set<Person>?

    init {
        name = builder.name
        surname = builder.surname
        age = builder.age
        height = builder.height
        weight = builder.weight
        parents = builder.parents
    }

    companion object Builder {
        var name: String? = null
            private set
        var surname: String? = null
            private set
        var age: Int? = null
            private set
        var height: Int? = null
            private set
        var weight: Int? = null
            private set
        var parents: Set<Person>? = null
            private set

        fun name(name: String) = apply { this.name = name }
        fun surname(surname: String) = apply { this.surname = surname }
        fun age(age: Int) = apply { this.age = age }
        fun height(height: Int) = apply { this.height = height }
        fun weight(weight: Int) = apply { this.weight = weight }
        fun parents(parents: Set<Person>) = apply { this.parents = parents }
        fun build() = Person(this)
    }

    override fun toString(): String {
        return "Person(name=$name, surname=$surname, age=$age, height=$height, weight=$weight, parents=$parents)"
    }

}

class FoodOrder private constructor(
    val bread: String?,
    val condiments: String?,
    val meat: String?,
    val fish: String?
) {

    data class Builder(
        var bread: String? = null,
        var condiments: String? = null,
        var meat: String? = null,
        var fish: String? = null
    ) {

        fun bread(bread: String) = apply { this.bread = bread }
        fun condiments(condiments: String) = apply { this.condiments = condiments }
        fun meat(meat: String) = apply { this.meat = meat }
        fun fish(fish: String) = apply { this.fish = fish }
        fun build() = FoodOrder(bread, condiments, meat, fish)
    }

    override fun toString(): String {
        return "FoodOrder(bread=$bread, condiments=$condiments, meat=$meat, fish=$fish)"
    }

}