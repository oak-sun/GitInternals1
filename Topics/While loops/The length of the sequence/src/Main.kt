fun main() {
    var sequence = 0
    while (true) {
        val input = readln().toInt()
        if (input == 0) {
            break
        }
        sequence++
    }

    println(sequence)
}
