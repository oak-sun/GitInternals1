fun main() {
    val product = readln().toInt() * readln().toInt()
    println(
        if (product == 0) "It can't be zero!" else product
    )
}
