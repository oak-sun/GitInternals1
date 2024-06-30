data class Spell(val name: String, val power: Int)

@Suppress("all")
fun main() =
    println(
        readln().split(" ")
            .map { 
                Spell(
                    it.split("-")[0],
                    it.split("-")[1].toInt()
                ) 
            }
            .find { it.power > 50 }
            ?: "No spell found"
    )
