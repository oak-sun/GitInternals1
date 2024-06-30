@SuppressWarnings("All")
fun main() {
    val (h1, m1, s1) = Array(3) { readLine()!!.toInt() }
    val (h2, m2, s2) = Array(3) { readLine()!!.toInt() }
    print(Math.abs(h1 * 3600 + m1 * 60 + s1 - (h2 * 3600 + m2 * 60 + s2)))
}
