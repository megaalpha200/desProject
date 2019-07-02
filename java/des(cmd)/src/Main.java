import javafx.util.Pair;
import stringmanip.StringManipulations;
import textconversions.TextConversions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        encryptDecryptMenu();
    }

    public static void encryptDecryptMenu() {
        DesMainFunctions.CipherMode cipherMode = null;
        String inputText = null;
        String inputKey = null;
        Boolean swapLastRound = null;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        do {
            try {
                if (cipherMode == null) {
                    System.out.print(System.lineSeparator());
                    System.out.println("DES Encryptor/Decryptor");
                    System.out.println("Using Java/JVM");
                    System.out.println("Created by: Jose A. Alvarado");
                    System.out.println("Copyright J.A.A. Productions 2019");

                    System.out.println();

                    System.out.println("Please choose an option: ");
                    System.out.println("\t1. Encrypt");
                    System.out.println("\t2. Decrypt");
                    System.out.println("\t3. Quit");
                    System.out.print("Choice: ");

                    switch (Integer.parseInt(bufferedReader.readLine())) {
                        case 1:
                            cipherMode = DesMainFunctions.CipherMode.ENCRYPT;
                            break;
                        case 2:
                            cipherMode = DesMainFunctions.CipherMode.DECRYPT;
                            break;
                        case 3:
                            System.out.println("Goodbye!");
                            return;
                        default:
                            throw new WrongMenuChoiceException();
                    }
                }

                if (inputText == null) {
                    System.out.print(System.lineSeparator());

                    System.out.println("Please choose an option: ");
                    System.out.println("\t1. " + cipherMode.toString() + " String " + ((cipherMode == DesMainFunctions.CipherMode.ENCRYPT) ? "PlainText" : "CipherText"));
                    System.out.println("\t2. " + cipherMode.toString() + " Hexadecimal " + ((cipherMode == DesMainFunctions.CipherMode.ENCRYPT) ? "PlainText" : "CipherText"));
                    System.out.println("\t3. Quit");
                    System.out.print("Choice: ");
                    int menuOption = Integer.parseInt(bufferedReader.readLine());

                    switch (menuOption) {
                        case 1:
                            inputText = encryptDecryptInputTextPrep(cipherMode, DesMainFunctions.TextMode.STRING);
                            break;
                        case 2:
                            inputText = encryptDecryptInputTextPrep(cipherMode, DesMainFunctions.TextMode.HEX);
                            break;
                        case 3:
                            System.out.println("Goodbye!");
                            return;
                        default:
                            inputText = null;
                    }

                    if (inputText == null)
                        throw new WrongMenuChoiceException();

                    System.out.print(System.lineSeparator());

                    System.out.println("Please choose an option: ");
                    System.out.println("\t1. Use String Key");
                    System.out.println("\t2. Use Hexadecimal Key");
                    System.out.println("\t3. Quit");
                    System.out.print("Choice: ");
                    menuOption = Integer.parseInt(bufferedReader.readLine());

                    switch (menuOption) {
                        case 1:
                            inputKey = keyTextPrep(DesMainFunctions.TextMode.STRING);
                            break;
                        case 2:
                            inputKey = keyTextPrep(DesMainFunctions.TextMode.HEX);
                            break;
                        case 3:
                            System.out.println("Goodbye!");
                            return;
                        default:
                            inputText = null;
                            inputKey = null;
                    }

                    if (inputKey == null)
                        throw new WrongMenuChoiceException();
                }

                System.out.print(System.lineSeparator());

                if (swapLastRound == null) {
                    System.out.print("Swap Last Round? (y/n) ");

                    switch (bufferedReader.readLine().toCharArray()[0]) {
                        case 'y':
                        case 'Y':
                            swapLastRound = true;
                            break;
                        case 'n':
                        case 'N':
                            swapLastRound = false;
                            break;
                        default:
                            swapLastRound = null;
                    }


                    if (swapLastRound == null)
                        throw new WrongMenuChoiceException();
                }

                if (cipherMode == DesMainFunctions.CipherMode.ENCRYPT)
                    encrypt(inputText, inputKey, swapLastRound);
                else if (cipherMode == DesMainFunctions.CipherMode.DECRYPT)
                    decrypt(inputText, inputKey, swapLastRound);

                System.out.println();

                cipherMode = null;
                inputText = null;
                inputKey = null;
                swapLastRound = null;
            }
            catch (WrongMenuChoiceException e) {
                System.out.println(e.toString());
            }
            catch (NumberFormatException e) {
                System.out.println(e.toString() + " - Please enter a number!");
            }
            catch (Exception e) {
                System.out.println(e.toString());
                cipherMode = null;
                inputText = null;
                inputKey = null;
                swapLastRound = null;
            }
        } while(true);
    }

    public static String encryptDecryptInputTextPrep(DesMainFunctions.CipherMode cipherMode, DesMainFunctions.TextMode textMode) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String inputTextTypeStr = (cipherMode == DesMainFunctions.CipherMode.ENCRYPT) ? "PlainText" : "CipherText";

        System.out.print(System.lineSeparator());
        System.out.print("Please enter your " + inputTextTypeStr + " as a " + textMode.toString() + " Value: ");
        String inputText = bufferedReader.readLine();

        switch (cipherMode)
        {
            case ENCRYPT:
                System.out.println("PlainText: " + inputText);
                System.out.println("PlainText Hex and Binary Representations:");
                break;
            case DECRYPT:
                System.out.println("CipherText: " + inputText);
                System.out.println("CipherText Hex and Binary Representations:");
                break;
        }

        String inputTextBin = "";

        switch (textMode)
        {
            case STRING:
                inputTextBin = TextConversions.convertStringToBin(inputText);
                break;
            case HEX:
                inputText = inputText.replaceAll("\\s", "");

                inputTextBin = TextConversions.convertHexToBin(inputText);
                System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(inputText, 2)));
                System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(inputTextBin, 8)));
                break;
        }

        System.out.print(System.lineSeparator());

        return inputTextBin;
    }

    public static String keyTextPrep(DesMainFunctions.TextMode textMode) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter your key as a " + textMode.toString() + " Value: ");
        String initialKey = bufferedReader.readLine();

        System.out.print(System.lineSeparator());

        System.out.println("Initial Key: " + initialKey);
        System.out.println("Initial Key Hex and Binary Representations:");

        String initialKeyBin = "";

        switch (textMode)
        {
            case STRING:
                initialKeyBin = TextConversions.convertStringToBin(initialKey);
                break;
            case HEX:
                initialKey = initialKey.replaceAll("\\s", "");

                initialKeyBin = TextConversions.convertHexToBin(initialKey);
                System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(initialKey, 2)));
                System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(initialKeyBin, 8)));
                break;
        }

        System.out.print(System.lineSeparator());

        return initialKeyBin;
    }

    public static void encrypt(String plainTextBin, String initialKeyBin, boolean swapLastRound) {
        final String cipherTextHex = TextConversions.convertBinToHex(DesMainFunctions.desPrep(DesMainFunctions.CipherMode.ENCRYPT, plainTextBin, initialKeyBin, swapLastRound));
        final String cipherTextStr = TextConversions.convertHexToString(cipherTextHex);

        System.out.println("Final CipherText (Hex): " + String.join(" ", StringManipulations.chunkString(cipherTextHex, 2)));
        System.out.println("Final CipherText (String): " + cipherTextStr);
    }

    public static void decrypt(String cipherTextBin, String initialKeyBin, boolean swapLastRound) {
        final String plainTextHex = TextConversions.convertBinToHex(DesMainFunctions.desPrep(DesMainFunctions.CipherMode.DECRYPT, cipherTextBin, initialKeyBin, swapLastRound));
        final String plainTextStr = TextConversions.convertHexToString(plainTextHex);

        System.out.println("Final PlainText (Hex): " + String.join(" ", StringManipulations.chunkString(plainTextHex, 2)));
        System.out.println("Final PlainText (String): " + plainTextStr);
    }

    public static class WrongMenuChoiceException extends Exception {
        @Override
        public String toString() {
            return this.getClass().getCanonicalName() + ": Invalid Choice!";
        }
    }

}
