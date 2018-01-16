import java.util.*

fun main(args: Array<String>) {
    val pc = PerformanceChecker()
    val ar1 = IntArray(10000).populateArray(10000)

    //block approach
    val random = Random()
    val randomBound = 10
    val ar2 = IntArray(12) {
        random.nextInt(randomBound)
    }


    println(Arrays.toString(ar1))
    println(Arrays.toString(ar2))
    println(pc.checkPerf(Quicksort()::sort, ar1))
}

//extension function approach
fun IntArray.populateArray(randomBound: Int): IntArray {
    val rnd = Random()
    (0 until this.size).forEach { this[it] = rnd.nextInt(randomBound) }
    return this
}