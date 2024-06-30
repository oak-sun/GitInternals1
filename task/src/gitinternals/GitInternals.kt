package gitinternals

import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toKotlinInstant
import java.io.File
import java.io.FileInputStream
import java.util.zip.InflaterInputStream
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.LinkedList

fun main() {
    GitInternals()
}

class GitInternals {
    private val location: String

    init {
        println("Enter .git directory location:")
        location = readln().also { println("Enter command:") }
        when (readln()) {
            "list-branches" -> listBranches()
            "cat-file" -> println("Enter git object hash:").also { catFile(getFileContents(readln())) }
            "log" -> println("Enter branch name:").also { log(readln()) }
            "commit-tree" -> println("Enter commit-hash:").also { commitTree(decodeCommit(getFileContents(readln()).innerContent).tree) }
        }
    }

    private fun getFileContents(objectHash: String) = InflaterInputStream(FileInputStream("$location/objects/${objectHash.take(2)}/${objectHash.drop(2)}"))
        .let { stream -> stream.readAllBytes().let { bytes ->
            val stringContents = bytes.toString(Charsets.UTF_8)
            val (type, innerContent) = stringContents.let {
                listOf(it.substring(0, it.indexOf('\u0000')).split(" ")[0], it.drop(it.indexOf('\u0000') + 1)) }
            stream.close()
            FileInfo(bytes, type, innerContent, stringContents)
        } }

    private fun changeDataToReadable(string: String, prefix: String): String {
        val (beforeDate, afterDate) = string.split("(?<=>)\\s".toRegex())
        val (epoch, diff) = afterDate.split(" ")
        val finalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss XXX").withZone(ZoneOffset.of(diff)).format(
            Instant.ofEpochSecond(epoch.toLong()).toKotlinInstant().toJavaInstant())
        return "${beforeDate.replace("[<>]".toRegex(), "")} $prefix timestamp: $finalDate"
    }

    private fun decodeCommit(content: String): Commit {
        val commit = Commit()
        content.split("\n").map { it.trim() }.filter { it.isNotBlank() && it.isNotEmpty() }.forEach {
            when (true) {
                it.startsWith("tree") -> commit.tree = it.drop("tree".length + 1)
                it.startsWith("parent") -> commit.parents.add(it.drop("author".length + 1))
                it.startsWith("author") -> commit.author = changeDataToReadable(it.drop("author".length + 1), "original")
                it.startsWith("committer") -> commit.committer = changeDataToReadable(it.drop("committer".length + 1), "commit")
                else -> commit.commitMessage += "$it\n"
            }
        }

        return commit
    }


    private data class Tree(val result: String, val hashes: Map<String, String>)


    private fun decodeTree(content: List<Byte>): Tree {
        var result = ""; var fileInfo = ""
        var count = 0; var byteCount = 0
        var foundHash = ""
        val hashes = mutableMapOf<String, String>()
        content.forEach { inputChar ->
            when (count) {
                0 -> {
                    if (inputChar.toInt().toChar() == ' ') count++
                    result += inputChar.toInt().toChar().toString()
                }
                1 -> if (inputChar.toInt().toChar() == 0.toChar()) count++ else fileInfo += inputChar.toInt().toChar().toString()
                2 -> {
                    String.format("%02x", inputChar).lowercase().also { foundHash += it; result += it }
                    if (++byteCount == 20) {
                        hashes[fileInfo] = foundHash
                        result += " $fileInfo\n"; fileInfo = ""
                        count = 0; byteCount = 0; foundHash = ""
                    }
                }
            }
        }
        return Tree(result, hashes)
    }

    private fun catFile(info: FileInfo) = println(
        "*${info.type.uppercase()}*\n" + when (info.type) {
            "blob" -> info.innerContent
            "commit" -> decodeCommit(info.innerContent)
            "tree" -> decodeTree(info.getTruncatedBytes()).result
            else -> ""
        }
    )


    private fun listBranches() {
        val branches = File("$location/refs/heads").list()?.sorted()
        println(
            branches?.joinToString("\n") {
                if (it == File("$location/HEAD").readText().split("/").last().trim()) "* $it" else "  $it"
            } ?: "No branches found"
        )
    }

    private fun log(branchName: String) {
        val hashes = LinkedList<Pair<String, Boolean>>().also {
            it.add(Pair(File("$location/refs/heads/$branchName").readText().trim(), true)) }
        while (hashes.isNotEmpty()) {
            val (hash, leavesChildren) = hashes.poll()
            println("Commit: $hash${if (!leavesChildren) " (merged)" else ""}\n${decodeCommit(getFileContents(hash).innerContent).let { commit ->
                if (leavesChildren) {
                    if (commit.parents.size == 2) {
                        hashes.add(Pair(commit.parents[1], false))
                        hashes.add(Pair(commit.parents[0], true))
                    } else hashes.addAll(commit.parents.map { Pair(it, true) })
                }
                "${commit.committer}\n${commit.commitMessage}"
            }}")
        }
    }

    private fun commitTree(hash: String, line: String = "") {
        for ((name, localHash) in decodeTree(getFileContents(hash).getTruncatedBytes().toList()).hashes) {
            val fileName = if (line == "") name else "$line/$name"
            if (getFileContents(localHash).type == "tree") commitTree(localHash, fileName) else println(fileName)
        }
    }
}

data class FileInfo(val bytes: ByteArray, val type: String, val innerContent: String, val stringContents: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as FileInfo
        if (!bytes.contentEquals(other.bytes)) return false
        if (type != other.type) return false
        if (innerContent != other.innerContent) return false
        if (stringContents != other.stringContents) return false
        return true
    }

    override fun hashCode(): Int {
        var result = bytes.contentHashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + innerContent.hashCode()
        result = 31 * result + stringContents.hashCode()
        return result
    }

    fun getTruncatedBytes(): List<Byte> = bytes.drop(stringContents.indexOf('\u0000') + 1)
}

data class Commit(
    var committer: String = "",
    var author: String = "",
    val parents: MutableList<String> = mutableListOf(),
    var tree: String = "",
    var commitMessage: String = "") {

    override fun toString() = "tree: $tree\n${if (parents.size > 0) "parents: " else ""}${parents.joinToString(separator = " | ")}" +
                "${if (parents.size > 0) "\n" +
                        "" else ""}author: $author\n" +
            "committer: $committer\ncommit message:\n${commitMessage.trim()}"

}
