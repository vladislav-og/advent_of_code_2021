fun main() {

    fun parseLine(input: String): Pair<List<String>, List<String>> {
        val segmentsSides = input.split("|").map { it.trim() }
        var segmentsLeft = segmentsSides[0].split("[^a-g]+".toRegex())
        var segmentsRight = segmentsSides[1].split("[^a-g]+".toRegex())
        return Pair(segmentsLeft, segmentsRight)
    }

    fun part1(inputList: List<String>): Int {
        var count = 0
        for (input in inputList) {
            val (segmentsLeft, segmentsRight) = parseLine(input)
            for (segment in segmentsRight) {
                if (listOf(2, 3, 4, 7).contains(segment.length)) {
                    count++
                }
            }
        }
        return count
    }

    fun permutations(list: List<String>): List<List<String>> {
        val permutations = mutableListOf<List<String>>()
        if (list.isEmpty()) {
            permutations.add(listOf())
        } else {
            val firstElement = list[0]
            val rest = list.subList(1, list.size)
            for (permutation in permutations(rest)) {
                for (i in 0..permutation.size) {
                    val newPermutation = permutation.toMutableList()
                    newPermutation.add(i, firstElement)
                    permutations.add(newPermutation)
                }
            }
        }
        return permutations
    }

    fun mapCorrectCurrentMapping(
        baseMappingValues: List<String>,
        mappedValues: List<String>,
    ): Map<Int, List<String>> {
        val initialMappingMap = mapOf(
            0 to listOf("a", "b", "c", "e", "f", "g"),
            2 to listOf("a", "c", "d", "e", "g"),
            3 to listOf("a", "c", "d", "f", "g"),
            5 to listOf("a", "b", "d", "f", "g"),
            6 to listOf("a", "b", "d", "e", "f", "g"),
            9 to listOf("a", "b", "c", "d", "f", "g"),
        )
        var newMappingMap = mutableMapOf<Int, List<String>>()

        for (key in initialMappingMap.keys) {
            var mappedLetters = mutableListOf<String>()
            for (letter in initialMappingMap[key]!!) {
                mappedLetters.add(mappedValues[baseMappingValues.indexOf(letter)])
            }
            newMappingMap.put(key, mappedLetters)
        }
        return newMappingMap
    }

    fun isSignalCorrectNumber(signal: List<String>, mappings: Map<Int, List<String>>): Boolean {
        for (number in mappings.keys) {
            if (signal.size == mappings[number]!!.size && signal.containsAll(mappings[number]!!)) {
                return true
            }
        }
        return false
    }

    fun decode(input: List<String>): Map<Int, List<String>> {
        val initialMapping = mapOf(
            0 to listOf("a", "b", "c", "e", "f", "g"),
            2 to listOf("a", "c", "d", "e", "g"),
            3 to listOf("a", "c", "d", "f", "g"),
            5 to listOf("a", "b", "d", "f", "g"),
            6 to listOf("a", "b", "d", "e", "f", "g"),
            9 to listOf("a", "b", "c", "d", "f", "g"),
        )
        val segments = mutableListOf<List<String>>()
        for (segment in input) {
            if (segment.length == 5) {
                segments.add(segment.chunked(1))
            } else if (segment.length == 6) {
                segments.add(segment.chunked(1))
            }
        }

        val baseMappingValues = listOf("a", "b", "c", "d", "e", "f", "g")
        val allMappingCombinations = permutations(baseMappingValues)

        for (mapping in allMappingCombinations) {
            val segmentsCopy = segments.map { it.toMutableList() }
            for (segment in segmentsCopy) {
                for (i in segment.indices) {
                    segment[i] = baseMappingValues[mapping.indexOf(segment[i])]
                }
            }
            val currentMapping = mapCorrectCurrentMapping(baseMappingValues, mapping)
            var correctCount = 0
            for (segment in segments) {
                if (isSignalCorrectNumber(segment, currentMapping)) {
                    correctCount++
                }
                if (correctCount == segments.size) {
                    return currentMapping
                }
            }

        }

        return initialMapping
    }

    fun part2(inputList: List<String>): Int {

        var count = 0
        for (input in inputList) {
            val (segmentsLeft, segmentsRight) = parseLine(input)
            val segmentMap = decode(segmentsLeft)
            var number = ""
            for (segment in segmentsRight) {
                when (segment.length) {
                    2 -> {
                        number += "1"
                    }
                    3 -> {
                        number += "7"
                    }
                    4 -> {
                        number += "4"
                    }
                    7 -> {
                        number += "8"
                    }
                }
                for (key in segmentMap.keys) {
                    if (segmentMap[key]!!.size == segment.length && segmentMap[key]!!.containsAll(segment.chunked(1))) {
                        number += key.toString()
                    }
                }

            }
            count += number.toInt()
        }
        return count
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    println(part2(testInput))

    val input = readInput("Day08")
//    println(part2(input))
    println(part2(input))
}

