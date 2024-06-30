@SuppressWarnings("All")
fun main() {
    val desks = List(3) { 
        (readLine()!!.toInt() + 1) / 2 
    }.sum()
    println(desks)
}
