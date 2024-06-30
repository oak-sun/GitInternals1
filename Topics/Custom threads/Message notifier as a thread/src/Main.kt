class MessageNotifier(private val message: String, private val repetitions: Int) : Thread() {
    override fun run() = repeat(repetitions) { println(message) }
}
