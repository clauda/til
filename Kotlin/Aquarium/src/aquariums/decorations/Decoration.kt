package aquariums.decorations

fun main(args: Array<String>) {
    decorate()
}

fun decorate() {
    val granite = Decoration("granite", "ype")
    println(granite)
    val slate = Decoration("slate", "no-ype")
    println(slate)
    val (rocks, wood) = slate
    println("Rocks $rocks and Wood $wood")
}

data class Decoration(val rocks: String, val wood: String)