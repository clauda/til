import java.util.*

// LAMBDAS

fun main(args: Array<String>) {
    waterProcessor()
    gamePlay(rollDiceWithZero(4))
}

var dirty = 20

val waterFilter : (Int) -> Int = { dirty -> dirty / 10 }
fun waterPollute(dirty: Int) = dirty + 10

fun updateWater(dirty: Int, operation: (Int) -> Int) : Int {
    return operation(dirty)
}

fun waterProcessor() {
    dirty = updateWater(dirty, waterFilter)
    dirty = updateWater(dirty, ::waterPollute)
    dirty = updateWater(dirty) { it + 50 }
}


val rollDice = { sides: Int ->
    Random().nextInt(sides) + 1
}

val rollDiceWithZero: (Int) -> Int = {
    if (it == 0) 0
    else Random().nextInt(it) + 1
}

fun gamePlay(dice: Int) {
    println(dice)
}