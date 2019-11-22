package practices

import java.util.*

const val MAX_NUMBER_BOOKS = 2

class Book(
    val title : String,
    val author : String,
    val year : Int,
    var pages : Int = 10
) {

    fun summary() : Pair<String, String> {
        return (title to author)
    }

    fun description() : Triple<String, String, Int> {
        return Triple(title, author, year)
    }

    fun canBarrow(barrowBooks : Int): Boolean {
        return (barrowBooks >= MAX_NUMBER_BOOKS)
    }

    fun url() {
        println("${Constants.BASE_URL}/$title.html")
    }

}

object Constants {
    const val BASE_URL = "http://locahost"
}

fun Book.weigth() : Double { return (pages * 1.5) }
fun Book.tornPages(torn : Int) = if (pages >= torn) pages -= torn else pages = 0
class Puppy() {
    fun playWithBook(book: Book) {
        book.tornPages(Random().nextInt(12))
    }
}