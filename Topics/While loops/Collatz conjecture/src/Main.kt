@SuppressWarnings("MagicNumber")
fun main() {
    var x = readln().toInt()
    while (x != 1) {
        print("$x ")
        x = if (x % 2 == 0) {
            x / 2 
        } else {
            x * (2 + 1) + 1
        }
    }
    print(x)
}
