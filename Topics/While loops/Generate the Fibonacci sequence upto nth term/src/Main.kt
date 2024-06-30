import java.util.*

fun main(args: Array<String>) = println(
    generateSequence({ 0 to 1 }) { it.second to it.first + it.second }
        .map { it.first }
        .take(readln().toInt()).joinToString()
)
    
