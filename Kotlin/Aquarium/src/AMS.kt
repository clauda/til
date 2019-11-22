import java.util.*

fun main(args: Array<String>) {
    feedTheFish()

//    println(canAddFish(10.0, listOf(3,3,3)))
//    println(canAddFish(8.0, listOf(2,2,2), hasDecorations = false))
//    println(canAddFish(9.0, listOf(1,1,3), 3))
//    println(canAddFish(10.0, listOf(), 7, true))

    waterProcessor()
}

fun feedTheFish() {
    val wday : String = randomDay()
    val food = fishFood(wday)
    println("today is $wday and my fish eats $food")
    if (shouldChangeWater(wday, Random().nextInt(32), Random().nextInt(50))) {
        println("Change the water today")
    }
}

fun randomDay() : String {
    val weekdays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    return weekdays[Random().nextInt(7)]
}

fun fishFood(wday: String): String {
    return when (wday) {
        "Mon" -> "flakes"
        "Tue" -> "pellets"
        "Thu" -> "redworms"
        "Wed" -> "granules"
        "Fri" -> "mosquitoes"
        "Sat" -> "lettuce"
        "Sun" -> "plankton"
        else -> "fasting"
    }
}

fun swin(speed: String = "fast") {
    println("Swimming $speed")
}

fun shouldChangeWater(
    wday: String,
    temperature: Int,
    dirty: Int = 20
): Boolean {

    val isTooHot = temperature > 30
    val isDirty = dirty > 30
    val isSunday = wday == "Sun"

    return when {
        isTooHot -> true
        isDirty -> true
        isSunday -> true
        else -> false
    }
}

fun canAddFish(
    tankSize: Double,
    fishes: List<Int>,
    newFish: Int = 2,
    hasDecorations: Boolean = true) : Boolean {

    val shoal = fishes + newFish
    val realTankSize = if (hasDecorations) tankSize * 0.8 else tankSize

    return shoal.sum() <= realTankSize;
}

//fun canAddFishOficial(
//    tankSize: Double,
//    currentFish: List<Int>,
//    fishSize: Int = 2,
//    hasDecorations: Boolean = true): Boolean {
//    return (tankSize * if (hasDecorations) 0.8 else 1.0) >= (currentFish.sum() + fishSize)
//}


