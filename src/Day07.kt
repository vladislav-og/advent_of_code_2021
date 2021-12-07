
fun main() {

    fun readCrubsAndFindMinMax(inputList: List<String>): Triple<List<Int>, Int, Int> {
        val crubs: List<Int> = inputList.first().split(",").map { it.toInt() }
        val max = crubs.maxByOrNull { it }!!
        val min = crubs.minByOrNull { it }!!
        return Triple(crubs, max, min)
    }

    fun part1(inputList: List<String>): Int {
        val (crubs: List<Int>, max, min) = readCrubsAndFindMinMax(inputList)

        val countList = mutableListOf<Int>()

        for (i in min until max + 1) {
            var count = 0
            for (crub in crubs) {
                count += kotlin.math.abs(crub - i)
            }
            countList.add(count)
        }
        return countList.minByOrNull { it }!!
    }

    fun arithmeticSequenceSum(steps: Int): Int {
        return (steps * (1 + steps)) / 2
    }
    fun part2(inputList: List<String>): Int {
        val (crubs: List<Int>, max, min) = readCrubsAndFindMinMax(inputList)

        val countList = mutableListOf<Int>()

        for (i in min until max + 1) {
            var count = 0
            for (crub in crubs) {
                count += arithmeticSequenceSum(kotlin.math.abs(crub - i))
            }
            countList.add(count)
        }
        return countList.minByOrNull { it }!!
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    println(part2(testInput))

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

