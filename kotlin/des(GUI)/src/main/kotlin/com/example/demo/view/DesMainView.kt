package com.example.demo.view

import com.example.demo.app.Styles
import DESFunctions.desMainFunctions
import DESFunctions.desMainFunctions.CipherMode
import DESFunctions.desMainFunctions.TextMode
import DESFunctions.DesMain
import javafx.geometry.Orientation
import javafx.scene.control.*
import tornadofx.*
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.lang.StringBuilder
import javax.swing.JOptionPane

class DesMainView : View("DES Encryptor/Decryptor") {
    var inputTextTextField: TextArea? = null
    var keyTextField: TextField? = null
    var outputTextHexTextField: TextField? = null
    var outputTextStrTextField: TextArea? = null
    var outputTextHexCopyButton: Button? = null
    var outputTextStringCopyButton: Button? = null
    var inputTextStrRadioButton: RadioButton? = null
    var inputTextHexRadioButton: RadioButton? = null
    var keyStrRadioButton: RadioButton? = null
    var keyHexRadioButton: RadioButton? = null
    var encryptRadioButton: RadioButton? = null
    var decryptRadioButton: RadioButton? = null
    var swapYesRadioButton: RadioButton? = null
    var swapNoRadioButton: RadioButton? = null
    var debugTextArea: TextArea? = null

    val inputTextModeToggleGroup = ToggleGroup()
    val KeyTextModeToggleGroup = ToggleGroup()
    val swapToggleGroup = ToggleGroup()
    val encryptDecryptToggleGroup = ToggleGroup()


    override val root = vbox {
        hbox {
            label("$title using Kotlin") {
                addClass(Styles.heading)
            }
            addClass(Styles.headingBox)
        }
        form {
            fieldset("Inputs", labelPosition = Orientation.HORIZONTAL) {
                field("Input:") {
                    inputTextTextField = textarea {
                        prefHeight = 15.0
                    }
                    inputTextStrRadioButton = radiobutton("String", inputTextModeToggleGroup) { isSelected = true }
                    inputTextHexRadioButton = radiobutton("Hex", inputTextModeToggleGroup) {  }
                }
                field("Key:") {
                    keyTextField = textfield { }
                    keyStrRadioButton = radiobutton("String", KeyTextModeToggleGroup) { isSelected = true }
                    keyHexRadioButton = radiobutton("Hex", KeyTextModeToggleGroup) {  }
                }
                field("Swap?") {
                    swapYesRadioButton = radiobutton("Yes", swapToggleGroup) {  }
                    swapNoRadioButton = radiobutton("No", swapToggleGroup) { isSelected = true }
                }
                field("Encrypt or Decrypt?") {
                    encryptRadioButton = radiobutton("Encrypt", encryptDecryptToggleGroup) { isSelected = true }
                    decryptRadioButton = radiobutton("Decrypt", encryptDecryptToggleGroup) {  }
                }
            }
            hbox {
                button("Execute") {
                    action { executeBtnOnClick() }
                }
                addClass(Styles.executeBtnBox)
            }
            fieldset("Outputs", labelPosition = Orientation.HORIZONTAL) {
                field("Output Text (Hex):") {
                    outputTextHexTextField = textfield { }
                    outputTextHexTextField!!.isEditable = false
                    outputTextHexCopyButton = button("Copy") {
                        action { outputHexCopyBtnOnClick() }
                    }
                }
                field("Output Text (String):") {
                    outputTextStrTextField = textarea { prefHeight = 15.0 }
                    outputTextHexTextField!!.isEditable = false
                    outputTextStringCopyButton = button("Copy") {
                        action { outputStringCopyBtnOnClick() }
                    }
                }
            }
            fieldset(labelPosition = Orientation.VERTICAL) {
                field("Debug:") {
                   debugTextArea = textarea {  }
                    debugTextArea!!.isEditable = false
                }
            }
        }
    }

    private fun executeBtnOnClick() {
        try {
            val inputText = inputTextTextField!!.text
            val inputKey = keyTextField!!.text
            val textModeInput = if (inputTextStrRadioButton!!.isSelected) TextMode.STRING else TextMode.HEX
            val textModeKey = if (keyStrRadioButton!!.isSelected) TextMode.STRING else TextMode.HEX
            val cipherMode = if (encryptRadioButton!!.isSelected) CipherMode.ENCRYPT else CipherMode.DECRYPT
            val swapLastRound = swapYesRadioButton!!.isSelected

            val inputTextBin = DesMain.encryptDecryptInputTextPrep(inputText, cipherMode, textModeInput)
            val keyTextBin = DesMain.keyTextPrep(inputKey, textModeKey)

            var completionMessage = ""
            var outputTextHexStringPair: Pair<String, String>? = null
            when (cipherMode) {
                CipherMode.ENCRYPT -> {
                    outputTextHexStringPair = DesMain.encrypt(inputTextBin, keyTextBin, swapLastRound)
                    completionMessage = "The PlainText has been sucessfully Encrypted!"
                }
                CipherMode.DECRYPT -> {
                    outputTextHexStringPair = DesMain.decrypt(inputTextBin, keyTextBin, swapLastRound)
                    completionMessage = "The CipherText has been sucessfully Decrypted!"
                }
                else -> throw Exception("An unexpected error has occured!")
            }

            if (outputTextHexStringPair == null || outputTextHexStringPair.first == "")
                throw Exception("Please enter inputs!")

            val outputTextHex = outputTextHexStringPair.first
            val outputTextStr = outputTextHexStringPair.second
            outputTextHexTextField!!.text = outputTextHex
            outputTextStrTextField!!.text = outputTextStr

            var debugStr = desMainFunctions.debugOutput.toString()
            debugTextArea!!.text = debugStr
            desMainFunctions.debugOutput = StringBuilder("")

            JOptionPane.showMessageDialog(null, completionMessage, "Complete!", JOptionPane.INFORMATION_MESSAGE)
        } catch (ex: Exception) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE)
        }

    }

    private fun outputHexCopyBtnOnClick() {
        val outputHexStr = outputTextHexTextField!!.text
        val stringSelection = StringSelection(outputHexStr)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(stringSelection, null)

        JOptionPane.showMessageDialog(null, "Text copied!", "Copied!", JOptionPane.INFORMATION_MESSAGE)
    }

    private fun outputStringCopyBtnOnClick() {
        val outputStr = outputTextStrTextField!!.text
        val stringSelection = StringSelection(outputStr)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(stringSelection, null)

        JOptionPane.showMessageDialog(null, "Text copied!", "Copied!", JOptionPane.INFORMATION_MESSAGE)
    }
}