interface Animal {
    val numberOfLimbs: Int
    fun move()
    fun communicate(): String
}
fun bark() = "bark"
fun meow() = "meow"
fun speak() = "speak"

fun walk() = println("walk")
fun fly() = println("fly") 
fun swim() = println("swim")

class Dog : Animal {
    override val numberOfLimbs: Int
        get() = 4

    override fun move() = walk()
    override fun communicate(): String {
        return bark()
    }
}
