fun main() {
    val input = readln().toInt()
    var c = 1
    while (c * c <= input) {
        println(c * c)
        c++
    }
}
