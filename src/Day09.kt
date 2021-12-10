fun main() {

    fun checkAroundLocations(position: Pair<Int, Int>, matrix: MutableList<MutableList<Int>>): Boolean {
        val (y, x) = position
        val curNumber = matrix[y][x]
        val bottomNumber = if (y < matrix.size - 1) matrix[y + 1][x] else 999999999
        val topNumber = if (y > 0) matrix[y - 1][x] else 999999999
        val leftNumber = if (x > 0) matrix[y][x - 1] else 999999999
        val rightNumber = if (x < matrix.first().size - 1) matrix[y][x + 1] else 999999999
        return curNumber < topNumber && curNumber < bottomNumber && curNumber < leftNumber && curNumber < rightNumber
    }

    fun findBasin(position: Pair<Int, Int>, matrix: MutableList<MutableList<Int>>, visited: HashSet<Pair<Int, Int>>): Int {
        val (y, x) = position
        val curNumber = matrix[y][x]
        val bottomNumber = if (y < matrix.size - 1) matrix[y + 1][x] else 0
        val topNumber = if (y > 0) matrix[y - 1][x] else 0
        val leftNumber = if (x > 0) matrix[y][x - 1] else 0
        val rightNumber = if (x < matrix.first().size - 1) matrix[y][x + 1] else 0

        visited.add(Pair(y, x))
        if (curNumber < bottomNumber && bottomNumber != 9 && !visited.contains(Pair(y + 1, x))) {
            findBasin(Pair(y + 1, x), matrix, visited)
        }
        if (curNumber < topNumber && topNumber != 9  && !visited.contains(Pair(y - 1, x))) {
            findBasin(Pair(y - 1, x), matrix, visited)
        }
        if (curNumber < leftNumber && leftNumber != 9 && !visited.contains(Pair(y, x - 1))) {
            findBasin(Pair(y, x - 1), matrix, visited)
        }
        if (curNumber < rightNumber && rightNumber != 9 && !visited.contains(Pair(y, x + 1))) {
            findBasin(Pair(y, x + 1), matrix, visited)
        }

        return visited.size
    }

    fun part1(inputList: List<String>): Int {
        var matrix = mutableListOf<MutableList<Int>>()
        for (input in inputList) {
            // transform input string to mjutable list of ints
            matrix.add(input.chunked(1).map { it.toInt() }.toMutableList())
        }

        var count = 0
        for (y in 0 until matrix.size) {
            for (x in 0 until matrix[y].size) {
                if (checkAroundLocations(Pair(y, x), matrix)) {
                    count += matrix[y][x] + 1
                }
            }
        }
        return count
    }

    fun part2(inputList: List<String>): Int {
        var matrix = mutableListOf<MutableList<Int>>()
        for (input in inputList) {
            // transform input string to mjutable list of ints
            matrix.add(input.chunked(1).map { it.toInt() }.toMutableList())
        }
        // iterate over matrix
        var lowestPoints = mutableSetOf<Pair<Int, Int>>()
        for (y in 0 until matrix.size) {
            for (x in 0 until matrix[y].size) {
                if (checkAroundLocations(Pair(y, x), matrix)) {
//                    println(matrix[y][x])
                    lowestPoints.add(Pair(y, x))
                }
            }
        }

        var allBasins = mutableListOf<Int>()
        for (value in lowestPoints) {
            allBasins.add(findBasin(value, matrix, HashSet()))
        }
        allBasins.sort()
        return allBasins[allBasins.size - 1] * allBasins[allBasins.size - 2] * allBasins[allBasins.size - 3]
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    println(part2(testInput))

    val input = readInput("Day09")
//    println(part1(input))
    println(part2(input))
    //1134
    //1059300
}

