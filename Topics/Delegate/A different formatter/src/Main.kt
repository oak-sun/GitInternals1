interface Deus {
    fun print()
    var voxDei: String
}

class Human : Deus {
    override fun print() { println(voxDei) }
    override var voxDei: String = "To be, or not to be, that is the question:"
    fun updateMsg(newVox: String) { voxDei = newVox }
}

class Destiny(base: Deus) : Deus by base {
    private var start: String = voxDei
    private var end: String = "[${voxDei.length} characters]"
    override fun print() = println("$start $end")
}

fun main() {
    val line = readln()
    val human = Human()
    human.updateMsg(line)
    val delegate = Destiny(human)
    delegate.print()
}
