import kotlin.system.measureTimeMillis

class PerformanceChecker {
    fun checkPerf(sort: (IntArray) -> Unit, ar: IntArray): Long {
        return measureTimeMillis {
            sort(ar)
        }
    }
}