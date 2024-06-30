@Suppress("all")
fun main() = readln()
    .split(" ")
    .map { it.toDouble() }
    .let { l -> "${l.firstOrNull { it >= 5.0 }}:${l.maxOrNull()}" }
    .let(::println)
