package desfunctions;

import javafx.util.Pair;
import stringmanip.StringManipulations;
import textconversions.TextConversions;

public class DesMain {

    public static String encryptDecryptInputTextPrep(String inputText, DesMainFunctions.CipherMode cipherMode, DesMainFunctions.TextMode textMode)  {
        DesMainFunctions.debugOutput.append(System.lineSeparator());

        switch (cipherMode)
        {
            case ENCRYPT:
                DesMainFunctions.debugOutput.append("PlainText: ").append(inputText).append(System.lineSeparator());
                DesMainFunctions.debugOutput.append("PlainText Hex and Binary Representations:").append(System.lineSeparator());
                break;
            case DECRYPT:
                DesMainFunctions.debugOutput.append("CipherText: ").append(inputText).append(System.lineSeparator());
                DesMainFunctions.debugOutput.append("CipherText Hex and Binary Representations:").append(System.lineSeparator());
                break;
        }

        String inputTextBin = "";

        if (cipherMode == DesMainFunctions.CipherMode.ENCRYPT && textMode == DesMainFunctions.TextMode.STRING) {
            String inputTextHex = TextConversions.convertStringToHex(inputText);
            inputTextBin = TextConversions.convertHexToBin(inputTextHex);
            DesMainFunctions.debugOutput.append("Hex: ").append(String.join(" ", StringManipulations.chunkString(inputTextHex, 2))).append(System.lineSeparator());
            DesMainFunctions.debugOutput.append("Binary: ").append(String.join(" ", StringManipulations.chunkString(inputTextBin, 8))).append(System.lineSeparator());
        }
        else if (cipherMode == DesMainFunctions.CipherMode.DECRYPT && textMode == DesMainFunctions.TextMode.STRING) {
            String inputTextHex = TextConversions.convertBase64StringToHex(inputText);
            inputTextBin = TextConversions.convertHexToBin(inputTextHex);
            DesMainFunctions.debugOutput.append("Hex: ").append(String.join(" ", StringManipulations.chunkString(inputTextHex, 2))).append(System.lineSeparator());
            DesMainFunctions.debugOutput.append("Binary: ").append(String.join(" ", StringManipulations.chunkString(inputTextBin, 8))).append(System.lineSeparator());
        }
        else if (textMode == DesMainFunctions.TextMode.HEX) {
            inputText = inputText.replaceAll("\\s", "");

            inputTextBin = TextConversions.convertHexToBin(inputText);
            DesMainFunctions.debugOutput.append("Hex: ").append(String.join(" ", StringManipulations.chunkString(inputText, 2))).append(System.lineSeparator());
            DesMainFunctions.debugOutput.append("Binary: ").append(String.join(" ", StringManipulations.chunkString(inputTextBin, 8))).append(System.lineSeparator());
        }

        DesMainFunctions.debugOutput.append(System.lineSeparator());

        return inputTextBin;
    }

    public static String keyTextPrep(String initialKey, DesMainFunctions.TextMode textMode) {
        DesMainFunctions.debugOutput.append(System.lineSeparator());

        DesMainFunctions.debugOutput.append("Initial Key: ").append(initialKey).append(System.lineSeparator());
        DesMainFunctions.debugOutput.append("Initial Key Hex and Binary Representations:").append(System.lineSeparator());

        String initialKeyBin = "";

        switch (textMode)
        {
            case STRING:
                String initialKeyHex = TextConversions.convertStringToHex(initialKey);
                initialKeyBin = TextConversions.convertHexToBin(initialKeyHex);

                DesMainFunctions.debugOutput.append("Hex: ").append(String.join(" ", StringManipulations.chunkString(initialKeyHex, 2))).append(System.lineSeparator());
                DesMainFunctions.debugOutput.append("Binary: ").append(String.join(" ", StringManipulations.chunkString(initialKeyBin, 8))).append(System.lineSeparator());
                break;
            case HEX:
                initialKey = initialKey.replaceAll("\\s", "");

                initialKeyBin = TextConversions.convertHexToBin(initialKey);
                DesMainFunctions.debugOutput.append("Hex: ").append(String.join(" ", StringManipulations.chunkString(initialKey, 2))).append(System.lineSeparator());
                DesMainFunctions.debugOutput.append("Binary: ").append(String.join(" ", StringManipulations.chunkString(initialKeyBin, 8))).append(System.lineSeparator());
                break;
        }

        DesMainFunctions.debugOutput.append(System.lineSeparator());

        return initialKeyBin;
    }

    public static Pair<String, String> encrypt(String plainTextBin, String initialKeyBin, boolean swapLastRound) {
        final String cipherTextHex = TextConversions.convertBinToHex(DesMainFunctions.desPrep(DesMainFunctions.CipherMode.ENCRYPT, plainTextBin, initialKeyBin, swapLastRound));
        final String cipherTextStr = TextConversions.convertHexToBase64String(cipherTextHex);

        final String chunkedCipherTextHexStr = String.join(" ", StringManipulations.chunkString(cipherTextHex, 2));

        DesMainFunctions.debugOutput.append("Final CipherText (Hex): ").append(chunkedCipherTextHexStr).append(System.lineSeparator());
        DesMainFunctions.debugOutput.append("Final CipherText (Base64 String): ").append(cipherTextStr).append(System.lineSeparator());

        return new Pair<>(chunkedCipherTextHexStr, cipherTextStr);
    }

    public static Pair<String, String> decrypt(String cipherTextBin, String initialKeyBin, boolean swapLastRound) {
        final String plainTextHex = TextConversions.convertBinToHex(DesMainFunctions.desPrep(DesMainFunctions.CipherMode.DECRYPT, cipherTextBin, initialKeyBin, swapLastRound));
        final String plainTextStr = TextConversions.convertHexToString(plainTextHex);

        final String chunkedPlainTextHexStr = String.join(" ", StringManipulations.chunkString(plainTextHex, 2));

        DesMainFunctions.debugOutput.append("Final PlainText (Hex): ").append(chunkedPlainTextHexStr).append(System.lineSeparator());
        DesMainFunctions.debugOutput.append("Final PlainText (String): ").append(plainTextStr).append(System.lineSeparator());

        return new Pair<>(chunkedPlainTextHexStr, plainTextStr);
    }

    public static class WrongMenuChoiceException extends Exception {
        @Override
        public String toString() {
            return this.getClass().getCanonicalName() + ": Invalid Choice!";
        }
    }

}
