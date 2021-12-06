fun main() {

    fun part1(inputList: List<String>): Int {
        val fishes = inputList.first().split(",").map { it.toInt() }.toMutableList()
        for (i in 0 until 80) {
            for (j in fishes.indices) {
                var fish = fishes[j]
                if (fish == 0) {
                    fishes[j] = 6
                    fishes.add(8)
                } else {
                    fishes[j] = fish - 1
                }
            }
        }
        return fishes.size
    }

    fun part2(inputList: List<String>): Long {
        val fishes = inputList.first().split(",").map { it.toInt() }.toMutableList()
        val fishAtCurDay = mutableListOf<Long>()
        repeat(9) { fishAtCurDay.add(0L)}
        for (fish in fishes) {
            fishAtCurDay[fish]++
        }
        for (i in 0 until 256) {
            val dayOfCycle = i % 9
            val nextDayOfCycle = (dayOfCycle + 7) % 9
            fishAtCurDay[nextDayOfCycle] = fishAtCurDay[nextDayOfCycle] + fishAtCurDay[dayOfCycle]
        }

        return fishAtCurDay.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    println(part2(testInput))

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}

