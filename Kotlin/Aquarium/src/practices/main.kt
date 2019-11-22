package practices

fun main() {
//    spicy()
//    playingWithPairs()
    lostInTheLibrary()
}

fun spicy() {
    val pots = listOf(
        SpicePot(Curry("Yellow Curry", "mild")),
        SpicePot(Curry("Red Curry", "medium")),
        SpicePot(Curry("Green Curry", "spicy")))

    for(element in pots) println(element.label)
}

fun playingWithPairs() {
    val equipament = "fishnet" to "catching fish"
    println("The ${equipament.first} is a tool for ${equipament.second}")

    println(equipament.toString())
    println(equipament.toList())

    val (tool, useful) = giveMeATool()
    println(tool)
    println(useful)
}

fun giveMeATool() : Pair<String, String> {
    return ("fishnet" to "catching")
}

fun lostInTheLibrary() {
    val book = Book(author = "Svetlana Isakova", title = "Kotlin in Action", year = 2017)
    val summary = book.summary()
    val description = book.description()

    println("Here is your book ${summary.first} by ${summary.second}")
    println("Here is your book ${description.first} " +
            "by ${description.second} written in ${description.third}")

    val moreBooks = mapOf<String, String>("Wilhelm Tell" to "Schiller")
    moreBooks.getOrElse("Jungle Book") { "Kipling" }
    moreBooks.getOrElse("Hamlet") { "Shakespeare" }
    println(moreBooks)

    println(book.canBarrow(3))
    book.url()

    val puppy = Puppy()
    val dickens = Book("Oliver Twist", "Charles Dickens", 1837, 540)
    puppy.playWithBook(book)
    println("${dickens.pages} left in ${dickens.title}")
    println("Sad puppy, no more pages in ${dickens.title}. ")
}