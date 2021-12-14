fun main() {
    fun parseLine(line: String): List<Int> {
        return line.split("[^0-9]+".toRegex()).map { it.toInt() }
    }

    fun printMatrix(board: MutableList<MutableList<Int>>) {
        for (i in board.indices) {
            println(board[i])
        }
    }

    fun countMatrix(board: MutableList<MutableList<Int>>): Int {
        var count = 0
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] > 1) {
                    count++
                }
            }
        }
        return count
    }

    fun part1(inputList: List<String>): Int {
        val board = MutableList(1000) { MutableList(1000) { 0 } }

        for (input in inputList) {
            val (x1,x2,y1,y2) = parseLine(input)

            if (x1 != y1 && x2 != y2) continue

            if (x1 == y1) {
                if (x2 > y2) {
                    for (i in y2 until x2 + 1) {
                        board[i][x1] = board[i][x1] + 1
                    }
                } else {
                    for (i in x2 until y2 + 1) {
                        board[i][x1] = board[i][x1] + 1
                    }
                }
            } else {
                if (x1 > y1) {
                    for (i in y1 until x1 + 1) {
                        board[x2][i] = board[x2][i] + 1
                    }
                } else {
                    for (i in x1 until y1 + 1) {
                        board[x2][i] = board[x2][i] + 1
                    }
                }
            }
        }

        return countMatrix(board)
    }

    fun part2(inputList: List<String>): Int {
        val board = MutableList(1000) { MutableList(1000) { 0 } }
        printMatrix(board)

        for (input in inputList) {
            val (x1,y1,x2,y2) = parseLine(input)

            if (x1 != x2 && y1 != y2) {
                if (x2 > x1) {
                    if (y2 > y1) {
                        var i = x1
                        var j = y1
                        while (j <= y2) {
                            board[j][i] = board[j][i] + 1
                            j++
                            i++
                        }
                    } else {
                        println("$x1 $y1 $x2 $y2")
                        var i = x1
                        var j = y1
                        while (j >= y2) {
                            println("$i $j")
                            board[j][i] = board[j][i] + 1
                            i++
                            j--
                        }
                    }
                } else {
                    if (y2 > y1) {
                        var i = y1
                        var j = x1
                        while (j >= x2) {
                            board[i][j] = board[i][j] + 1
                            j--
                            i++
                        }
                    } else {
                        var i = x1
                        var j = y1
                        while (j >= y2) {
                            board[j][i] = board[j][i] + 1
                            j--
                            i--
                        }
                    }
                }
                continue
            }

            if (x1 == x2) {
                if (y1 > y2) {
                    for (i in y2 until y1 + 1) {
                        board[i][x1] = board[i][x1] + 1
                    }
                } else {
                    for (i in y1 until y2 + 1) {
                        board[i][x1] = board[i][x1] + 1
                    }
                }
            } else {
                if (x1 > x2) {
                    for (i in x2 until x1 + 1) {
                        board[y1][i] = board[y1][i] + 1
                    }
                } else {
                    for (i in x1 until x2 + 1) {
                        board[y1][i] = board[y1][i] + 1
                    }
                }
            }
        }
        return countMatrix(board)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    println(part2(testInput))

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

