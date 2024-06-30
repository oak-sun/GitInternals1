import java.util.*

fun main(args: Array<String>) {
    val input = Scanner(System.`in`).nextLine().trim()
    if (input != null && input.all { char -> char.isDigit() }) {
        val int = input.toIntOrNull()
        if (int != null && int in 0..9999) {    
            var sum = 0
            var temp = int
            while (temp != 0) {
                val digit = temp % 10
                sum += digit
                temp /= 10
            }
            print(sum)
        }
    } else {
        print("Invalid Input")
    }
}
