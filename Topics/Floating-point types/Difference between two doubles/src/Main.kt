

fun main() = List(2) { readln().toDouble() }.let {
    print(it[1].minus(it[0]))
}
