fun main() = readLine()!!
    .toInt()
    .let {
        it * readLine()!!.toInt() + 
            2 * readLine()!!.toInt() +
            it.dec() * readLine()!!.toInt() 
    }
    .run(::print)

