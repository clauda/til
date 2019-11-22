package aquariums

interface IMarine {
    fun eat()
    fun swin() {
        println("Swimming....")
    }
}

interface IColorful {
    val color: String
}

abstract class Fish : IMarine {
    abstract val color: String
}

class Shark: Fish() {
    override val color = "grey"

    override fun eat() {
        println("hunt and eat fish")
    }

}

class Plecostomus: Fish() {
    override val color = "gold"

    override fun eat() {
        println("algae")
    }
}

object GoldColor : IColorful {
    override val color = "gold"
}

class GoldenMarineHorse(
    color: IColorful = GoldColor
    ) : IMarine by HungryFish("a lot of algae"),
        IColorful by color

object RedColor : IColorful {
    override val color = "red"
}

class HungryFish(private val food: String) : IMarine {
    override fun eat() {
        println(food)
    }
}

// Singleton
object MobyDickWhale {
    val author = "Herman Melville"

    fun jump() {

    }
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFFFF00)
}

sealed class Seal

class SeaLion: Seal()
class Walrus: Seal()

fun matchSeal(seal: Seal) : String {
    return when(seal) {
        is Walrus -> "Walrus"
        is SeaLion -> "SealLion"
    }
}

