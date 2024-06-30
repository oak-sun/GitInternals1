fun main() {
    val dividend: Int = readln().toInt()
    val divisor: Int = readln().toInt()
    print(
        "The quotient is: ${dividend.floorDiv(divisor)} " +
            "and the remainder is: ${dividend.mod(divisor)}"
    )
}
