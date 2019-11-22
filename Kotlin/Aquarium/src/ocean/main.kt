package ocean

fun main(args: Array<String>) {
    val dory = Fish("dory")

    with(dory.name) {
        println(capitalize())
    }

    funnyWith(dory.name) {
        println(toUpperCase())
    }

    println(dory.run { name })
    println(dory.apply {})

    val whale = Fish("Mr. Fish").apply { name = "Moby Dick" }
    println(whale.name)

    dory.let { it.name.capitalize() }
        .let { it + "Fish" }
        .let { it.length }
        .let { it + 31 }
        .let { println(it) }

}

inline fun funnyWith(name: String, block: String.() -> Unit) {
    name.block()
}

