import kotlin.math.pow

fun main() {
    val x = readln().toDouble()
    print(x.pow(3).plus(x.pow(2)).plus(x).plus(1))
}
