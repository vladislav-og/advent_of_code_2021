fun main() {
    fun part1(input: List<String>): Int {
        var increasesCount = 0

        for (i in 1 until input.size) {
            val previous = input[i - 1].toInt()
            val current = input[i].toInt()
            if (current > previous) {
                increasesCount++
            }
        }
        return increasesCount
    }

    fun part2(input: List<String>): Int {
        var increasesCount = 0;
        // foor loop starting from 1 to input.size - 1
        for (i in 3 until input.size) {
            val b = input[i - 3].toInt()
            val c = input[i - 2].toInt()
            val a = input[i - 1].toInt()
            val sumOne = a + b + c

            val b2 = input[i - 2].toInt()
            val c2 = input[i - 1].toInt()
            val a2 = input[i].toInt()
            val sumTwo = a2 + b2 + c2
            if (sumTwo > sumOne) {
                increasesCount++
            }
        }
        return increasesCount
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    println(part2(testInput))

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
