import DESFunctions.desEncrypt
import TextConversions.convertBinToHex
import TextConversions.convertHexToString
import TextConversions.convertStringToBin

fun main() {
    print("Please enter your plaintext: ")
    val plainText: String = readLine()!!

    println()

    print("Please enter your key: ")
    val initialKey: String = readLine()!!

    print(System.lineSeparator())

    println("PlainText: $plainText")
    println("PlainText Hex and Binary Representations:")
    val plainTextBin = convertStringToBin(plainText)

    print(System.lineSeparator())

    println("Initial Key: $initialKey")
    println("Initial Key Hex and Binary Representations:")
    val initialKeyBin = convertStringToBin(initialKey)

    print(System.lineSeparator())

    val cipherTextHex = convertBinToHex(desEncrypt(plainTextBin, initialKeyBin))
    val cipherTextStr = convertHexToString(cipherTextHex)

    println("Final CipherText (Hex): ${cipherTextHex.chunked(2).joinToString(" ")}")
    println("Final CipherText (String): $cipherTextStr")

    print(System.lineSeparator())
    println("DES Encryptor")
    println("Using Kotlin/JVM")
    println("Created by: Jose A. Alvarado")
    println("Copyright J.A.A. Productions 2019")
}