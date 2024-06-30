import kotlin.concurrent.thread

fun createThreadInNewState() = thread(start = false, block = {})
