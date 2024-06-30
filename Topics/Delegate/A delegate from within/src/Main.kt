
interface ILogHolder {
    fun printLog()
    fun collectLog(log: String)
    var curLog: String
}

class Logger : ILogHolder {
    // Print the current log
    override fun printLog() {
        println(curLog)
    }

    override fun collectLog(log: String) {
        curLog += "\n> $log"
    }

    override var curLog: String = "> Logger initialized."
}

class ErrorHandler(base: ILogHolder) : ILogHolder by base {
    private var errorMessage: String = ""

    fun getErrorMessage(msg: String) {
        errorMessage = msg
        logErrorMessage()
    }

    private fun logErrorMessage() {
        collectLog(errorMessage)
    }
}

fun doSomething(handler: ErrorHandler) {
    handler.getErrorMessage("Access denied")
    handler.getErrorMessage("Out of memory")
    handler.getErrorMessage("I'm not an error")
}

fun main() {
    val log = Logger()
    val handler = ErrorHandler(log)
    doSomething(handler)
    handler.printLog()
}
