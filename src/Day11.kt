fun main() {

    fun part1(inputList: List<String>): Int {
        val matrix: MutableList<MutableList<Int>> = inputList.map { it.chunked(1).map { it.toInt() }.toMutableList() }.toMutableList()
//        printMatrix(matrix)
        for (i in 0 until 100) {
            
        }
        return 0
    }

    fun part2(inputList: List<String>): Int {
        return 0
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    println(part1(testInput))

    val input = readInput("Day11")
//    println(part1(input))
//    println(part2(input))
}

