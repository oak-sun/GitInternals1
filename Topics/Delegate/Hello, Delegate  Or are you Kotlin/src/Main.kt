interface Printer {
    val message: String
    fun print()
}

class BasicConsole(private val x: String, private val y: String) : Printer {
    override val message: String
        get() = "$x $y"

    override fun print() {
        print("Hello Kotlin")
    }
}

class DerivedConsole(base: Printer) : Printer by base {
    override fun print() {
        print(message)
    }
}

fun main() {
    val hello = "Hello"
    val delegate = "Delegate"
    val basicConsole = BasicConsole(hello, delegate)
    val printer = DerivedConsole(basicConsole)
    printer.print()
}
