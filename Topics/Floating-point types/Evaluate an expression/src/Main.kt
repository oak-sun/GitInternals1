const val ONE = 10.5
const val TWO = 4.4
const val THREE = 2.2

fun main() = List(4) { readln().toDouble() }.let {
    print((it[0].times(ONE) + it[1].times(TWO) + (it[2].plus(it[3])) / THREE))
}
