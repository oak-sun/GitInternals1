fun main() = repeat(2) { WorkerThread("worker-$it").start() }

class WorkerThread(name: String) : Thread(name) {
    override fun run() {
        if (!currentThread().name.startsWith("worker-")) { return }
        for (i in 0 until 3) { println("do something...") }
    }
}
