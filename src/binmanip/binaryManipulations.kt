package binmanip

import java.lang.StringBuilder

fun main() {

}

fun shiftBitsLeft(input: String, shiftBy: Int = 1) : String {
    val modShiftVal = shiftBy % (input.length)
    val shiftedString = StringBuilder("")

    shiftedString.append(input.substring(modShiftVal))

    for(i in 0..(modShiftVal - 1)) {
        shiftedString.append(input[i])
    }

    return shiftedString.toString()
}

fun binSplit(input: String, blockSize: Int) : List<String> {
    var binBlocksList = input.chunked(blockSize)
    val lastBlock = binBlocksList[binBlocksList.lastIndex]

    if (lastBlock.length < blockSize) {
        val tempList = arrayListOf<String>()

        for (i in 0..(binBlocksList.lastIndex - 1)) {
            tempList.add(binBlocksList[i])
        }

        tempList.add(lastBlock.padEnd(blockSize, '0'))

        binBlocksList = tempList
    }

    return binBlocksList
}