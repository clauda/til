package aquariums

import aquariums.generics.genericIt

fun main(args: Array<String>) {
//    buildAquarium()
//    buildFish()

//    genericIt()
    reflections()
}

fun buildAquarium() {
    val aquarium = Aquarium()
    println(aquarium.volume)
    aquarium.height = 80
    println("Aquarium ${aquarium.volume} liters")

    val smallAquarium = Aquarium(length = 20, width = 15, height = 30)
    println("Small Aquarium ${smallAquarium.volume} liters")

    val superAquarium = Aquarium(fishCounter = 9)
    println("Populate Aquarium ${superAquarium.volume} liters")
}

fun buildFish() {
    val shark = Shark()
    val pleco = Plecostomus()

    println("Shark ${shark.color} \n Plecostomus ${pleco.color}")
    shark.eat()

    feedFish(pleco)
}

fun feedFish(fish: IMarine) {
    fish.eat()
}

//val fish = Fish(true, 20)
//val commonFish = Fish()
//println(fish.size)
//println(commonFish.size)