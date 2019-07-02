package desfunctions;

import stringmanip.StringManipulations;
import textconversions.TextConversions;

import java.util.ArrayList;
import java.util.List;

public class DesMainFunctions {
    /*--------------------------------P-Boxes-------------------------------------*/

    public static final int[] INITIAL_P_BOX = {58, 50, 42, 34, 26, 18, 10, 2,
                                        60, 52, 44, 36, 28, 20, 12, 4,
                                        62, 54, 46, 38, 30, 22, 14, 6,
                                        64, 56, 48, 40, 32, 24, 16, 8,
                                        57, 49, 41, 33, 25, 17, 9, 1,
                                        59, 51, 43, 35, 27, 19, 11, 3,
                                        61, 53, 45, 37, 29, 21, 13, 5,
                                        63, 55, 47, 39, 31, 23, 15, 7};

    public static final int[] FINAL_P_BOX = {40, 8, 48, 16, 56, 24, 64, 32,
                                        39, 7, 47, 15, 55, 23, 63, 31,
                                        38, 6, 46, 14, 54, 22, 62, 30,
                                        37, 5, 45, 13, 53, 21, 61, 29,
                                        36, 4, 44, 12, 52, 20, 60, 28,
                                        35, 3, 43, 11, 51, 19, 59, 27,
                                        34, 2, 42, 10, 50, 18, 58, 26,
                                        33, 1, 41, 9, 49, 17, 57, 25};

    public static final int[] EXPANSION_P_BOX = {32, 1, 2, 3, 4, 5,
                                            4, 5, 6, 7, 8, 9,
                                            8, 9, 10, 11, 12, 13,
                                            12, 13, 14, 15, 16, 17,
                                            16, 17, 18, 19, 20, 21,
                                            20, 21, 22, 23, 24, 25,
                                            24, 25, 26, 27, 28, 29,
                                            28, 29, 30, 31, 32, 1};

    public static final int[] STRAIGHT_P_BOX = {16, 7, 20, 21, 29, 12, 28, 17,
                                            1, 15, 23, 26, 5, 18, 31, 10,
                                            2, 8, 24, 14, 32, 27, 3, 9,
                                            19, 13, 30, 6, 22, 11, 4, 25};

    public static final int[] PARITY_BIT_DROP_P_BOX = {57, 49, 41, 33, 25, 17, 9, 1,
                                                58, 50, 42, 34, 26, 18, 10, 2,
                                                59, 51, 43, 35, 27, 19, 11, 3,
                                                60, 52, 44, 36, 63, 55, 47, 39,
                                                31, 23, 15, 7, 62, 54, 46, 38,
                                                30, 22, 14, 6, 61, 53, 45, 37,
                                                29, 21, 13, 5, 28, 20, 12, 4};

    public static final int[] KEY_COMPRESSION_P_BOX = {14, 17, 11, 24, 1, 5, 3, 28,
                                                15, 6, 21, 10, 23, 19, 12, 4,
                                                26, 8, 16, 7, 27, 20, 13, 2,
                                                41, 52, 31, 37, 47, 55, 30, 40,
                                                51, 45, 33, 48, 44, 49, 39, 56,
                                                34, 53, 46, 42, 50, 36, 29, 32};

    /*----------------------------------------------------------------------------*/

    /*--------------------------------S-Boxes-------------------------------------*/

