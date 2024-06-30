fun main() = println(
    message = try {
        readlnOrNull()?.let { num ->
            num.toInt().let {
                when {
                    it < 0 -> "The number must be non-negative."
                    it == 0 -> 0
                    else -> it * it
                }
            }
        } ?: throw NumberFormatException()

    } catch (e: Exception) {
        when (e) {
            is NumberFormatException -> "The input must be a valid integer."
            else -> "Unknown error has happened!"
        }
    }
)
