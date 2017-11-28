data class SiteVisit(val path: String, val duration: Double, val os: OS)

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

//extension function
fun List<SiteVisit>.averageDuration(predicate: (SiteVisit) -> Boolean) = filter(predicate).map(SiteVisit::duration).average()

fun main(args: Array<String>) {
    val log = listOf(
            SiteVisit("/", 34.0, OS.WINDOWS),
            SiteVisit("/", 22.0, OS.MAC),
            SiteVisit("/login", 12.0, OS.WINDOWS),
            SiteVisit("/signup", 8.0, OS.IOS),
            SiteVisit("/", 16.3, OS.ANDROID)
    )

    //basic implementation
    val averageDuration = log.filter { it.os == OS.WINDOWS }
            .map(SiteVisit::duration)
            .average()

    println(averageDuration)
    println("----------------------")
    println(log.averageDuration { it.os == OS.WINDOWS })
    println(log.averageDuration { it.os == OS.MAC })
    println(log.averageDuration { it.os in setOf(OS.ANDROID, OS.IOS) })
    println(log.averageDuration { it.os == OS.WINDOWS && it.path == "/login" })
}