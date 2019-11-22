
fun main() {
    val birthday = getBirthday()
    val fortune = birthday?.let { getFortuneCookie(it) }
    println("\nYour fortune is: $fortune")
}

fun getFortuneCookie(birthday : String = "1"): String {
    val fortunes = listOf("You will have a great day!",
        "Things will go well for you today.",
        "Enjoy a wonderful day of success.",
        "Be humble and all will turn out well.",
        "Today is a good day for exercising restraint.",
        "Take it easy and enjoy life!",
        "Treasure your friends because they are your greatest fortune.")

    val cookie = birthday.toInt() % fortunes.size;
    return fortunes[cookie]
}

fun getBirthday() : String? {
    print("Enter you birthday: ")
    val birthday = readLine()
    println(birthday)
    return birthday
}