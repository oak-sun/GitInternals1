/* Do not change code below */
fun main() = println(
    readln()
        .split(" ")
        .find { it.endsWith("m") }
        ?.reversed()
)
