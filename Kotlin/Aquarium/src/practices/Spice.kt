package practices

import aquariums.Color

//Fazer com que Spice seja uma classe selada,
// ajuda a manter todas as especiarias juntas em um único arquivo.
sealed class Spice (
    val name: String,
    val spiciness: String = "mild",
    color: IColor) : IColor by color {

    abstract fun main()
        abstract fun prepare()
}


interface IColor {
    val color: Color
}

object Yellow : IColor {
    override val color = Color.YELLOW
}

interface Grinder {
    fun grind()
}

class Curry(
    name: String,
    spiciness: String,
    color: IColor = Yellow) :
        Spice(name, spiciness, color),
        Grinder,
        IColor by color {

    override fun prepare() {
        grind()
    }

    override fun main() {
        println("main")
    }

    override fun grind() {
        println("grinded")
    }
}

data class SpicePot(val spice: Spice) {
    val label = spice.name
}