data class Ship(val name: String, val ammunition: Int)

fun main() {
    val names = readlnOrNull()?.split(" ") ?: emptyList()
    val res = names.filter { it.split("-")[1].toInt() > 200 }
    if (res.isEmpty()) { println("No ship found") }
    else { println(res.last().split("-")[0]) }
}