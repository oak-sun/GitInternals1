@SuppressWarnings("All") 
fun main() = (System.currentTimeMillis() / 1000)
    .let { time -> 
        print("${time / 3600 % 24 }:${time % 3600 / 60 }:${time % 3600 % 60}")
    }
