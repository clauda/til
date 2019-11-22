package practices

class Fish(private val friendly: Boolean = true, volumeNeeded: Int) {

    private val size: Int


    init {
        println("primary constructor")
        size = if (friendly) volumeNeeded else volumeNeeded * 2
    }

    init { println(volumeNeeded) }
    init { println("last init block") }

    // Secondary
    constructor() : this(true, 9) {
        println("secondary constructor")
    }
}