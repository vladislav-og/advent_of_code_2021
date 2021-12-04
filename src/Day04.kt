fun main() {

    fun printMatrix(board: MutableList<MutableList<Int>>) {
        for (i in 0 until 5) {
            println(board[i])
        }
    }

    fun validateBingoBoard(bingoboard: MutableList<MutableList<Int>>): Boolean {
        for (i in 0 until 5) {
            var count = 0
            var row = bingoboard[i]
            for (j in 0 until 5) {
                if (row[j] == -1) {
                    count++
                } else {
                    count = 0
                    break
                }
                if (count == 5) {
                    return true
                }
            }
            count = 0
            for (k in 0 until 5) {
                for (z in 0 until 5) {
                    if (bingoboard[z][k] == -1) {
                        count++
                    } else {
                        count = 0
                        break
                    }
                    if (count == 5) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun matchBingoNumberToBoard(number: Int, bingoboards: MutableList<MutableList<MutableList<Int>>>) {
        for (i in 0 until bingoboards.size) {
            for (j in 0 until 5) {
                for (k in 0 until 5) {
                    if (bingoboards[i][j][k] == number) {
                        bingoboards[i][j][k] = -1
                        break
                    }
                }
            }
        }
    }

    fun sumBoard(bingoboard: MutableList<MutableList<Int>>): Int {
        var sum = 0
        for (i in 0 until 5) {
            for (j in 0 until 5) {
                if (bingoboard[i][j] != -1) {
                    sum += bingoboard[i][j]
                }
            }
        }
        return sum
    }

    fun part1(inputList: List<String>): Int {
        var boards = mutableListOf<MutableList<MutableList<Int>>>()
        var scoreBoards = mutableListOf<MutableList<MutableList<Int>>>()

        var lineIndex = 0
        var boardCount = 0
        for (i in 2 until inputList.size) {
            if (inputList[i].trim() == "") {
                lineIndex = 0
                boardCount++
                continue
            } else {
                if (lineIndex == 0) {
                    boards.add(mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()))
                    scoreBoards.add(mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()))
                }
                inputList[i].split(" ")
                    .filter { it.isNotEmpty() }
                    .map { it.toInt() }
                    .forEach( {
                        boards[boardCount][lineIndex].add(it)
                        scoreBoards[boardCount][lineIndex].add(it)
                    })
                lineIndex++
            }
        }

        var bingoNumbers = inputList.first().split(",").map { it.toInt() }

        for (i in bingoNumbers.indices) {
            var number = bingoNumbers[i]
            matchBingoNumberToBoard(number, scoreBoards)
            if (i > 4) {
                for (board in scoreBoards) {
                    if (validateBingoBoard(board)) {
                        return sumBoard(board) * number
                    }
                }
            }
        }
        return 0
    }

    fun part2(inputList: List<String>): Int {
        var boards = mutableListOf<MutableList<MutableList<Int>>>()
        var scoreBoards = mutableListOf<MutableList<MutableList<Int>>>()

        var lineIndex = 0
        var boardCount = 0
        for (i in 2 until inputList.size) {
            if (inputList[i].trim() == "") {
                lineIndex = 0
                boardCount++
                continue
            } else {
                if (lineIndex == 0) {
                    boards.add(mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()))
                    scoreBoards.add(mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()))
                }
                inputList[i].split(" ")
                    .filter { it.isNotEmpty() }
                    .map { it.toInt() }
                    .forEach( {
                        boards[boardCount][lineIndex].add(it)
                        scoreBoards[boardCount][lineIndex].add(it)
                    })
                lineIndex++
            }
        }

        var bingoNumbers = inputList.first().split(",").map { it.toInt() }

        var boardComplited = mutableListOf<Boolean>()
        repeat(boards.size) { boardComplited.add(false) }
        var boardsFinished = 0

        for (i in bingoNumbers.indices) {
            var number = bingoNumbers[i]
            matchBingoNumberToBoard(number, scoreBoards)
            if (i > 4) {

                for (j in scoreBoards.indices) {
                    val board = scoreBoards[j]
                    if (!boardComplited[j]) {
                        if (validateBingoBoard(board)) {
                            boardComplited[j] = true
                            boardsFinished++
                            if (boardsFinished == boards.size) {
                                return sumBoard(board) * number
                            }
                        }
                    }
                }
            }
        }

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    println(part2(testInput))

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

