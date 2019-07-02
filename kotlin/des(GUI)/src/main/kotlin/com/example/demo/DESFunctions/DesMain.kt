package DESFunctions

import DESFunctions.desMainFunctions.CipherMode
import DESFunctions.desMainFunctions.TextMode
import DESFunctions.desMainFunctions.debugOutput

object DesMain {

    fun encryptDecryptInputTextPrep(inputText: String, cipherMode: CipherMode, textMode: TextMode): String {
        var inputText = inputText
        debugOutput.append(System.lineSeparator())

        when (cipherMode) {
            CipherMode.ENCRYPT -> {
                debugOutput.append("PlainText: $inputText${System.lineSeparator()}")
                debugOutput.append("PlainText Hex and Binary Representations:${System.lineSeparator()}")
            }
            CipherMode.DECRYPT -> {
                debugOutput.append("CipherText: $inputText${System.lineSeparator()}")
                debugOutput.append("CipherText Hex and Binary Representations:${System.lineSeparator()}")
            }
        }

        var inputTextBin = ""

        when (textMode) {
            TextMode.STRING -> {
                val inputTextHex = TextConversions.convertStringToHex(inputText)
                inputTextBin = TextConversions.convertHexToBin(inputTextHex)
                debugOutput.append("Hex: ${inputTextHex.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                debugOutput.append("Binary: ${inputTextBin.chunked(8).joinToString(" ")}${System.lineSeparator()}")
            }
            TextMode.HEX -> {
                inputText = inputText.replace("\\s".toRegex(), "")

                inputTextBin = TextConversions.convertHexToBin(inputText)
                debugOutput.append("Hex: ${inputText.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                debugOutput.append("Binary: ${inputTextBin.chunked(8).joinToString(" ")}${System.lineSeparator()}")
            }
        }

        debugOutput.append(System.lineSeparator())

        return inputTextBin
    }

    fun keyTextPrep(initialKey: String, textMode: TextMode): String {
        var initialKey = initialKey
        debugOutput.append(System.lineSeparator())

        debugOutput.append("Initial Key: $initialKey${System.lineSeparator()}")
        debugOutput.append("Initial Key Hex and Binary Representations:${System.lineSeparator()}")

        var initialKeyBin = ""

        when (textMode) {
            TextMode.STRING -> {
                val initialKeyHex = TextConversions.convertStringToHex(initialKey)
                initialKeyBin = TextConversions.convertHexToBin(initialKeyHex)

                debugOutput.append("Hex: ${initialKey.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                debugOutput.append("Binary: ${initialKeyBin.chunked(8).joinToString(" ")}${System.lineSeparator()}")
            }
            TextMode.HEX -> {
                initialKey = initialKey.replace("\\s".toRegex(), "")

                initialKeyBin = TextConversions.convertHexToBin(initialKey)
                debugOutput.append("Hex: ${initialKey.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                debugOutput.append("Binary: ${initialKeyBin.chunked(8).joinToString(" ")}${System.lineSeparator()}")
            }
        }

        debugOutput.append(System.lineSeparator())

        return initialKeyBin
    }

    fun encrypt(plainTextBin: String, initialKeyBin: String, swapLastRound: Boolean): Pair<String, String> {
        val cipherTextHex = TextConversions.convertBinToHex(desMainFunctions.desPrep(CipherMode.ENCRYPT, plainTextBin, initialKeyBin, swapLastRound))
        val cipherTextStr = TextConversions.convertHexToString(cipherTextHex)

        val chunkedCipherTextHexStr = cipherTextHex.chunked(2).joinToString(" ")

        debugOutput.append("Final CipherText (Hex): $chunkedCipherTextHexStr${System.lineSeparator()}")
        debugOutput.append("Final CipherText (String): $cipherTextStr${System.lineSeparator()}")

        return Pair(chunkedCipherTextHexStr, cipherTextStr)
    }

    fun decrypt(cipherTextBin: String, initialKeyBin: String, swapLastRound: Boolean): Pair<String, String> {
        val plainTextHex = TextConversions.convertBinToHex(desMainFunctions.desPrep(CipherMode.DECRYPT, cipherTextBin, initialKeyBin, swapLastRound))
        val plainTextStr = TextConversions.convertHexToString(plainTextHex)

        val chunkedPlainTextHexStr = plainTextHex.chunked(2).joinToString(" ")

        debugOutput.append("Final PlainText (Hex): $chunkedPlainTextHexStr${System.lineSeparator()}")
        debugOutput.append("Final PlainText (String): $plainTextStr${System.lineSeparator()}")

        return Pair(chunkedPlainTextHexStr, plainTextStr)
    }

    class WrongMenuChoiceException : Exception() {
        override fun toString(): String {
            return this.javaClass.canonicalName + ": Invalid Choice!"
        }
    }

}
