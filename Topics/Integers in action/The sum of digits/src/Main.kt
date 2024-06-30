fun main() {
    println(
        readLine()!!
            .map { it.digitToInt() }
            .sum()
    )
}
