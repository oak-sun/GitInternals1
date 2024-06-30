interface Deus {
    fun print()
    val amount: Int
}

class Agnus : Deus {
    override fun print() { for (i in 1..amount) print("*") }
    override val amount: Int = 3 + 7
}

class Sanctus : Deus {
    override fun print() { for (i in 1..amount) print("*") }
    override val amount: Int = 23 - 15
}

class Archangelus : Deus {
    override fun print() { for (i in 1..amount) print("*") }
    override val amount: Int = 3 * 4
}
class Angelus(base: Deus) : Deus by base {
    override fun print() { for (i in 1..amount + 2) print("*") }
}

fun main() = Angelus(Sanctus()).print()
