import * as aux from './auxFuncs';
import { convertBinToHex } from './conversions';

/*--------------------------------P-Boxes-------------------------------------*/

const INITIAL_P_BOX = [58, 50, 42, 34, 26, 18, 10, 2,
                        60, 52, 44, 36, 28, 20, 12, 4,
                        62, 54, 46, 38, 30, 22, 14, 6,
                        64, 56, 48, 40, 32, 24, 16, 8,
                        57, 49, 41, 33, 25, 17, 9, 1,
                        59, 51, 43, 35, 27, 19, 11, 3,
                        61, 53, 45, 37, 29, 21, 13, 5,
                        63, 55, 47, 39, 31, 23, 15, 7];

const FINAL_P_BOX = [40, 8, 48, 16, 56, 24, 64, 32,
                    39, 7, 47, 15, 55, 23, 63, 31,
                    38, 6, 46, 14, 54, 22, 62, 30,
                    37, 5, 45, 13, 53, 21, 61, 29,
                    36, 4, 44, 12, 52, 20, 60, 28,
                    35, 3, 43, 11, 51, 19, 59, 27,
                    34, 2, 42, 10, 50, 18, 58, 26,
                    33, 1, 41, 9, 49, 17, 57, 25];

const EXPANSION_P_BOX = [32, 1, 2, 3, 4, 5,
                        4, 5, 6, 7, 8, 9,
                        8, 9, 10, 11, 12, 13,
                        12, 13, 14, 15, 16, 17,
                        16, 17, 18, 19, 20, 21,
                        20, 21, 22, 23, 24, 25,
                        24, 25, 26, 27, 28, 29,
                        28, 29, 30, 31, 32, 1];

const STRAIGHT_P_BOX = [16, 7, 20, 21, 29, 12, 28, 17,
                        1, 15, 23, 26, 5, 18, 31, 10,
                        2, 8, 24, 14, 32, 27, 3, 9,
                        19, 13, 30, 6, 22, 11, 4, 25];

const PARITY_BIT_DROP_P_BOX = [57, 49, 41, 33, 25, 17, 9, 1,
                            58, 50, 42, 34, 26, 18, 10, 2,
                            59, 51, 43, 35, 27, 19, 11, 3,
                            60, 52, 44, 36, 63, 55, 47, 39,
                            31, 23, 15, 7, 62, 54, 46, 38,
                            30, 22, 14, 6, 61, 53, 45, 37,
                            29, 21, 13, 5, 28, 20, 12, 4];

const KEY_COMPRESSION_P_BOX = [14, 17, 11, 24, 1, 5, 3, 28,
                            15, 6, 21, 10, 23, 19, 12, 4,
                            26, 8, 16, 7, 27, 20, 13, 2,
                            41, 52, 31, 37, 47, 55, 30, 40,
                            51, 45, 33, 48, 44, 49, 39, 56,
                            34, 53, 46, 42, 50, 36, 29, 32];

/*----------------------------------------------------------------------------*/

/*--------------------------------S-Boxes-------------------------------------*/

const S_BOX_1 = [[14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7],
                    [0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8],
                    [4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0],
                    [15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13]];

const S_BOX_2 = [[15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10],
                    [3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5],
                    [0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15],
                    [13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9]];

const S_BOX_3 = [[10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8],
                    [13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1],
                    [13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7],
                    [1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12]];

const S_BOX_4 = [[7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15],
                    [13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9],
                    [10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4],
                    [3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14]];

const S_BOX_5 = [[2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9],
                    [14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6],
                    [4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14],
                    [11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3]];

const S_BOX_6 = [[12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11],
                    [10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8],
                    [9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6],
                    [4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13]];

const S_BOX_7 = [[4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1],
                    [13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6],
                    [1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2],
                    [6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12]];

const S_BOX_8 = [[13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7],
                    [1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2],
                    [7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8],
                    [2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11]];

/*----------------------------------------------------------------------------*/

const S_BOX_GROUP = [S_BOX_1, S_BOX_2, S_BOX_3, S_BOX_4, S_BOX_5, S_BOX_6, S_BOX_7, S_BOX_8];
const SHIFT_ONCE_ROUNDS = [1, 2, 9, 16];

export let outputLines = [];

export const desEncrypt = (plainText, key, swapLastRound = false) => {
    outputLines = [];
    const finalResultArray = [];
    const plainTextBin = plainText;
    const plainTextHex = convertBinToHex(plainTextBin).padEnd(16, '0');
    const plainTextBinChuncked = aux.string_chop(plainTextBin, 64);

    for (let chunk = 0; chunk < plainTextBinChuncked.length; chunk++) {
        plainTextBinChuncked[chunk] = plainTextBinChuncked[chunk].padEnd(64, '0');
    }

    outputLines.push({output: `PlainText Binary: ${plainTextBinChuncked.join(" | ")}`})
    outputLines.push({output: `PlainText Hex: ${aux.string_chop(plainTextHex, 2).join(" ")}`})
    outputLines.push({output: ""});

    const keyBin = key.padEnd(64, '0');
    outputLines.push({output: `Initial Key Binary: ${aux.string_chop(keyBin, 8).join(" | ")}`})
    outputLines.push({output: `Initial Key Hex: ${aux.string_chop(convertBinToHex(keyBin), 2).join(" ")}`})
    outputLines.push({output: ""});

    const roundKeys = generateRoundKeys(keyBin);
    outputLines.push({output: ""});

    for(let chunk = 0; chunk < plainTextBinChuncked.length; chunk++) {
        outputLines.push({output: `For block ${chunk + 1}...`});
        finalResultArray.push(desBinEncrypt(plainTextBinChuncked[chunk].toString(), roundKeys, swapLastRound));
    }

    return finalResultArray.join("");
}

