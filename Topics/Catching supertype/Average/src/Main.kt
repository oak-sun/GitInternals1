
fun main() = println(
    try {
        readln()
            .run { require(isNotEmpty()).let { split(",").map { it.toInt() }.average() } }
    } catch (e: NumberFormatException) {
        "Invalid input!"
    } catch (e: IllegalArgumentException) {
        "Cannot calculate average of an empty list!"
    } catch (e: Exception) { "An error occurred!" }
)
