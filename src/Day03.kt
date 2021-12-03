fun main() {

    fun binaryStr2Integer(str: String): Int {
        var result = 0
        for (i in str) {
            result = result * 2 + i.toString().toInt()
        }
        return result
    }

    fun part1(input: List<String>): Int {
        val iSize = input.first().length
        val countListG = ArrayList<Int>(iSize)
        repeat(iSize) { countListG.add(0) }
        val countListE = ArrayList<Int>(iSize)
        repeat(iSize) { countListE.add(0) }

        for (i in input) {
            for (j in i.indices) {
                if (i[j] == '1') {
                    countListG[j] = countListG[j] + 1
                } else {
                    countListE[j] = countListE[j] + 1
                }
            }
        }

        var gamma = ""
        var epsilon = ""

        for (i in 0 until iSize) {
            if (countListG[i] > countListE[i]) {
                gamma += '1'
                epsilon += '0'
            } else {
                gamma += '0'
                epsilon += '1'
            }
        }

        return binaryStr2Integer(gamma) * binaryStr2Integer(epsilon)
    }

    fun isBitAtPositionPositive(inputList: List<String>, pos: Int): Boolean {
        var positiveCount = 0

        for (input in inputList) {
            if (input[pos] == '1') {
                positiveCount++
            }
        }
        return positiveCount >= inputList.size - positiveCount
    }

    fun part2(inputList: List<String>): Int {

        val mutableInputList = inputList.toMutableList()
        val elementsToRemove = mutableListOf<String>()

        var pointer = 0
        while (pointer <= inputList.size && mutableInputList.size != 1) {
            val isPositive = isBitAtPositionPositive(mutableInputList, pointer)
            for (i in mutableInputList.indices) {
                val element = mutableInputList[i]
                if (isPositive) {
                    if (element[pointer] == '0') {
                        elementsToRemove.add(element)
                    }
                } else {
                    if (element[pointer] == '1') {
                        elementsToRemove.add(element)
                    }
                }
            }
            pointer++
            mutableInputList.removeAll(elementsToRemove)
        }

        val mutableInputListCo2 = inputList.toMutableList()
        val elementsToRemoveCo2 = mutableListOf<String>()

        var pointerCo2 = 0
        while (pointerCo2 <= inputList.size && mutableInputListCo2.size != 1) {
            val isPositive = isBitAtPositionPositive(mutableInputListCo2, pointerCo2)
            for (i in mutableInputListCo2.indices) {
                val element = mutableInputListCo2[i]
                if (isPositive) {
                    if (element[pointerCo2] == '1') {
                        elementsToRemoveCo2.add(element)
                    }
                } else {
                    if (element[pointerCo2] == '0') {
                        elementsToRemoveCo2.add(element)
                    }
                }
            }
            pointerCo2++
            mutableInputListCo2.removeAll(elementsToRemoveCo2)
        }
        return binaryStr2Integer(mutableInputList.first()) * binaryStr2Integer(mutableInputListCo2.first())
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println(part2(testInput))

        val input = readInput("Day03")
        println(part1(input))
    println(part2(input))
}

