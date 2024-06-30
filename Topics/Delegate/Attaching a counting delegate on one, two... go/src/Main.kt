interface Counter {
    var count: Int
    fun incrementCount()
    fun getCurrentCount(): Int
}

class CounterImpl(override var count: Int) : Counter {
    override fun incrementCount() { count++ }
    override fun getCurrentCount(): Int = count
}

class Iterator(base: Counter) : Counter by base { fun next() = incrementCount() }

fun main() {
    val iterat = Iterator(CounterImpl(readln().toInt()))
    for (i in 1..5) { iterat.next() }
    println(iterat.getCurrentCount())
}
