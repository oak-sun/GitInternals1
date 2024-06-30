fun main() = repeat(3) { Thread(RunnableWorker(), "worker-$it").start() }
class RunnableWorker : Runnable {
    override fun run() {
        if (Thread.currentThread().name.startsWith("worker-")) { println("too hard calculations...") }
        else { return }
    }
}
