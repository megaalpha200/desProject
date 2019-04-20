import DESFunctions.desEncrypt
import TextConversions.convertBinToHex
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

    println("Final CipherText: ${convertBinToHex(desEncrypt(plainTextBin, initialKeyBin)).chunked(2).joinToString(" ")}")

    print(System.lineSeparator())
    println("DES Encryptor")
    println("Using Kotlin/JVM")
    println("Created by: Jose A. Alvarado")
    println("Copyright J.A.A. Productions 2019")
}