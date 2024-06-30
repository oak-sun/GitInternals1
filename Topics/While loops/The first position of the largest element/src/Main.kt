fun main() {
    val seq = mutableListOf<Int>()
    while (true) {
        val n = readLine() ?: break
        seq.add(n.toInt())
    }
    println(
        "${seq.maxOrNull()} ${seq.indexOf(seq.maxOrNull()) + 1}"
    )
}
