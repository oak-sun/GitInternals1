
class Box<T> (private var furniture: T, private var volume: Int) {
    fun getBoxVolume(): Int { return volume }
    fun getFurnitureFromBox(): T { return furniture }
}

class Fridge
class Armchair