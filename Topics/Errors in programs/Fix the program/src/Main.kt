@SuppressWarnings("MagicNumber")
fun main() {
    Array(3) { 
        readln().toInt() 
    }
        .sum()
        .let { println(it) }
}
