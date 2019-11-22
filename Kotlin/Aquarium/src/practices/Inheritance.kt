package practices

open class BaseBook(
    open val title : String,
    open val author : String) {
    private var currentPage = 0

    open fun readPage() { currentPage++ }

}

class EBook(
    val format : String = "text",
    override val title: String,
    override val author: String) : BaseBook(title, author) {

    private var wordsRead = 0

    override fun readPage() { wordsRead += 250 }
}