import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`)
    var balance = readln().toInt()
    while (sc.hasNextInt()) {
        val payment = sc.nextInt()
        if (payment > balance) {
            println("Error, insufficient funds for the purchase. Your balance is $balance, but you need $payment.")
            return
        }
        balance -= payment
    }
    println("Thank you for choosing us to manage your account! Your balance is $balance.")
}

