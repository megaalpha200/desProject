#pragma once
#ifndef DES_FUNCTIONS_H

#include <iostream>
#include <iomanip>
#include <string>
#include <bitset>
#include <vector>
#include <sstream>
#include <math.h>
#include <exception>
#include <algorithm>
#include "base64.h"

namespace DesFunctions
{
	/*--------------------------------P-Boxes-------------------------------------*/

	static const std::vector<int> INITIAL_P_BOX = { 58, 50, 42, 34, 26, 18, 10, 2,
													60, 52, 44, 36, 28, 20, 12, 4,
													62, 54, 46, 38, 30, 22, 14, 6,
													64, 56, 48, 40, 32, 24, 16, 8,
													57, 49, 41, 33, 25, 17, 9, 1,
													59, 51, 43, 35, 27, 19, 11, 3,
													61, 53, 45, 37, 29, 21, 13, 5,
													63, 55, 47, 39, 31, 23, 15, 7 };

	static const std::vector<int> FINAL_P_BOX = { 40, 8, 48, 16, 56, 24, 64, 32,
												39, 7, 47, 15, 55, 23, 63, 31,
												38, 6, 46, 14, 54, 22, 62, 30,
												37, 5, 45, 13, 53, 21, 61, 29,
												36, 4, 44, 12, 52, 20, 60, 28,
												35, 3, 43, 11, 51, 19, 59, 27,
												34, 2, 42, 10, 50, 18, 58, 26,
												33, 1, 41, 9, 49, 17, 57, 25 };

	static const std::vector<int> EXPANSION_P_BOX = { 32, 1, 2, 3, 4, 5,
													4, 5, 6, 7, 8, 9,
													8, 9, 10, 11, 12, 13,
													12, 13, 14, 15, 16, 17,
													16, 17, 18, 19, 20, 21,
													20, 21, 22, 23, 24, 25,
													24, 25, 26, 27, 28, 29,
													28, 29, 30, 31, 32, 1 };

	static const std::vector<int> STRAIGHT_P_BOX = { 16, 7, 20, 21, 29, 12, 28, 17,
													1, 15, 23, 26, 5, 18, 31, 10,
													2, 8, 24, 14, 32, 27, 3, 9,
													19, 13, 30, 6, 22, 11, 4, 25 };

	static const std::vector<int> PARITY_BIT_DROP_P_BOX = { 57, 49, 41, 33, 25, 17, 9, 1,
															58, 50, 42, 34, 26, 18, 10, 2,
															59, 51, 43, 35, 27, 19, 11, 3,
															60, 52, 44, 36, 63, 55, 47, 39,
															31, 23, 15, 7, 62, 54, 46, 38,
															30, 22, 14, 6, 61, 53, 45, 37,
															29, 21, 13, 5, 28, 20, 12, 4 };

	static const std::vector<int> KEY_COMPRESSION_P_BOX = { 14, 17, 11, 24, 1, 5, 3, 28,
															15, 6, 21, 10, 23, 19, 12, 4,
															26, 8, 16, 7, 27, 20, 13, 2,
															41, 52, 31, 37, 47, 55, 30, 40,
															51, 45, 33, 48, 44, 49, 39, 56,
															34, 53, 46, 42, 50, 36, 29, 32 };



	/*----------------------------------------------------------------------------*/

	/*--------------------------------S-Boxes-------------------------------------*/

