import DESFunctions.Mode
import DESFunctions.desPrep
import TextConversions.convertBinToHex
import TextConversions.convertHexToBin
import TextConversions.convertHexToString
import TextConversions.convertStringToBin

fun main() {
    encryptDecryptMenu()
}

fun encryptDecryptMenu() {
    var mode: Mode? = null
    var inputTextAndKeyPair: Pair<String, String>? = null
    var swapLastRound: Boolean? = null

    do {
        try {
            if (mode == null) {
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

                mode = when (readLine()!!.toInt()) {
                    1 -> Mode.ENCRYPT
                    2 -> Mode.DECRYPT
                    3 -> {
                        println("Goodbye!")
                        return
                    }
                    else -> throw WrongMenuChoiceException()
                }
            }

            if (inputTextAndKeyPair == null) {
                print(System.lineSeparator())

                println("Please choose an option: ")
                println("\t1. $mode String ${if (mode == Mode.ENCRYPT) "PlainText" else if (mode == Mode.DECRYPT) "CipherText" else ""}")
                println("\t2. $mode Hexadecimal ${if (mode == Mode.ENCRYPT) "PlainText" else if (mode == Mode.DECRYPT) "CipherText" else ""}")
                println("\t3. Quit")
                print("Choice: ")
                val menuOption = readLine()!!.toInt()

                inputTextAndKeyPair = when(menuOption) {
                    1 -> encryptDecryptStringInputTextPrep(mode)
                    2 -> encryptDecryptInputPlainTextPrep(mode)
                    3 -> {
                        println("Goodbye!")
                        return
                    }
                    else -> null
                }

                if (inputTextAndKeyPair == null)
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

            when (mode) {
                Mode.ENCRYPT -> encrypt(inputTextAndKeyPair.first, inputTextAndKeyPair.second, swapLastRound)
                Mode.DECRYPT -> decrypt(inputTextAndKeyPair.first, inputTextAndKeyPair.second, swapLastRound)
            }

            println()

            mode = null
            inputTextAndKeyPair = null
            swapLastRound = null
        }
        catch (e: WrongMenuChoiceException) {
            println(e.toString())
        }
        catch (e: NumberFormatException) {
            println("Please enter a number!")
        }
        catch (e: Exception) {
            println(e.toString())
            mode = null
            inputTextAndKeyPair = null
            swapLastRound = null
        }
    } while(true)
}

fun encryptDecryptStringInputTextPrep(mode: Mode) : Pair<String, String> {
    print(System.lineSeparator())

    when(mode) {
        Mode.ENCRYPT -> print("Please enter your plaintext as a String: ")
        Mode.DECRYPT -> print("Please enter your ciphertext as a String: ")
    }

    val inputText: String = readLine()!!

    println()

    print("Please enter your key as a String: ")
    val initialKey: String = readLine()!!

    print(System.lineSeparator())

    when(mode) {
        Mode.ENCRYPT -> {
            println("PlainText: $inputText")
            println("PlainText Hex and Binary Representations:")
        }
        Mode.DECRYPT -> {
            println("CipherText: $inputText")
            println("CipherText Hex and Binary Representations:")
        }
    }

    val inputTextBin = convertStringToBin(inputText)

    print(System.lineSeparator())

    println("Initial Key: $initialKey")
    println("Initial Key Hex and Binary Representations:")
    val initialKeyBin = convertStringToBin(initialKey)

    print(System.lineSeparator())

    return Pair(inputTextBin, initialKeyBin)
}

fun encryptDecryptInputPlainTextPrep(mode: Mode) : Pair<String, String> {
    print(System.lineSeparator())

    when(mode) {
        Mode.ENCRYPT -> print("Please enter your plaintext as a Hex Value: ")
        Mode.DECRYPT -> print("Please enter your ciphertext as a Hex Value: ")
    }

    val inputText: String = readLine()!!.replace("\\s".toRegex(), "")

    println()

    print("Please enter your key as a Hex Value: ")
    val initialKey: String = readLine()!!.replace("\\s".toRegex(), "")

    print(System.lineSeparator())

    when(mode) {
        Mode.ENCRYPT -> {
            println("PlainText: $inputText")
            println("PlainText Hex and Binary Representations:")
        }
        Mode.DECRYPT -> {
            println("CipherText: $inputText")
            println("CipherText Hex and Binary Representations:")
        }
    }

    val InputTextBin = convertHexToBin(inputText)
    println("Hex: ${inputText.chunked(2).joinToString(" ")}")
    println("Binary: ${InputTextBin.chunked(8).joinToString(" ")}")

    print(System.lineSeparator())

    println("Initial Key: $initialKey")
    println("Initial Key Hex and Binary Representations:")
    val initialKeyBin = convertHexToBin(initialKey)
    println("Hex: ${initialKey.chunked(2).joinToString(" ")}")
    println("Binary: ${initialKeyBin.chunked(8).joinToString(" ")}")

    print(System.lineSeparator())

    return Pair(InputTextBin, initialKeyBin)
}

fun encrypt(plainTextBin: String, initialKeyBin: String, swapLastRound: Boolean) {
    val cipherTextHex = convertBinToHex(desPrep(Mode.ENCRYPT, plainTextBin, initialKeyBin, swapLastRound))
    val cipherTextStr = convertHexToString(cipherTextHex)

    println("Final CipherText (Hex): ${cipherTextHex.chunked(2).joinToString(" ")}")
    println("Final CipherText (String): $cipherTextStr")
}

fun decrypt(cipherTextBin: String, initialKeyBin: String, swapLastRound: Boolean) {
    val plainTextHex = convertBinToHex(desPrep(Mode.DECRYPT, cipherTextBin, initialKeyBin, swapLastRound))
    val plainTextStr = convertHexToString(plainTextHex)

    println("Final PlainText (Hex): ${plainTextHex.chunked(2).joinToString(" ")}")
    println("Final PlainText (String): $plainTextStr")
}

private class WrongMenuChoiceException : Exception() {
    override fun toString(): String {
        return this.javaClass.canonicalName + ": Invalid Choice!"
    }
}