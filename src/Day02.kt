fun main() {

    fun part1(input: List<String>): Int {
        var curX = 0
        var curY = 0

        for (line in input) {
            val lineList = line.split(" ")
            val move = lineList[0]
            val count = lineList[1].toInt()

            when (move) {
                "forward" -> curX += count
                "down" -> curY += count
                "up" -> curY -= count
            }
        }
        return curX * curY
    }

    fun part2(input: List<String>): Int {
        var curX = 0
        var curY = 0
        var aim = 0

        for (line in input) {
            val lineList = line.split(" ")
            val move = lineList[0]
            val count = lineList[1].toInt()

            when (move) {
                "forward" -> {
                    curX += count
                    curY += aim * count
                }
                "down" -> {
                    aim += count
                }
                "up" -> {
                    aim -= count
                }
            }
        }
        return curX * curY
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part2(testInput))

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