	static const std::vector<std::vector<int>> S_BOX_1 = { {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
															{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
															{4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
															{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13} };

	static const std::vector<std::vector<int>> S_BOX_2 = { {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
															{3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
															{0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
															{13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9} };

	static const std::vector<std::vector<int>> S_BOX_3 = { {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
															{13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
															{13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
															{1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12} };

	static const std::vector<std::vector<int>> S_BOX_4 = { {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
															{13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
															{10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
															{3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14} };

	static const std::vector<std::vector<int>> S_BOX_5 = { {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
															{14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
															{4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
															{11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3} };

	static const std::vector<std::vector<int>> S_BOX_6 = { {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
															{10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
															{9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
															{4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13} };

	static const std::vector<std::vector<int>> S_BOX_7 = { {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
															{13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
															{1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
															{6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12} };

	static const std::vector<std::vector<int>> S_BOX_8 = { {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
															{1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
															{7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
															{2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11} };

	/*----------------------------------------------------------------------------*/

	static const char BASE2[] = { "01" };
	static const std::vector<std::vector<std::vector<int>>> S_BOX_GROUP = { S_BOX_1, S_BOX_2, S_BOX_3, S_BOX_4, S_BOX_5, S_BOX_6, S_BOX_7, S_BOX_8 };
	static const std::vector<int> SHIFT_ONCE_ROUNDS = { 1, 2, 9, 16 };

	static enum class CipherMode { ENCRYPT, DECRYPT, NONE };
	static enum class TextMode { STRING, HEX };

	/*--------------------------------Function Prototypes-------------------------------------*/

	static void encryptDecryptMenu();
	static std::string encryptDecryptInputTextPrep(std::string, CipherMode, TextMode);
	static std::string keyTextPrep(std::string, TextMode);
	static void encrypt(std::string, std::string, bool, std::pair<std::string, std::string>&);
	static void decrypt(std::string, std::string, bool, std::pair<std::string, std::string>&);
	static std::string desPrep(CipherMode, std::string, std::string, bool);
	static std::string desBinEncryptDecrypt(CipherMode, std::string, std::vector<std::string>&, bool);
	static void generateRoundKeys(std::string, CipherMode, std::vector<std::string>&);
	static std::string roundFunction(CipherMode, std::string, std::string, bool = true);
	static std::string innerRoundFunction(std::string, std::string);
	static std::string xorBinaryBlocks(std::string, std::string);
	static std::string applyPBox(std::string, const std::vector<int>&);
	static std::string applySBox(std::string, const std::vector<std::vector<int>>&);
	static std::string shiftBitsLeft(std::string, int = 1);
	static void chunkString(std::string, int, std::vector<std::string>&);
	static char* reverseConstString(char const*);
	static std::string padLeftWithZeros(std::string, int);
	static std::string padRightWithZeros(std::string, int);
	static std::string convertBin(int, char* = nullptr, int = 1, bool = true);
	static std::string convertStringToHex(std::string);
	static std::string convertHexToBin(std::string);
	static std::string convertBinToHex(std::string);
	static std::string convertHexToString(std::string);
	static std::string convertStringToBin(std::string);
	static std::string joinToString(std::vector<std::string>&, std::string);
	static void removeSpaces(std::string&);

	/*----------------------------------------------------------------------------------------*/

	/*----------------------------------Program Main Functions-------------------------------------*/

	static std::stringstream outputStream;

	static std::string encryptDecryptInputTextPrep(std::string inputText, CipherMode cipherMode, TextMode textMode)
	{
		switch (cipherMode)
		{
		case CipherMode::ENCRYPT:
			outputStream << "PlainText: " << inputText << std::endl;
			outputStream << "PlainText Hex and Binary Representations:" << std::endl;
			break;
		case CipherMode::DECRYPT:
			outputStream << "CipherText: " << inputText << std::endl;
			outputStream << "CipherText Hex and Binary Representations:" << std::endl;
			break;
		default:
			throw std::exception("An unexpected error has occured!");
			break;
		}

		std::string inputTextBin;
		std::vector<std::string> chunkedInputTextHex;
		std::vector<std::string> chunkedInputTextBin;

		if (cipherMode == CipherMode::ENCRYPT && textMode == TextMode::STRING)
		{
			inputTextBin = convertStringToBin(inputText);
		}
		else if (cipherMode == CipherMode::DECRYPT && textMode == TextMode::STRING)
		{
			std::string decodedBase64Str = base64_decode(inputText);
			inputTextBin = convertStringToBin(decodedBase64Str);
		}
		else if (textMode == TextMode::HEX)
		{
			removeSpaces(inputText);
			inputTextBin = convertHexToBin(inputText);

			chunkString(inputText, 2, chunkedInputTextHex);
			chunkString(inputTextBin, 8, chunkedInputTextBin);
			outputStream << "Hex: " << joinToString(chunkedInputTextHex, " ") << std::endl;
			outputStream << "Binary: " << joinToString(chunkedInputTextBin, " ") << std::endl;
		}
		else
			throw std::exception("An unexpected error has occured!");

		outputStream << std::endl;

		return inputTextBin;
	}

	static std::string keyTextPrep(std::string initialKey, TextMode textMode)
	{
		outputStream << std::endl;

		outputStream << "Initial Key: " << initialKey << std::endl;
		outputStream << "Initial Key Hex and Binary Representations:" << std::endl;
		
		std::string initialKeyBin;
		std::vector<std::string> chunkedInitialKeyHex;
		std::vector<std::string> chunkedInitialKeyBin;

		switch (textMode)
		{
		case TextMode::STRING:
			initialKeyBin = convertStringToBin(initialKey);
			break;
		case TextMode::HEX:
			removeSpaces(initialKey);
			initialKeyBin = convertHexToBin(initialKey);
			chunkString(initialKey, 2, chunkedInitialKeyHex);
			chunkString(initialKeyBin, 8, chunkedInitialKeyBin);

			outputStream << "Hex: " << joinToString(chunkedInitialKeyHex, " ") << std::endl;
			outputStream << "Binary: " << joinToString(chunkedInitialKeyBin, " ") << std::endl;
			break;
		default:
			throw std::exception("An unexpected error has occured!");
			break;
		}

		outputStream << std::endl;

		return initialKeyBin;
	}

	static void encrypt(std::string plainTextBin, std::string initialKeyBin, bool swapLastRound, std::pair<std::string, std::string> &outputPair)
	{
		std::string cipherTextHex = convertBinToHex(desPrep(CipherMode::ENCRYPT, plainTextBin, initialKeyBin, swapLastRound));
		std::string cipherTextStr = convertHexToString(cipherTextHex);

		std::vector<std::string> chunkedCipherTextHex;
		chunkString(cipherTextHex, 2, chunkedCipherTextHex);
		std::string chunkedCipherTextHexStr = joinToString(chunkedCipherTextHex, " ");

		outputStream << "Final CipherText (Hex): " << chunkedCipherTextHexStr << std::endl;
		outputStream << "Final CipherText (Base64 String): " << base64_encode((unsigned char *)cipherTextStr.c_str(), cipherTextStr.size()) << std::endl;

		outputPair =  std::pair<std::string, std::string>(chunkedCipherTextHexStr, base64_encode((unsigned char*)cipherTextStr.c_str(), cipherTextStr.size()));
	}

	static void decrypt(std::string cipherTextBin, std::string initialKeyBin, bool swapLastRound, std::pair<std::string, std::string> &outputPair)
	{
		std::string plainTextHex = convertBinToHex(desPrep(CipherMode::DECRYPT, cipherTextBin, initialKeyBin, swapLastRound));
		std::string plainTextStr = convertHexToString(plainTextHex);

		std::vector<std::string> chunkedPlainTextHex;
		chunkString(plainTextHex, 2, chunkedPlainTextHex);
		std::string chunkedPlainTextHexStr = joinToString(chunkedPlainTextHex, " ");

		outputStream << "Final PlainText (Hex): " << chunkedPlainTextHexStr << std::endl;
		outputStream << "Final PlainText (String): " << plainTextStr << std::endl;

		outputPair = std::pair<std::string, std::string>(chunkedPlainTextHexStr, plainTextStr);
	}

	/*---------------------------------------------------------------------------------------------*/

	/*----------------------------------DES Main Functions-------------------------------------*/

	static std::string desPrep(CipherMode mode, std::string inputText, std::string key, bool swapLastRound)
	{
		std::string finalResult = "";
		std::string inputTextBin = inputText;
		std::vector<std::string> inputTextBinChunked;
		chunkString(inputTextBin, 64, inputTextBinChunked);
		for (int i = 0; i < inputTextBinChunked.size(); i++)
		{
			inputTextBinChunked[i] = padRightWithZeros(inputTextBinChunked[i], 64);
		}
		outputStream << ((mode == CipherMode::ENCRYPT) ? "PlainText" : "CipherText") << " Binary: " << joinToString(inputTextBinChunked, " | ") << std::endl;

		std::string keyBin = padRightWithZeros(key, 64);

		std::vector<std::string> roundKeys;
		generateRoundKeys(keyBin, mode, roundKeys);
		outputStream << std::endl;

		int blockNum = 1;
		for (std::string block : inputTextBinChunked)
		{
			outputStream << "For block " << blockNum << "..." << std::endl;
			finalResult += desBinEncryptDecrypt(mode, block, roundKeys, swapLastRound);
		}

		return finalResult;
	}

	static std::string desBinEncryptDecrypt(CipherMode mode, std::string inputTextBin, std::vector<std::string> &roundKeys, bool swapLastRound)
	{
		std::string inputTextPostInitPBox = applyPBox(inputTextBin, INITIAL_P_BOX);

		std::string tempRoundInputText = inputTextPostInitPBox;
		for (int round = 0; round < 16; round++)
		{
			outputStream << "Round " << (round + 1) << "..." << std::endl;

			if ((mode == CipherMode::ENCRYPT && round + 1 == 16) || (mode == CipherMode::DECRYPT && round + 1 == 1))
				tempRoundInputText = roundFunction(mode, tempRoundInputText, roundKeys[round], swapLastRound);
			else
				tempRoundInputText = roundFunction(mode, tempRoundInputText, roundKeys[round]);

			std::vector<std::string> chunkedTempRoundInputText;
			chunkString(convertBinToHex(tempRoundInputText), 8, chunkedTempRoundInputText);

			outputStream << ((mode == CipherMode::ENCRYPT) ? "CipherText" : "PlainText") << " after Round " << padLeftWithZeros(std::to_string(round + 1), 2) << ": ";
			outputStream << joinToString(chunkedTempRoundInputText, " ") << std::endl;

			outputStream << std::endl;
		}

		outputStream << std::endl;
		return applyPBox(tempRoundInputText, FINAL_P_BOX);
	}

	/*-----------------------------------------------------------------------------------------*/

	/*----------------------------------Key Functions-------------------------------------*/

	static void generateRoundKeys(std::string key, CipherMode mode, std::vector<std::string> &roundKeys)
	{
		std::string cipherKey = applyPBox(key, PARITY_BIT_DROP_P_BOX);

		std::string tempShiftedKey = cipherKey;
		for (int round = 1; round <= 16; round++)
		{
			std::string leftBlock = tempShiftedKey.substr(0, (tempShiftedKey.length() / 2));
			std::string rightBlock = tempShiftedKey.substr((tempShiftedKey.length() / 2));

			std::string leftShiftedBlock;
			std::string rightShiftedBlock;

			//Checks if SHIFT_ONCE_ROUNDS contain "round"
			if (find(SHIFT_ONCE_ROUNDS.begin(), SHIFT_ONCE_ROUNDS.end(), round) != SHIFT_ONCE_ROUNDS.end())
			{
				leftShiftedBlock = shiftBitsLeft(leftBlock);
				rightShiftedBlock = shiftBitsLeft(rightBlock);
			}
			else
			{
				leftShiftedBlock = shiftBitsLeft(leftBlock, 2);
				rightShiftedBlock = shiftBitsLeft(rightBlock, 2);
			}

			tempShiftedKey = leftShiftedBlock + rightShiftedBlock;

			std::string compressedKey = applyPBox(tempShiftedKey, KEY_COMPRESSION_P_BOX);
			roundKeys.push_back(compressedKey);
		}

		if (mode == CipherMode::DECRYPT)
		{
			for (int i = 0, j = roundKeys.size() - 1; i < j; i++, j--)
			{
				swap(roundKeys[i], roundKeys[j]);
			}
		}

		for (int round = 0; round < 16; round++)
		{
			std::vector<std::string> chunkedRoundKey;
			chunkString(roundKeys[round], 6, chunkedRoundKey);

			outputStream << "Round " << padLeftWithZeros(std::to_string(round + 1), 2) << " Key: " << joinToString(chunkedRoundKey, " ") << std::endl;
		}

		return;
	}

	/*------------------------------------------------------------------------------------*/

	/*--------------------------------Round Functions-------------------------------------*/

	static std::string roundFunction(CipherMode mode, std::string input, std::string key, bool swap)
	{
		std::string leftInput = input.substr(0, (input.length() / 2));
		std::string rightInput = input.substr((input.length() / 2));

		outputStream << "Pre-Round Input: " << convertBinToHex(leftInput) + " " + convertBinToHex(rightInput) << std::endl;

		std::string leftOutput;
		std::string rightOutput;

		if (mode == CipherMode::ENCRYPT && swap)
		{
			leftOutput = rightInput;
			rightOutput = xorBinaryBlocks(leftInput, innerRoundFunction(rightInput, key));
		}
		else if (mode == CipherMode::DECRYPT && swap)
		{
			leftOutput = xorBinaryBlocks(rightInput, innerRoundFunction(leftInput, key));
			rightOutput = leftInput;
		}
		else
		{
			leftOutput = xorBinaryBlocks(leftInput, innerRoundFunction(rightInput, key));
			rightOutput = rightInput;
		}

		return leftOutput + rightOutput;
	}

	static std::string innerRoundFunction(std::string input, std::string key)
	{
		std::string expandedInput = applyPBox(input, EXPANSION_P_BOX);
		outputStream << "Expansion Result: " << convertBinToHex(expandedInput) << std::endl;
		std::string inputWithAppliedKey = xorBinaryBlocks(expandedInput, key);
		outputStream << "Applied Key Result: " << convertBinToHex(inputWithAppliedKey);
		std::vector<std::string> chunkedInputWithAppliedKey;
		chunkString(inputWithAppliedKey, 6, chunkedInputWithAppliedKey);

		std::string postSBoxResults = "";

		int index = 0;
		for (std::string chunk : chunkedInputWithAppliedKey)
		{
			std::string sBoxResult = applySBox(chunk, S_BOX_GROUP[index]);

			outputStream << "S-Box " << (index + 1) << " input: " << chunk << std::endl;
			outputStream << "S-Box " << (index + 1) << " output: " << sBoxResult << std::endl;

			postSBoxResults += sBoxResult;
			index++;
		}

		return applyPBox(postSBoxResults, STRAIGHT_P_BOX);
	}

	/*------------------------------------------------------------------------------------*/

	/*--------------------------------AUX Functions-------------------------------------*/

	static std::string xorBinaryBlocks(std::string block1, std::string block2)
	{
		std::string block1In = block1;
		std::string block2In = block2;
		std::string result = "";

		if (block1In.length() > block2In.length())
		{
			block2In = padLeftWithZeros(block2In, block1In.length());
		}

		if (block2In.length() > block1In.length())
		{
			block1In = padLeftWithZeros(block1In, block2In.length());
		}

		for (int i = 0; i < block1In.length(); i++)
		{
			int block1Int = stoi(block1In.substr(i, 1));
			int block2Int = stoi(block2In.substr(i, 1));

			result += std::to_string(block1Int ^ block2Int);
		}

		return result;
	}

	static std::string applyPBox(std::string input, const std::vector<int> &pBox)
	{
		std::string output = "";
		output = padLeftWithZeros(output, pBox.size());

		for (int i = 0; i < pBox.size(); i++)
		{
			output[i] = input[pBox[i] - 1];
		}

		return output;
	}

	static std::string applySBox(std::string input, const std::vector<std::vector<int>> &sBox)
	{
		int boxRow = stoi((input.substr(0, 1) + input[input.length() - 1]), nullptr, 2);
		int boxCol = stoi((input.substr(1, input.length() - 2)), nullptr, 2);

		return std::bitset<4>(sBox[boxRow][boxCol]).to_string();
	}

	static std::string shiftBitsLeft(std::string input, int shiftBy)
	{
		int modShiftVal = shiftBy % input.length();
		std::string shiftedString = "";

		shiftedString += input.substr(modShiftVal);
		shiftedString += input.substr(0, modShiftVal);

		return shiftedString;
	}

	/*----------------------------------------------------------------------------------*/

	/*------------------------------------------------String Manipulation Functions------------------------------------------------*/
	void chunkString(std::string s, int size, std::vector<std::string>& chunkedList)
	{
		int startIndex = 0;
		int endIndex = size;

		while (startIndex != endIndex)
		{
			chunkedList.push_back(s.substr(startIndex, size));
			startIndex += size;

			if (endIndex < s.length())
				endIndex += size;
		}
	}

	static char* reverseConstString(char const* str)
	{
		// find length of string 
		int n = strlen(str);

		// create dynamic pointer char array 
		char *rev = new char[n + 1];

		// copy of string to ptr array 
		strcpy_s(rev, n + 1, str);

		// Swap character starting from two 
		// corners 
		for (int i = 0, j = n - 1; i < j; i++, j--)
			std::swap(rev[i], rev[j]);

		// return pointer of reversed string 
		return rev;
	}

	static std::string padLeftWithZeros(std::string s, int amount)
	{
		std::stringstream ss;
		std::string paddedStr;

		ss << "" << std::setfill('0') << std::setw(amount) << s;
		std::getline(ss, paddedStr);

		return paddedStr;
	}

	static std::string padRightWithZeros(std::string s, int amount)
	{
		std::stringstream ss;
		std::string paddedStr;

		ss << s << std::setw(amount - s.length()) << std::setfill('0') << "";
		std::getline(ss, paddedStr);

		return paddedStr;
	}

	static std::string joinToString(std::vector<std::string> &list, std::string delimiter)
	{
		std::string finalStr;

		int count = 0;
		for (const std::string &listStr : list)
		{
			finalStr += listStr;

			count++;
			if (count != list.size())
				finalStr += delimiter;
		}

		return finalStr;
	}

	static void removeSpaces(std::string &input)
	{
		std::stringstream ss;
		std::string finalStr = "";

		for (char c : input)
		{
			if (!isspace(c))
				finalStr += c;
		}

		input = finalStr;
	}
	/*-----------------------------------------------------------------------------------------------------------------------------*/

	/*------------------------------------------------Text Conversion Functions------------------------------------------------*/

	static std::string convertBin(int num, char *buildNum, int placeVal, bool initial)
	{
		int diff = num;
		int pVal = placeVal;
		int pCount = log2(pVal);
		char rem;

		if (buildNum == nullptr)
		{
			buildNum = new char[1];
			buildNum[0] = NULL;
		}

		char *tempPtr = buildNum;

		if (strlen(buildNum) == 0)
		{
			while (pVal < num)
			{
				pVal *= 2;
			}

			if (pVal > num)
			{
				pVal /= 2;
			}
		}
		else if (pVal != 0)
			pVal /= 2;

		pCount = log2(pVal);

		if (pVal != 0)
		{
			if (pVal > diff)
				rem = BASE2[0];
			else
			{
				diff -= pVal;
				rem = BASE2[1];
			}

			*tempPtr = rem;
			tempPtr++;
			convertBin(diff, tempPtr, pVal, false);
		}
		else
		{
			pCount = 0;

			if (strlen(buildNum) == 0)
				buildNum[0] = '0';
		}

		if (initial)
		{
			buildNum[pCount + 1] = NULL;
			return std::string(buildNum);
		}

		return "";
	}

	static std::string convertStringToHex(std::string input)
	{
		std::string finalHexStr = "";

		for (char c : input)
		{
			std::stringstream ss;
			int charAsInt = (int)c % 256;

			if (charAsInt < 0)
				charAsInt = charAsInt + 256;

			ss << std::hex << charAsInt;
			std::string hexStr = ss.str();
			finalHexStr += padLeftWithZeros(hexStr, 2);
		}

		return finalHexStr;
	}

	static std::string convertHexToBin(std::string input)
	{
		std::string binStr = "";

		for (int i = 0; i < input.length(); i++)
		{
			std::string hex = input.substr(i, 1);
			std::string hexInBinary = padLeftWithZeros(convertBin(stoi(hex, nullptr, 16)), 4);

			binStr += hexInBinary;
		}

		return binStr;
	}

	static std::string convertBinToHex(std::string input)
	{
		std::stringstream ss;

		std::vector<std::string> chunkedBinList;
		chunkString(input, 4, chunkedBinList);

		for (std::string bin : chunkedBinList)
		{
			int inputBase10 = stoll(bin, nullptr, 2);
			ss << std::hex << inputBase10;
		}

		return ss.str();
	}

	static std::string convertHexToString(std::string input)
	{
		std::vector<std::string> chunkedHexList;
		std::string finalStr = "";

		chunkString(input, 2, chunkedHexList);

		for (std::string hex : chunkedHexList)
		{
			char convertedChar = (char)stoi(hex, nullptr, 16);
			finalStr += convertedChar;
		}

		return finalStr;
	}

	static std::string convertStringToBin(std::string input)
	{
		std::string stASCIIHex = convertStringToHex(input);
		std::string stASCIIBin = convertHexToBin(stASCIIHex);

		std::vector<std::string> stASCIIHexList;
		std::vector<std::string> stASCIIBinList;
		chunkString(stASCIIHex, 2, stASCIIHexList);
		chunkString(stASCIIBin, 8, stASCIIBinList);

		outputStream << "Hex: " << joinToString(stASCIIHexList, " ") << std::endl;
		outputStream << "Binary: " << joinToString(stASCIIBinList, " ") << std::endl;

		return stASCIIBin;
	}
	/*-------------------------------------------------------------------------------------------------------------------------*/
}

#endif // !DES_FUNCTIONS_H