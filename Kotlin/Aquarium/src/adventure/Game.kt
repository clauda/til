package adventure


class Game {
    var path = mutableListOf<Direction>(Direction.START)

    val north = { path.add(Direction.NORTH) }
    val south = { path.add(Direction.SOUTH) }
    val east =  { path.add(Direction.EAST) }
    val west =  { path.add(Direction.WEST) }

    val end = { path.add(Direction.END); println("Game Over: $path"); path.clear(); false }

    fun move(where: () -> Boolean) : Unit {
        where.invoke()
    }

    fun makeMove(move: String?) : Unit {
        when(move) {
            "n" -> move(north)
            "s" -> move(south)
            "e" -> move(east)
            "w" -> move(west)
            else -> move(end)
        }
    }
}