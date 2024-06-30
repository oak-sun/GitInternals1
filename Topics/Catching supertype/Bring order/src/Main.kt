@Suppress("All")
fun main() = try { println(100 / readln().toInt()) } 
catch (e: Exception) {
    println(
        when (e) {
            is NumberFormatException -> "You did not enter a valid integer!"
            is ArithmeticException -> "You cannot divide by zero!"
            else -> "An unknown error has occurred!"
        }
    )
}
