fun main() = generateSequence {
    readLine()!!
        .toInt()
        .takeIf { it != 0 }
}
    .sorted()
    .last()
    .let { print(it) }

