package DESFunctions

import TextConversions.convertBinToHex
import java.lang.StringBuilder

/*--------------------------------P-Boxes-------------------------------------*/

val INITIAL_P_BOX = arrayOf(58, 50, 42, 34, 26, 18, 10, 2,
                            60, 52, 44, 36, 28, 20, 12, 4,
                            62, 54, 46, 38, 30, 22, 14, 6,
                            64, 56, 48, 40, 32, 24, 16, 8,
                            57, 49, 41, 33, 25, 17, 9, 1,
                            59, 51, 43, 35, 27, 19, 11, 3,
                            61, 53, 45, 37, 29, 21, 13, 5,
                            63, 55, 47, 39, 31, 23, 15, 7)

val FINAL_P_BOX = arrayOf(40, 8, 48, 16, 56, 24, 64, 32,
                            39, 7, 47, 15, 55, 23, 63, 31,
                            38, 6, 46, 14, 54, 22, 62, 30,
                            37, 5, 45, 13, 53, 21, 61, 29,
                            36, 4, 44, 12, 52, 20, 60, 28,
                            35, 3, 43, 11, 51, 19, 59, 27,
                            34, 2, 42, 10, 50, 18, 58, 26,
                            33, 1, 41, 9, 49, 17, 57, 25)

val EXPANSION_P_BOX = arrayOf(32, 1, 2, 3, 4, 5,
                                4, 5, 6, 7, 8, 9,
                                8, 9, 10, 11, 12, 13,
                                12, 13, 14, 15, 16, 17,
                                16, 17, 18, 19, 20, 21,
                                20, 21, 22, 23, 24, 25,
                                24, 25, 26, 27, 28, 29,
                                28, 29, 30, 31, 32, 1)

val STRAIGHT_P_BOX = arrayOf(16, 7, 20, 21, 29, 12, 28, 17,
                                1, 15, 23, 26, 5, 18, 31, 10,
                                2, 8, 24, 14, 32, 27, 3, 9,
                                19, 13, 30, 6, 22, 11, 4, 25)

val PARITY_BIT_DROP_P_BOX = arrayOf(57, 49, 41, 33, 25, 17, 9, 1,
                                    58, 50, 42, 34, 26, 18, 10, 2,
                                    59, 51, 43, 35, 27, 19, 11, 3,
                                    60, 52, 44, 36, 63, 55, 47, 39,
                                    31, 23, 15, 7, 62, 54, 46, 38,
                                    30, 22, 14, 6, 61, 53, 45, 37,
                                    29, 21, 13, 5, 28, 20, 12, 4)

val KEY_COMPRESSION_P_BOX = arrayOf(14, 17, 11, 24, 1, 5, 3, 28,
                                    15, 6, 21, 10, 23, 19, 12, 4,
                                    26, 8, 16, 7, 27, 20, 13, 2,
                                    41, 52, 31, 37, 47, 55, 30, 40,
                                    51, 45, 33, 48, 44, 49, 39, 56,
                                    34, 53, 46, 42, 50, 36, 29, 32)

/*----------------------------------------------------------------------------*/

/*--------------------------------S-Boxes-------------------------------------*/

val S_BOX_1 = arrayOf(intArrayOf(14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7),
                        intArrayOf(0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8),
                        intArrayOf(4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0),
                        intArrayOf(15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13))

val S_BOX_2 = arrayOf(intArrayOf(15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10),
                        intArrayOf(3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5),
                        intArrayOf(0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15),
                        intArrayOf(13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9))

val S_BOX_3 = arrayOf(intArrayOf(10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8),
                        intArrayOf(13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1),
                        intArrayOf(13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7),
                        intArrayOf(1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12))

val S_BOX_4 = arrayOf(intArrayOf(7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15),
                        intArrayOf(13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9),
                        intArrayOf(10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4),
                        intArrayOf(3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14))

val S_BOX_5 = arrayOf(intArrayOf(2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9),
                        intArrayOf(14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6),
                        intArrayOf(4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14),
                        intArrayOf(11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3))

val S_BOX_6 = arrayOf(intArrayOf(12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11),
                        intArrayOf(10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8),
                        intArrayOf(9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6),
                        intArrayOf(4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13))

