fun main() {
    val str = readlnOrNull()
    val index = readln().toInt()
    str?.let { 
        println(
            "Symbol # $index of the string" +
                " \"$it\" is '${it[index - 1]}'"
        )
    }
}