    public static final int[][] S_BOX_1 = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};

    public static final int[][] S_BOX_2 = {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};

    public static final int[][] S_BOX_3 = {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};

    public static final int[][] S_BOX_4 = {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};

    public static final int[][] S_BOX_5 = {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};

    public static final int[][] S_BOX_6 = {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};

    public static final int[][] S_BOX_7 = {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};

    public static final int[][] S_BOX_8 = {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};

    /*----------------------------------------------------------------------------*/

    private static final int[][][] S_BOX_GROUP = {S_BOX_1, S_BOX_2, S_BOX_3, S_BOX_4, S_BOX_5, S_BOX_6, S_BOX_7, S_BOX_8};
    private static final List<Integer> SHIFT_ONCE_ROUNDS = new ArrayList<Integer>() {
        {
            add(1); add(2); add(9); add(16);
        }
    };

    public enum CipherMode {
        ENCRYPT, DECRYPT;

        public String toString() {
            return (this == ENCRYPT) ? "Encrypt" : "Decrypt";
        }
    }

    public enum TextMode {
        STRING, HEX;

        public String toString() {
            return (this == STRING) ? "String" : "Hexadecimal";
        }
    }

    public static StringBuilder debugOutput = new StringBuilder();

    public static void main(String[] args) {
        //generateRoundKeys(TextConversions.convertStringToBin("Computer"), CipherMode.ENCRYPT);
    }

    public static String desPrep(CipherMode cipherMode, String inputText, String key, boolean swapLastRound) {
        final StringBuilder finalResult = new StringBuilder();
        final String inputTextBin = inputText;
        final List<String> inputTextBinChunkedNoPadding = StringManipulations.chunkString(inputTextBin, 64);
        final List<String> inputTextBinChunked = new ArrayList<>();
        for (String chunk : inputTextBinChunkedNoPadding) {
            inputTextBinChunked.add(StringManipulations.padRightWithZeros(chunk, 64));
        }
        debugOutput.append((cipherMode == CipherMode.ENCRYPT) ? "PlainText" : "CipherText" + " Binary: " + String.join(" | ", inputTextBinChunked) + System.lineSeparator());

        final String keyBin = StringManipulations.padRightWithZeros(key, 64);

        final List<String> roundKeys = generateRoundKeys(keyBin, cipherMode);
        debugOutput.append(System.lineSeparator());

        int blockNum = 1;
        for (String block : inputTextBinChunked) {
            debugOutput.append("For block " + blockNum + "..." + System.lineSeparator());

            finalResult.append(desBinEncryptDecrypt(cipherMode, block, roundKeys, swapLastRound));
        }

        return finalResult.toString();
    }

    private static String desBinEncryptDecrypt(CipherMode cipherMode, String inputTextBin, List<String> roundKeys, boolean swapLastRound) {
        final String inputTextPostInitPBox = applyPBox(inputTextBin, INITIAL_P_BOX);

        String tempRoundInputText = inputTextPostInitPBox;
        for (int round = 0; round < 16; round++) {
            debugOutput.append("Round " + (round+1) + "..." + System.lineSeparator());

            if ((cipherMode == CipherMode.ENCRYPT && round + 1 == 16) || (cipherMode == CipherMode.DECRYPT && round + 1 == 1))
                tempRoundInputText = roundFunction(cipherMode, tempRoundInputText, roundKeys.get(round), swapLastRound);
            else
                tempRoundInputText = roundFunction(cipherMode, tempRoundInputText, roundKeys.get(round), true);

            debugOutput.append((cipherMode == CipherMode.ENCRYPT) ? "CipherText" : "PlainText").append(" after Round ").append(StringManipulations.padLeftWithZeros(String.valueOf(round + 1), 2)).append(": ").append(String.join(" ", StringManipulations.chunkString(TextConversions.convertBinToHex(tempRoundInputText), 8))).append(System.lineSeparator());

            debugOutput.append(System.lineSeparator());
        }

        debugOutput.append(System.lineSeparator());
        return applyPBox(tempRoundInputText, FINAL_P_BOX);
    }



    /*----------------------------------Key Functions-------------------------------------*/

    private static List<String> generateRoundKeys(String key, CipherMode cipherMode) {
        ArrayList<String> roundKeys = new ArrayList<>();

        final String cipherKey = applyPBox(key, PARITY_BIT_DROP_P_BOX);

        String tempShiftedKey = cipherKey;
        for (int round = 1; round <= 16; round++) {
            final String leftBlock = tempShiftedKey.substring(0, (tempShiftedKey.length() / 2));
            final String rightBlock = tempShiftedKey.substring((tempShiftedKey.length() / 2));

            final String leftShiftedBlock;
            final String rightShiftedBlock;

            if (SHIFT_ONCE_ROUNDS.contains(round)) {
                leftShiftedBlock = shiftBitsLeft(leftBlock, 1);
                rightShiftedBlock = shiftBitsLeft(rightBlock, 1);
            }
            else {
                leftShiftedBlock = shiftBitsLeft(leftBlock, 2);
                rightShiftedBlock = shiftBitsLeft(rightBlock, 2);
            }

            tempShiftedKey = leftShiftedBlock + rightShiftedBlock;

            final String compressedKey = applyPBox(tempShiftedKey, KEY_COMPRESSION_P_BOX);
            roundKeys.add(compressedKey);
        }

        if (cipherMode == CipherMode.DECRYPT) {
            final ArrayList<String> tempRoundKeys = new ArrayList<>();
            final ArrayList<String> reversedRoundKeys = new ArrayList<>();

            for (String roundKey : roundKeys) {
                tempRoundKeys.add(roundKey);
            }

            for (int roundKeyIndex = roundKeys.size() - 1; roundKeyIndex >= 0; roundKeyIndex--) {
                reversedRoundKeys.add(tempRoundKeys.get(roundKeyIndex));
            }

            roundKeys = reversedRoundKeys;
        }

        for (int round = 0; round < 16; round++) {
            debugOutput.append("Round " + StringManipulations.padLeftWithZeros(String.valueOf(round+1), 2) + " Key: " + String.join(" ", StringManipulations.chunkString(roundKeys.get(round), 6)) + System.lineSeparator());
        }

        return roundKeys;
    }

    /*------------------------------------------------------------------------------------*/

    /*--------------------------------Round Functions-------------------------------------*/

    private static String roundFunction(CipherMode cipherMode, String input, String key, boolean swap) {
        final String leftInput = input.substring(0, (input.length() / 2));
        final String rightInput = input.substring((input.length() / 2));

        debugOutput.append("Pre-Round Input: " + TextConversions.convertBinToHex(leftInput) + " " + TextConversions.convertBinToHex(rightInput) + System.lineSeparator());

        final String leftOutput;
        final String rightOutput;

        if (swap && cipherMode == CipherMode.ENCRYPT) {
            leftOutput = rightInput;
            rightOutput = xorBinaryBlocks(leftInput, innerRoundFunction(rightInput, key));
        }
        else if (swap && cipherMode == CipherMode.DECRYPT) {
            leftOutput = xorBinaryBlocks(rightInput, innerRoundFunction(leftInput, key));
            rightOutput = leftInput;
        }
        else {
            leftOutput = xorBinaryBlocks(leftInput, innerRoundFunction(rightInput, key));
            rightOutput = rightInput;
        }

        return leftOutput + rightOutput;
    }

    private static String innerRoundFunction(String input, String key) {
        final String expandedInput = applyPBox(input, EXPANSION_P_BOX);
        debugOutput.append("Expansion Result: " + TextConversions.convertBinToHex(expandedInput) + System.lineSeparator());
        final String inputWithAppliedKey = xorBinaryBlocks(expandedInput, key);
        debugOutput.append("Applied Key Result: " + TextConversions.convertBinToHex(inputWithAppliedKey) + System.lineSeparator());
        final List<String> chunkedInputWithAppliedKey = StringManipulations.chunkString(inputWithAppliedKey, 6);

        final ArrayList<String> postSBoxResults = new ArrayList<>();

        int index = 0;
        for (String chunk: chunkedInputWithAppliedKey) {
            final String sBoxResult = applySBox(chunk, S_BOX_GROUP[index]);

            debugOutput.append("S-Box " + (index + 1) + " input: " + chunk + System.lineSeparator());
            debugOutput.append("S-Box " + (index + 1) + " output: " + sBoxResult + System.lineSeparator());

            postSBoxResults.add(sBoxResult);

            index++;
        }

        final String postSBoxResultStr = String.join("", postSBoxResults);

        return applyPBox(postSBoxResultStr, STRAIGHT_P_BOX);
    }

    /*------------------------------------------------------------------------------------*/

    /*--------------------------------AUX Functions-------------------------------------*/

    public static String xorBinaryBlocks(String block1, String block2) {
        String block1In = block1;
        String block2In = block2;
        final StringBuilder result = new StringBuilder();

        if (block1In.length() > block2In.length()) {
            block2In = StringManipulations.padLeftWithZeros(block2In, block1In.length());
        }
        else if (block2In.length() > block1In.length()) {
            block1In = StringManipulations.padLeftWithZeros(block1In, block2In.length());
        }

        for (int i = 0; i < block1In.length(); i++) {
            result.append(Integer.parseInt(String.valueOf(block1In.charAt(i))) ^ Integer.parseInt(String.valueOf(block2In.charAt(i))));
        }

        return result.toString();
    }

    public static String applyPBox(String input, int[] pBox) {
        final StringBuilder output = new StringBuilder();
        final ArrayList<Character> outputArrayList = new ArrayList<>();

        for (int i = 1; i <= pBox.length; i++) {
            outputArrayList.add('0');
        }

        for (int j = 0; j < pBox.length; j++) {
            outputArrayList.set(j, input.charAt(pBox[j] - 1));
        }

        for (char c : outputArrayList) {
            output.append(c);
        }

        return output.toString();
    }

    public static String applySBox(String input, int[][] sBox) {
        final int boxRow = Integer.parseInt(String.valueOf(input.charAt(0)) + input.charAt(input.length() - 1), 2);
        final int boxCol = Integer.parseInt(input.substring(1, input.length() - 1), 2);

        return StringManipulations.padLeftWithZeros(Integer.toBinaryString(sBox[boxRow][boxCol]), 4);
    }

    public static String shiftBitsLeft(String input, int shiftBy) {
        final int modShiftVal = shiftBy % input.length();
        final StringBuilder shiftedString = new StringBuilder();

        shiftedString.append(input.substring(modShiftVal));

        for (int i = 0; i <= modShiftVal - 1; i++) {
            shiftedString.append(input.charAt(i));
        }

        return shiftedString.toString();
    }

    /*----------------------------------------------------------------------------------*/
}