val S_BOX_7 = arrayOf(intArrayOf(4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1),
                        intArrayOf(13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6),
                        intArrayOf(1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2),
                        intArrayOf(6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12))

val S_BOX_8 = arrayOf(intArrayOf(13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7),
                        intArrayOf(1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2),
                        intArrayOf(7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8),
                        intArrayOf(2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11))

/*----------------------------------------------------------------------------*/

private val S_BOX_GROUP = arrayOf(S_BOX_1, S_BOX_2, S_BOX_3, S_BOX_4, S_BOX_5, S_BOX_6, S_BOX_7, S_BOX_8)
private val SHIFT_ONCE_ROUNDS = arrayOf(1, 2, 9, 16)

enum class Mode (value: Int) {
    ENCRYPT(0), DECRYPT(1);

    private val mode = value

    override fun toString() : String {
        return when(mode) {
            0 -> "Encrypt"
            1 -> "Decrypt"
            else -> ""
        }
    }
}

fun main() {
    //println("CipherText: ${convertBinToHex(desPrep(convertHexToBin("0123456789ABCDEF"), convertHexToBin("133457799BBCDFF1"), false))}")

    /*S_BOX_1.forEachIndexed { index, row ->
        if (row.size != row.distinct().size) {
            println("Error: $index")
        }
    }*/

    println(applySBox("110111", S_BOX_3))
}

fun desPrep(mode: Mode, inputText: String, key: String, swapLastRound: Boolean) : String {
    val finalResult = StringBuilder("")
    val inputTextBin = inputText
    val inputTextBinChunked = inputTextBin.chunked(64) { it.padEnd(64, '0') }
    println("${if (mode == Mode.ENCRYPT) "PlainText" else if (mode == Mode.DECRYPT) "CipherText" else ""} Binary: ${inputTextBinChunked.joinToString(" | ") { it.chunked(4).joinToString(" ") }}")

    val keyBin = key.padEnd(64, '0')
    //println("Key Binary: ${keyBin.chunked(8).joinToString(" ")}")

    val roundKeys = generateRoundKeys(keyBin, mode)
    println(System.lineSeparator())

    inputTextBinChunked.forEachIndexed { index, block ->
        println("For block ${index + 1}...")

        finalResult.append(desBinEncryptDecrypt(mode, block.toString(), roundKeys, swapLastRound))
    }

    return finalResult.toString()
}

private fun desBinEncryptDecrypt(mode: Mode, inputTextBin: String, roundKeys: List<String>, swapLastRound: Boolean) : String {
    val inputTextPostInitPBox = applyPBox(inputTextBin, INITIAL_P_BOX)

    var tempRoundInputText = inputTextPostInitPBox
    for (round in 0 until 16) {
        println("Round ${round+1}...")

        tempRoundInputText = if ((mode == Mode.ENCRYPT && round + 1 == 16) || (mode == Mode.DECRYPT && round + 1 == 1))
            roundFunction(mode, tempRoundInputText, roundKeys[round], swapLastRound)
        else
            roundFunction(mode, tempRoundInputText, roundKeys[round])

        println("${if (mode == Mode.ENCRYPT) "CipherText" else if (mode == Mode.DECRYPT) "PlainText" else ""} after Round ${(round + 1).toString().padStart(2, '0')}: ${convertBinToHex(tempRoundInputText).chunked(8).joinToString(" ")}")
        println(System.lineSeparator())
    }

    print(System.lineSeparator())
    return applyPBox(tempRoundInputText, FINAL_P_BOX)
}

/*----------------------------------Key Functions-------------------------------------*/

private fun generateRoundKeys(key: String, mode: Mode) : List<String> {
    val roundKeys = arrayListOf<String>()

    val cipherKey = applyPBox(key, PARITY_BIT_DROP_P_BOX)

    var tempShiftedKey = cipherKey
    for (round in 1..16) {
        val leftBlock = tempShiftedKey.subSequence(0, (tempShiftedKey.length / 2)).toString()
        val rightBlock = tempShiftedKey.subSequence((tempShiftedKey.length / 2), tempShiftedKey.length).toString()

        val leftShiftedBlock: String
        val rightShiftedBlock: String

        if (round in SHIFT_ONCE_ROUNDS) {
            leftShiftedBlock = shiftBitsLeft(leftBlock)
            rightShiftedBlock = shiftBitsLeft(rightBlock)
        }
        else {
            leftShiftedBlock = shiftBitsLeft(leftBlock, 2)
            rightShiftedBlock = shiftBitsLeft(rightBlock, 2)
        }


        tempShiftedKey = leftShiftedBlock + rightShiftedBlock

        val compressedKey = applyPBox(tempShiftedKey, KEY_COMPRESSION_P_BOX)
        roundKeys.add(compressedKey)
    }

    if (mode == Mode.DECRYPT)
        roundKeys.reverse()

    for(round in 0..15) {
        println("Round ${(round+1).toString().padStart(2,'0')} Key: ${roundKeys[round].chunked(6).joinToString(" ")}")
    }

    return roundKeys
}

