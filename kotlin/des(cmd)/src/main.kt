import DESFunctions.CipherMode
import DESFunctions.TextMode
import DESFunctions.desPrep
import TextConversions.convertBinToHex
import TextConversions.convertHexToString
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun main() {
    encryptDecryptMenu()
}

fun encryptDecryptMenu() {
    var cipherMode: CipherMode? = null
    var inputText: String? = null
    var inputKey: String? = null
    var swapLastRound: Boolean? = null

    do {
        try {
            if (cipherMode == null) {
                print(System.lineSeparator())
                println("DES Encryptor/Decryptor")
                println("Using Kotlin/JVM")
                println("Created by: Jose A. Alvarado")
                println("Copyright J.A.A. Productions 2019")

                println()

                println("Please choose an option: ")
                println("\t1. Encrypt")
                println("\t2. Decrypt")
                println("\t3. Quit")
                print("Choice: ")

                cipherMode = when (readLine()!!.toInt()) {
                    1 -> CipherMode.ENCRYPT
                    2 -> CipherMode.DECRYPT
                    3 -> {
                        println("Goodbye!")
                        return
                    }
                    else -> throw WrongMenuChoiceException()
                }
            }

            if (inputText == null) {
                print(System.lineSeparator())

                println("Please choose an option: ")
                println("\t1. $cipherMode String ${if (cipherMode == CipherMode.ENCRYPT) "PlainText" else if (cipherMode == CipherMode.DECRYPT) "CipherText" else ""}")
                println("\t2. $cipherMode Hexadecimal ${if (cipherMode == CipherMode.ENCRYPT) "PlainText" else if (cipherMode == CipherMode.DECRYPT) "CipherText" else ""}")
                println("\t3. Quit")
                print("Choice: ")
                var menuOption = readLine()!!.toInt()

                inputText = when(menuOption) {
                    1 -> encryptDecryptInputTextPrep(cipherMode, TextMode.STRING)
                    2 -> encryptDecryptInputTextPrep(cipherMode, TextMode.HEX)
                    3 -> {
                        println("Goodbye!")
                        return
                    }
                    else -> null
                }

                if (inputText == null)
                    throw WrongMenuChoiceException()

                print(System.lineSeparator())

                println("Please choose an option: ")
                println("\t1. Use String Key")
                println("\t2. Use Hexadecimal Key")
                println("\t3. Quit")
                print("Choice: ")
                menuOption = readLine()!!.toInt()

                when (menuOption) {
                    1 -> inputKey = keyTextPrep(TextMode.STRING)
                    2 -> inputKey = keyTextPrep(TextMode.HEX)
                    3 -> {
                        println("Goodbye!")
                        return
                    }
                    else -> {
                        inputText = null
                        inputKey = null
                    }
                }

                if (inputKey == null)
                    throw WrongMenuChoiceException()
            }


            print(System.lineSeparator())

            if (swapLastRound == null) {
                print("Swap Last Round? (y/n) ")

                swapLastRound = when(readLine()!!.toCharArray()[0]) {
                    'y', 'Y' -> true
                    'n', 'N' -> false
                    else -> null
                }


                if (swapLastRound == null)
                    throw WrongMenuChoiceException()
            }

            when (cipherMode) {
                CipherMode.ENCRYPT -> encrypt(inputText!!, inputKey!!, swapLastRound)
                CipherMode.DECRYPT -> decrypt(inputText!!, inputKey!!, swapLastRound)
            }

            println()

            cipherMode = null
            inputText = null
            inputKey = null
            swapLastRound = null
        }
        catch (e: WrongMenuChoiceException) {
            println(e.toString())
        }
        catch (e: NumberFormatException) {
            println("$e - Please enter a number!")
        }
        catch (e: Exception) {
            println(e.toString())
            cipherMode = null
            inputText = null
            inputKey = null
            swapLastRound = null
        }
    } while(true)
}

@Throws(IOException::class)
fun encryptDecryptInputTextPrep(cipherMode: CipherMode, textMode: TextMode): String {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val inputTextTypeStr = if (cipherMode == CipherMode.ENCRYPT) "PlainText" else "CipherText"

    print(System.lineSeparator())
    print("Please enter your $inputTextTypeStr as a $textMode Value: ")
    var inputText = bufferedReader.readLine()

    when (cipherMode) {
        CipherMode.ENCRYPT -> {
            println("PlainText: $inputText")
            println("PlainText Hex and Binary Representations:")
        }
        CipherMode.DECRYPT -> {
            println("CipherText: $inputText")
            println("CipherText Hex and Binary Representations:")
        }
    }

    var inputTextBin = ""

    when (textMode) {
        TextMode.STRING -> inputTextBin = TextConversions.convertStringToBin(inputText)
        TextMode.HEX -> {
            inputText = inputText.replace("\\s".toRegex(), "")

            inputTextBin = TextConversions.convertHexToBin(inputText)
            println("Hex: ${inputText.chunked(2).joinToString(" ")}")
            println("Binary: ${inputTextBin.chunked(8).joinToString(" ")}")
        }
    }

    print(System.lineSeparator())

    return inputTextBin
}

@Throws(IOException::class)
fun keyTextPrep(textMode: TextMode): String {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

    print("Please enter your key as a $textMode Value: ")
    var initialKey = bufferedReader.readLine()

    print(System.lineSeparator())

    println("Initial Key: $initialKey")
    println("Initial Key Hex and Binary Representations:")

    var initialKeyBin = ""

    when (textMode) {
        TextMode.STRING -> initialKeyBin = TextConversions.convertStringToBin(initialKey)
        TextMode.HEX -> {
            initialKey = initialKey.replace("\\s".toRegex(), "")

            initialKeyBin = TextConversions.convertHexToBin(initialKey)
            println("Hex: ${initialKey.chunked(2).joinToString(" ")}")
            println("Binary: ${initialKeyBin.chunked(8).joinToString(" ")}")
        }
    }

    print(System.lineSeparator())

    return initialKeyBin
}

fun encrypt(plainTextBin: String, initialKeyBin: String, swapLastRound: Boolean) {
    val cipherTextHex = convertBinToHex(desPrep(CipherMode.ENCRYPT, plainTextBin, initialKeyBin, swapLastRound))
    val cipherTextStr = convertHexToString(cipherTextHex)

    println("Final CipherText (Hex): ${cipherTextHex.chunked(2).joinToString(" ")}")
    println("Final CipherText (String): $cipherTextStr")
}

fun decrypt(cipherTextBin: String, initialKeyBin: String, swapLastRound: Boolean) {
    val plainTextHex = convertBinToHex(desPrep(CipherMode.DECRYPT, cipherTextBin, initialKeyBin, swapLastRound))
    val plainTextStr = convertHexToString(plainTextHex)

    println("Final PlainText (Hex): ${plainTextHex.chunked(2).joinToString(" ")}")
    println("Final PlainText (String): $plainTextStr")
}

private class WrongMenuChoiceException : Exception() {
    override fun toString(): String {
        return this.javaClass.canonicalName + ": Invalid Choice!"
    }
}