const desBinEncrypt = (plainTextBin, roundKeys, swapLastRound = false) => {
    const cipherTextPostInitPBox = applyPBox(plainTextBin, INITIAL_P_BOX);

    let tempRoundCipherText = cipherTextPostInitPBox;
    for(let round = 0; round < 16; round++) {
        outputLines.push({output: `Round ${round+1}...`});

        if ((round + 1) === 16) {
            tempRoundCipherText = roundFunction(tempRoundCipherText, roundKeys[round], swapLastRound);
        }
        else {
            tempRoundCipherText = roundFunction(tempRoundCipherText, roundKeys[round], true);
        }

        outputLines.push({output: `CipherText after Round ${(round + 1).toString().padStart(2, '0')}: ${aux.string_chop(convertBinToHex(tempRoundCipherText), 8).join(" ")}`});
        outputLines.push({output: ""});
    }

    outputLines.push({output: ""});
    return applyPBox(tempRoundCipherText, FINAL_P_BOX);
}

/*----------------------------------Key Functions-------------------------------------*/

const generateRoundKeys = (key) => {
    const roundKeys = [];
    const cipherKey = applyPBox(key, PARITY_BIT_DROP_P_BOX);

    let tempShiftedKey = cipherKey;
    for (let round = 1; round <= 16; round++) {
        const leftBlock = tempShiftedKey.substring(0, (tempShiftedKey.length / 2)).toString();
        const rightBlock = tempShiftedKey.substring((tempShiftedKey.length / 2), tempShiftedKey.length).toString();

        let leftShiftedBlock;
        let rightShiftedBlock;

        if (SHIFT_ONCE_ROUNDS.includes(round)) {
            leftShiftedBlock = aux.shiftBitsLeft(leftBlock, 1);
            rightShiftedBlock = aux.shiftBitsLeft(rightBlock, 1);
        }
        else {
            leftShiftedBlock = aux.shiftBitsLeft(leftBlock, 2);
            rightShiftedBlock = aux.shiftBitsLeft(rightBlock, 2);
        }

        tempShiftedKey = leftShiftedBlock + rightShiftedBlock;

        const compressedKey = applyPBox(tempShiftedKey, KEY_COMPRESSION_P_BOX);
        outputLines.push({output: `Round ${round.toString().padStart(2,'0')} Key: ${aux.string_chop(compressedKey, 6).join(" ")}`});
        roundKeys.push(compressedKey);
    }

    return roundKeys;
}

/*------------------------------------------------------------------------------------*/

/*--------------------------------Round Functions-------------------------------------*/

const roundFunction = (input, key, swap) => {
    const leftInput = input.substring(0, (input.length / 2)).toString();
    const rightInput = input.substring((input.length / 2), input.length).toString();

    outputLines.push({output: `Pre-Round Input: ${convertBinToHex(leftInput)} ${convertBinToHex(rightInput)}`});

    let leftOutput;
    let rightOutput;

    if (swap) {
        leftOutput = rightInput;
        rightOutput = xorBinaryBlocks(leftInput, innerRoundFunction(rightInput, key));
    }
    else {
        leftOutput = xorBinaryBlocks(leftInput, innerRoundFunction(rightInput, key));
        rightOutput = rightInput;
    }

    return leftOutput + rightOutput;
}

const innerRoundFunction = (input, key) => {
    const expandedInput = applyPBox(input, EXPANSION_P_BOX);
    outputLines.push({output: `Expansion Result: ${convertBinToHex(expandedInput)}`});
    const inputWithAppliedKey = xorBinaryBlocks(expandedInput, key);
    outputLines.push({output: `Applied Key Result: ${convertBinToHex(inputWithAppliedKey)}`});
    const chunkedInputWithAppliedKey = aux.string_chop(inputWithAppliedKey, 6);

    const postSBoxResults = [];

    for(let i = 0; i < chunkedInputWithAppliedKey.length; i++) {
        const sBoxResult = applySBox(chunkedInputWithAppliedKey[i], S_BOX_GROUP[i]);

        outputLines.push({output: `S-Box ${i + 1} input: ${chunkedInputWithAppliedKey[i]}`});
        outputLines.push({output: `S-Box ${i + 1} output: ${sBoxResult}`});

        postSBoxResults.push(sBoxResult);
    }

    const postSBoxResultStr = postSBoxResults.join("");
    return applyPBox(postSBoxResultStr, STRAIGHT_P_BOX);
}

/*------------------------------------------------------------------------------------*/

/*--------------------------------AUX Functions-------------------------------------*/

const xorBinaryBlocks = (block1, block2) => {
    let block1In = block1;
    let block2In = block2;
    const resultArray = [];

    if(block1In.length > block2In.length) {
        block2In=block2In.padStart(block1In.length, '0');
    }
    else if (block2In.length > block1In.length) {
        block1In = block1In.padStart(block2In.length, '0')
    }

    for(let i = 0; i < block1In.length; i++) {
        resultArray.push((parseInt(block1In[i], 2)^parseInt(block2In[i], 2)).toString());
    }

    return resultArray.join("");
}

const applyPBox = (input, pBox) => {
    const outputArray = [];

    for(let i = 0; i < pBox.length; i++) {
        outputArray.push('0');
    }

    for(let j = 0; j < pBox.length; j++) {
        outputArray[j] = input[(pBox[j] - 1)];
    }

    return outputArray.join("");
}

const applySBox = (input, sBox) => { 
    const boxRow = parseInt(`${input[0]}${input[input.length-1]}`, 2);
    const boxCol = parseInt(input.substring(1, input.length-1), 2);

    return sBox[boxRow][boxCol].toString(2).padStart(4, '0');
}

/*----------------------------------------------------------------------------------*/