/*------------------------------------------------------------------------------------*/

/*--------------------------------Round Functions-------------------------------------*/

private fun roundFunction(mode: Mode,input: String, key: String, swap: Boolean = true) : String {
    val leftInput = input.subSequence(0, (input.length / 2)).toString()
    val rightInput = input.subSequence((input.length / 2), input.length).toString()

    println("Pre-Round Input: ${convertBinToHex(leftInput)} ${convertBinToHex(rightInput)}")

    val leftOutput: String
    val rightOutput: String

    if (swap && mode == Mode.ENCRYPT) {
        leftOutput = rightInput
        rightOutput = xorBinaryBlocks(leftInput, innerRoundFunction(rightInput, key))
    }
    else if (swap && mode == Mode.DECRYPT) {
        leftOutput = xorBinaryBlocks(rightInput, innerRoundFunction(leftInput, key))
        rightOutput = leftInput
    }
    else {
        leftOutput = xorBinaryBlocks(leftInput, innerRoundFunction(rightInput, key))
        rightOutput = rightInput
    }

    return leftOutput + rightOutput
}

private fun innerRoundFunction(input: String, key: String) : String {
    val expandedInput = applyPBox(input, EXPANSION_P_BOX)
    println("Expansion Result: ${convertBinToHex(expandedInput)}")
    val inputWithAppliedKey = xorBinaryBlocks(expandedInput, key)
    println("Applied Key Result: ${convertBinToHex(inputWithAppliedKey)}")
    val chunkedInputWithAppliedKey = inputWithAppliedKey.chunked(6)

    val postSBoxResults = arrayListOf<String>()

    for((index, chunk) in chunkedInputWithAppliedKey.withIndex()) {
        val sBoxResult = applySBox(chunk, S_BOX_GROUP[index])

        println("S-Box ${index+1} input: $chunk")
        println("S-Box ${index+1} output: $sBoxResult")

        postSBoxResults.add(sBoxResult)
    }

    val postSBoxResultStr = postSBoxResults.joinToString("")

    return applyPBox(postSBoxResultStr, STRAIGHT_P_BOX)
}

/*------------------------------------------------------------------------------------*/

/*--------------------------------AUX Functions-------------------------------------*/

fun xorBinaryBlocks(block1 : String, block2: String) : String {
    var block1In = block1
    var block2In = block2
    val result = StringBuilder("")

    if (block1In.length > block2In.length) {
        block2In = block2In.padStart(block1In.length, '0')
    }
    else if (block2In.length > block1In.length) {
        block1In = block1In.padStart(block2In.length, '0')
    }

    block1In.forEachIndexed { index, b1 ->
        result.append((b1.toInt() xor block2In[index].toInt()).toString())
    }

    return result.toString()
}

fun applyPBox(input: String, pBox: Array<Int>) : String {
    val output = StringBuilder("")
    val outputArrayList = arrayListOf<Char>()

    for(i in 1..pBox.size) {
        outputArrayList.add('0')
    }

    pBox.forEachIndexed { index, it ->
        outputArrayList[index] = input[(it-1)]
    }

    outputArrayList.forEach {
        output.append(it)
    }

    return output.toString()
}

fun applySBox(input: String, sBox: Array<IntArray>) : String {
    val boxRow = Integer.parseInt("${input[0]}${input[input.lastIndex]}", 2)
    val boxCol = Integer.parseInt(input.substring(1, input.lastIndex), 2)

    return Integer.toBinaryString(sBox[boxRow][boxCol]).padStart(4, '0')
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

/*----------------------------------------------------------------------------------*/