fun main() = generateSequence { 
    readLine()!!
        .toInt() 
}
    .takeWhile { it != 0 }
    .sum()
    .run(::print)
