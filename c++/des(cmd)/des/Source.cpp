#include <iostream>
#include <iomanip>
#include <string>
#include <bitset>
#include <vector>
#include <sstream>
#include <fstream>
#include <math.h>
#include <exception>
#include <algorithm>
using namespace std;

/*--------------------------------P-Boxes-------------------------------------*/

const vector<int> INITIAL_P_BOX = { 58, 50, 42, 34, 26, 18, 10, 2,
									60, 52, 44, 36, 28, 20, 12, 4,
									62, 54, 46, 38, 30, 22, 14, 6,
									64, 56, 48, 40, 32, 24, 16, 8,
									57, 49, 41, 33, 25, 17, 9, 1,
									59, 51, 43, 35, 27, 19, 11, 3,
									61, 53, 45, 37, 29, 21, 13, 5,
									63, 55, 47, 39, 31, 23, 15, 7 };

const vector<int> FINAL_P_BOX = { 40, 8, 48, 16, 56, 24, 64, 32,
								39, 7, 47, 15, 55, 23, 63, 31,
								38, 6, 46, 14, 54, 22, 62, 30,
								37, 5, 45, 13, 53, 21, 61, 29,
								36, 4, 44, 12, 52, 20, 60, 28,
								35, 3, 43, 11, 51, 19, 59, 27,
								34, 2, 42, 10, 50, 18, 58, 26,
								33, 1, 41, 9, 49, 17, 57, 25 };

const vector<int> EXPANSION_P_BOX = { 32, 1, 2, 3, 4, 5,
									4, 5, 6, 7, 8, 9,
									8, 9, 10, 11, 12, 13,
									12, 13, 14, 15, 16, 17,
									16, 17, 18, 19, 20, 21,
									20, 21, 22, 23, 24, 25,
									24, 25, 26, 27, 28, 29,
									28, 29, 30, 31, 32, 1 };

const vector<int> STRAIGHT_P_BOX = { 16, 7, 20, 21, 29, 12, 28, 17,
									1, 15, 23, 26, 5, 18, 31, 10,
									2, 8, 24, 14, 32, 27, 3, 9,
									19, 13, 30, 6, 22, 11, 4, 25 };

const vector<int> PARITY_BIT_DROP_P_BOX = { 57, 49, 41, 33, 25, 17, 9, 1,
											58, 50, 42, 34, 26, 18, 10, 2,
											59, 51, 43, 35, 27, 19, 11, 3,
											60, 52, 44, 36, 63, 55, 47, 39,
											31, 23, 15, 7, 62, 54, 46, 38,
											30, 22, 14, 6, 61, 53, 45, 37,
											29, 21, 13, 5, 28, 20, 12, 4 };

const vector<int> KEY_COMPRESSION_P_BOX = { 14, 17, 11, 24, 1, 5, 3, 28,
											15, 6, 21, 10, 23, 19, 12, 4,
											26, 8, 16, 7, 27, 20, 13, 2,
											41, 52, 31, 37, 47, 55, 30, 40,
											51, 45, 33, 48, 44, 49, 39, 56,
											34, 53, 46, 42, 50, 36, 29, 32 };



/*----------------------------------------------------------------------------*/

/*--------------------------------S-Boxes-------------------------------------*/

const vector<vector<int>> S_BOX_1 = { {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
									{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
									{4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
									{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13} };

const vector<vector<int>> S_BOX_2 = { {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
									{3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
									{0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
									{13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9} };

const vector<vector<int>> S_BOX_3 = { {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
									{13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
									{13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
									{1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12} };

const vector<vector<int>> S_BOX_4 = { {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
									{13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
									{10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
									{3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14} };

const vector<vector<int>> S_BOX_5 = { {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
									{14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
									{4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
									{11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3} };

const vector<vector<int>> S_BOX_6 = { {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
									{10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
									{9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
									{4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13} };

const vector<vector<int>> S_BOX_7 = { {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
									{13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
									{1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
									{6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12} };

const vector<vector<int>> S_BOX_8 = { {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
									{1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
									{7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
									{2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11} };

/*----------------------------------------------------------------------------*/

const char BASE2[] = { "01" };
const vector<vector<vector<int>>> S_BOX_GROUP = { S_BOX_1, S_BOX_2, S_BOX_3, S_BOX_4, S_BOX_5, S_BOX_6, S_BOX_7, S_BOX_8 };
const vector<int> SHIFT_ONCE_ROUNDS = { 1, 2, 9, 16 };

enum class CipherMode {ENCRYPT, DECRYPT, NONE};
enum class TextMode {STRING, HEX};

/*--------------------------------Function Prototypes-------------------------------------*/

void encryptDecryptMenu();
static string encryptDecryptInputTextPrep(CipherMode, TextMode);
static string keyTextPrep(TextMode);
void encrypt(string, string, bool);
void decrypt(string, string, bool);
string desPrep(CipherMode, string, string, bool);
string desBinEncryptDecrypt(CipherMode, string, vector<string>&, bool);
void generateRoundKeys(string, CipherMode, vector<string>&);
string roundFunction(CipherMode, string, string, bool = true);
string innerRoundFunction(string, string);
string xorBinaryBlocks(string, string);
string applyPBox(string, const vector<int>&);
string applySBox(string, const vector<vector<int>>&);
string shiftBitsLeft(string, int = 1);
void chunkString(string, int, vector<string>&);
char* reverseConstString(char const*);
string padLeftWithZeros(string, int);
string padRightWithZeros(string, int);
string convertBin(int, char* = nullptr, int = 1, bool = true);
string convertStringToHex(string);
string convertHexToBin(string);
string convertBinToHex(string);
string convertHexToString(string);
string convertStringToBin(string);
string joinToString(vector<string>&, string);
void removeSpaces(string&);

/*----------------------------------------------------------------------------------------*/

/*----------------------------------Program Main Functions-------------------------------------*/

class WrongMenuChoiceException : public exception
{
public:
	virtual const char* what() const throw()
	{
		return "Invalid Choice!";
	}
} wrongChoiceEx;

fstream outputStream;

int main()
{
	outputStream = fstream("debug.txt", ios::out);
	encryptDecryptMenu();
	outputStream.close();

	cout << endl;
	system("pause");
	return 0;
}

void encryptDecryptMenu()
{
	CipherMode mode = CipherMode::NONE;
	//pair<string, string> inputTextAndKeyPair;
	string inputText = "";
	string inputKey = "";
	bool swapLastRound;

	bool isPairInitialized = false;
	bool isSwapRoundInitialized = false;

	string userInput;
	string modeStr;

	do
	{
		try
		{
			outputStream = fstream("debug.txt", ios::out | ios::app);

			if (mode == CipherMode::NONE)
			{
				cout << endl;
				cout << "DES Encryptor/Decryptor" << endl;
				cout << "Using C++" << endl;
				cout << "Created by Jose A. Alvarado" << endl;
				cout << "Copyright J.A.A. Productions 2019" << endl;

				cout << endl;

				cout << "Please choose an option: " << endl;
				cout << "\t1. Encrypt" << endl;
				cout << "\t2. Decrypt" << endl;
				cout << "\t3. Quit" << endl;
				cout << "Choice: ";

				getline(cin, userInput);
				switch (stoi(userInput, nullptr))
				{
				case 1:
					mode = CipherMode::ENCRYPT;
					modeStr = "Encrypt";
					break;
				case 2:
					mode = CipherMode::DECRYPT;
					modeStr = "Decrypt";
					break;
				case 3:
					cout << "Goodbye!" << endl;
					return;
					break;
				default:
					mode = CipherMode::NONE;
					throw wrongChoiceEx;
					break;
				}
			}

			if (!isPairInitialized)
			{
				cout << endl;

				cout << "Please choose an option: " << endl;
				cout << "\t1. " << modeStr << " String " << ((mode == CipherMode::ENCRYPT) ? "PlainText" : "CipherText") << endl;
				cout << "\t2. " << modeStr << " Hexidecimal " << ((mode == CipherMode::ENCRYPT) ? "PlainText" : "CipherText") << endl;
				cout << "\t3. Quit" << endl;
				cout << "Choice: ";
				
				getline(cin, userInput);
				switch (stoi(userInput, nullptr))
				{
				case 1:
					inputText = encryptDecryptInputTextPrep(mode, TextMode::STRING);
					break;
				case 2:
					inputText = encryptDecryptInputTextPrep(mode, TextMode::HEX);
					break;
				case 3:
					cout << "Goodbye!" << endl;
					return;
					break;
				default:
					isPairInitialized = false;
					throw wrongChoiceEx;
					break;
				}

				cout << endl;

				cout << "Please choose an option: " << endl;
				cout << "\t1. Use String Key"  << endl;
				cout << "\t2. Use Hexadecimal Key" << endl;
				cout << "\t3. Quit" << endl;
				cout << "Choice: ";

				getline(cin, userInput);
				switch (stoi(userInput, nullptr))
				{
				case 1:
					inputKey = keyTextPrep(TextMode::STRING);
					isPairInitialized = true;
					break;
				case 2:
					inputKey = keyTextPrep(TextMode::HEX);
					isPairInitialized = true;
					break;
				case 3:
					cout << "Goodbye!" << endl;
					return;
					break;
				default:
					isPairInitialized = false;
					throw wrongChoiceEx;
					break;
				}
			}

			cout << endl;

			if (!isSwapRoundInitialized)
			{
				cout << "Swap Last Round? (y/n) ";
				getline(cin, userInput);

				switch (userInput[0])
				{
				case 'y':
				case 'Y':
					swapLastRound = true;
					break;
				case 'n':
				case 'N':
					swapLastRound = false;
					break;
				default:
					isSwapRoundInitialized = false;
					throw wrongChoiceEx;
					break;
				}
			}

			if (mode == CipherMode::ENCRYPT)
				encrypt(inputText, inputKey, swapLastRound);
			else if (mode == CipherMode::DECRYPT)
				decrypt(inputText, inputKey, swapLastRound);

			cout << endl;

			mode = CipherMode::NONE;
			isPairInitialized = false;
			isSwapRoundInitialized = false;
		}
		catch (WrongMenuChoiceException e)
		{
			cout << e.what() << endl;
		}
		catch (exception e)
		{
			cout << e.what() << endl << endl;
			mode = CipherMode::NONE;
			isPairInitialized = false;
			isSwapRoundInitialized = false;
		}

		char full[_MAX_PATH];
		_fullpath(full, "debug.txt", _MAX_PATH);
		cout << "Debug Details Found At: " << full << endl << endl;

		outputStream << endl << endl << endl << endl;
		outputStream.flush();
		outputStream.close();

	} while (true);
}

static string encryptDecryptInputTextPrep(CipherMode cipherMode, TextMode textMode)
{
	string textModeStr = (textMode == TextMode::STRING) ? "String" : "Hexadecimal";
	string inputTextTypeStr = ((cipherMode == CipherMode::ENCRYPT) ? "Plaintext" : "Ciphertext");

	cout << endl;
	cout << "Please enter your " << inputTextTypeStr << " as a " << textModeStr << " Value: ";
	string inputText;
	getline(cin, inputText);

	switch (cipherMode)
	{
	case CipherMode::ENCRYPT:
		cout << "PlainText: " << inputText << endl;
		cout << "PlainText Hex and Binary Representations:" << endl;
		outputStream << "PlainText: " << inputText << std::endl;
		outputStream << "PlainText Hex and Binary Representations:" << std::endl;
		break;
	case CipherMode::DECRYPT:
		cout << "CipherText: " << inputText << endl;
		cout << "CipherText Hex and Binary Representations:" << endl;
		outputStream << "CipherText: " << inputText << endl;
		outputStream << "CipherText Hex and Binary Representations:" << endl;
		break;
	default:
		throw std::exception("An unexpected error has occured!");
		break;
	}

	string inputTextBin;
	vector<string> chunkedInputTextHex;
	vector<string> chunkedInputTextBin;

	switch (textMode)
	{
	case TextMode::STRING:
		inputTextBin = convertStringToBin(inputText);
		break;
	case TextMode::HEX:
		removeSpaces(inputText);
		inputTextBin = convertHexToBin(inputText);

		chunkString(inputText, 2, chunkedInputTextHex);
		chunkString(inputTextBin, 8, chunkedInputTextBin);
		cout << "Hex: " << joinToString(chunkedInputTextHex, " ") << endl;
		cout << "Binary: " << joinToString(chunkedInputTextHex, " ") << endl;
		outputStream << "Hex: " << joinToString(chunkedInputTextHex, " ") << endl;
		outputStream << "Binary: " << joinToString(chunkedInputTextBin, " ") << endl;
		break;
	default:
		throw exception("An unexpected error has occured!");
		break;
	}

	cout << endl;
	outputStream << endl;

	return inputTextBin;
}

static string keyTextPrep(TextMode textMode)
{
	string textModeStr = (textMode == TextMode::STRING) ? "String" : "Hex";

	cout << "Please enter your key as a " << textModeStr << " Value: ";
	string initialKey;
	getline(cin, initialKey);

	cout << endl;
	outputStream << std::endl;

	cout << "Initial Key: " << initialKey << endl;
	cout << "Initial Key Hex and Binary Representations:" << endl;
	outputStream << "Initial Key: " << initialKey << endl;
	outputStream << "Initial Key Hex and Binary Representations:" << endl;

	string initialKeyBin;
	vector<string> chunkedInitialKeyHex;
	vector<string> chunkedInitialKeyBin;

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

		cout << "Hex: " << joinToString(chunkedInitialKeyHex, " ") << endl;
		cout << "Binary: " << joinToString(chunkedInitialKeyBin, " ") << endl;
		outputStream << "Hex: " << joinToString(chunkedInitialKeyHex, " ") << endl;
		outputStream << "Binary: " << joinToString(chunkedInitialKeyBin, " ") << endl;
		break;
	default:
		throw std::exception("An unexpected error has occured!");
		break;
	}

	cout << endl;
	outputStream << endl;

	return initialKeyBin;
}

void encrypt(string plainTextBin, string initialKeyBin, bool swapLastRound)
{
	string cipherTextHex = convertBinToHex(desPrep(CipherMode::ENCRYPT, plainTextBin, initialKeyBin, swapLastRound));
	string cipherTextStr = convertHexToString(cipherTextHex);

	vector<string> chunkedCipherTextHex;
	chunkString(cipherTextHex, 2, chunkedCipherTextHex);

	cout << "Final CipherText (Hex): " << joinToString(chunkedCipherTextHex, " ") << endl;
	cout << "Final CipherText (String): " << cipherTextStr << endl;

	outputStream << "Final CipherText (Hex): " << joinToString(chunkedCipherTextHex, " ") << endl;
	outputStream << "Final CipherText (String): " << cipherTextStr << endl;
}

void decrypt(string cipherTextBin, string initialKeyBin, bool swapLastRound)
{
	string plainTextHex = convertBinToHex(desPrep(CipherMode::DECRYPT, cipherTextBin, initialKeyBin, swapLastRound));
	string plainTextStr = convertHexToString(plainTextHex);

	vector<string> chunkedPlainTextHex;
	chunkString(plainTextHex, 2, chunkedPlainTextHex);

	cout << "Final PlainText (Hex): " << joinToString(chunkedPlainTextHex, " ") << endl;
	cout << "Final PlainText (String): " << plainTextStr << endl;

	outputStream << "Final PlainText (Hex): " << joinToString(chunkedPlainTextHex, " ") << endl;
	outputStream << "Final PlainText (String): " << plainTextStr << endl;
}

/*---------------------------------------------------------------------------------------------*/

/*----------------------------------DES Main Functions-------------------------------------*/

string desPrep(CipherMode mode, string inputText, string key, bool swapLastRound)
{
	string finalResult = "";
	string inputTextBin = inputText;
	vector<string> inputTextBinChunked;
	chunkString(inputTextBin, 64, inputTextBinChunked);
	for (int i = 0; i < inputTextBinChunked.size(); i++)
	{
		inputTextBinChunked[i] = padRightWithZeros(inputTextBinChunked[i], 64);
	}
	outputStream << ((mode == CipherMode::ENCRYPT) ? "PlainText" : "CipherText") << " Binary: " << joinToString(inputTextBinChunked, " | ") << endl;

	string keyBin = padRightWithZeros(key, 64);

	vector<string> roundKeys;
	generateRoundKeys(keyBin, mode, roundKeys);
	outputStream << endl;

	int blockNum = 1;
	for (string block : inputTextBinChunked)
	{
		outputStream << "For block " << blockNum << "..." << endl;
		finalResult += desBinEncryptDecrypt(mode, block, roundKeys, swapLastRound);
	}

	return finalResult;
}

string desBinEncryptDecrypt(CipherMode mode, string inputTextBin, vector<string> &roundKeys, bool swapLastRound)
{
	string inputTextPostInitPBox = applyPBox(inputTextBin, INITIAL_P_BOX);

	string tempRoundInputText = inputTextPostInitPBox;
	for (int round = 0; round < 16; round++)
	{
		outputStream << "Round " << (round + 1) << "..." << endl;

		if ((mode == CipherMode::ENCRYPT && round + 1 == 16) || (mode == CipherMode::DECRYPT && round + 1 == 1))
			tempRoundInputText = roundFunction(mode, tempRoundInputText, roundKeys[round], swapLastRound);
		else
			tempRoundInputText = roundFunction(mode, tempRoundInputText, roundKeys[round]);

		vector<string> chunkedTempRoundInputText;
		chunkString(convertBinToHex(tempRoundInputText), 8, chunkedTempRoundInputText);

		outputStream << ((mode == CipherMode::ENCRYPT) ? "CipherText" : "PlainText") << " after Round " << padLeftWithZeros(to_string(round + 1), 2) << ": ";
		outputStream << joinToString(chunkedTempRoundInputText, " ") << endl;

		outputStream << endl;
	}

	outputStream << endl;
	return applyPBox(tempRoundInputText, FINAL_P_BOX);
}

/*-----------------------------------------------------------------------------------------*/

/*----------------------------------Key Functions-------------------------------------*/

void generateRoundKeys(string key, CipherMode mode, vector<string> &roundKeys)
{
	string cipherKey = applyPBox(key, PARITY_BIT_DROP_P_BOX);

	string tempShiftedKey = cipherKey;
	for (int round = 1; round <= 16; round++)
	{
		string leftBlock = tempShiftedKey.substr(0, (tempShiftedKey.length() / 2));
		string rightBlock = tempShiftedKey.substr((tempShiftedKey.length() / 2));

		string leftShiftedBlock;
		string rightShiftedBlock;

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

		string compressedKey = applyPBox(tempShiftedKey, KEY_COMPRESSION_P_BOX);
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
		vector<string> chunkedRoundKey;
		chunkString(roundKeys[round], 6, chunkedRoundKey);

		outputStream << "Round " << padLeftWithZeros(to_string(round + 1), 2) << " Key: " << joinToString(chunkedRoundKey, " ") << endl;
	}

	return;
}

/*------------------------------------------------------------------------------------*/

/*--------------------------------Round Functions-------------------------------------*/

string roundFunction(CipherMode mode, string input, string key, bool swap)
{
	string leftInput = input.substr(0, (input.length() / 2));
	string rightInput = input.substr((input.length() / 2));

	outputStream << "Pre-Round Input: " << convertBinToHex(leftInput) + " " + convertBinToHex(rightInput) << endl;

	string leftOutput;
	string rightOutput;

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

string innerRoundFunction(string input, string key)
{
	string expandedInput = applyPBox(input, EXPANSION_P_BOX);
	outputStream << "Expansion Result: " << convertBinToHex(expandedInput) << endl;
	string inputWithAppliedKey = xorBinaryBlocks(expandedInput, key);
	outputStream << "Applied Key Result: " << convertBinToHex(inputWithAppliedKey);
	vector<string> chunkedInputWithAppliedKey;
	chunkString(inputWithAppliedKey, 6, chunkedInputWithAppliedKey);

	string postSBoxResults = "";

	int index = 0;
	for (string chunk : chunkedInputWithAppliedKey)
	{
		string sBoxResult = applySBox(chunk, S_BOX_GROUP[index]);

		outputStream << "S-Box " << (index + 1) << " input: " << chunk << endl;
		outputStream << "S-Box " << (index + 1) << " output: " << sBoxResult << endl;

		postSBoxResults += sBoxResult;
		index++;
	}

	return applyPBox(postSBoxResults, STRAIGHT_P_BOX);
}

/*------------------------------------------------------------------------------------*/

/*--------------------------------AUX Functions-------------------------------------*/

string xorBinaryBlocks(string block1, string block2)
{
	string block1In = block1;
	string block2In = block2;
	string result = "";

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

		result += to_string(block1Int ^ block2Int);
	}

	return result;
}

string applyPBox(string input, const vector<int> &pBox)
{
	string output = "";
	output = padLeftWithZeros(output, pBox.size());

	for (int i = 0; i < pBox.size(); i++)
	{
		output[i] = input[pBox[i] - 1];
	}

	return output;
}

string applySBox(string input, const vector<vector<int>> &sBox)
{
	int boxRow = stoi((input.substr(0, 1) + input[input.length() - 1]), nullptr, 2);
	int boxCol = stoi((input.substr(1, input.length() - 2)), nullptr, 2);

	return bitset<4>(sBox[boxRow][boxCol]).to_string();
}

string shiftBitsLeft(string input, int shiftBy)
{
	int modShiftVal = shiftBy % input.length();
	string shiftedString = "";

	shiftedString += input.substr(modShiftVal);

	for (int i = 0; i < modShiftVal; i++)
	{
		shiftedString += input[i];
	}

	return shiftedString;
}

/*----------------------------------------------------------------------------------*/

/*------------------------------------------------String Manipulation Functions------------------------------------------------*/
void chunkString(string s, int size, vector<string> &chunkedList)
{
	int startIndex = 0;
	int endIndex = size;

	while (endIndex <= s.length())
	{
		chunkedList.push_back(s.substr(startIndex, size));
		startIndex += size;
		endIndex += size;
	}

	if (endIndex > s.length())
	{
		int diff = endIndex - s.length();
		string subStr = s.substr(startIndex, (size - diff));
		if (subStr != "")
			chunkedList.push_back(subStr);
	}
}

char* reverseConstString(char const* str)
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
		swap(rev[i], rev[j]);

	// return pointer of reversed string 
	return rev;
}

string padLeftWithZeros(string s, int amount)
{
	stringstream ss;
	string paddedStr;

	ss << "" << setfill('0') << setw(amount) << s;
	getline(ss, paddedStr);

	return paddedStr;
}

string padRightWithZeros(string s, int amount)
{
	stringstream ss;
	string paddedStr;

	ss << s << setw(amount - s.length()) << setfill('0') << "";
	getline(ss, paddedStr);

	return paddedStr;
}

string joinToString(vector<string> &list, string delimiter)
{
	string finalStr;

	int count = 0;
	for (const string &listStr : list)
	{
		finalStr += listStr;

		count++;
		if (count != list.size())
			finalStr += delimiter;
	}

	return finalStr;
}

void removeSpaces(string &input)
{
	stringstream ss;
	string finalStr = "";

	for (char c : input)
	{
		if (!isspace(c))
			finalStr += c;
	}

	input = finalStr;
}
/*-----------------------------------------------------------------------------------------------------------------------------*/

/*------------------------------------------------Text Conversion Functions------------------------------------------------*/

string convertBin(int num, char *buildNum, int placeVal, bool initial)
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
		return string(buildNum);
	}

	return "";
}

string convertStringToHex(string input)
{
	string finalHexStr = "";

	for (char c : input)
	{
		stringstream ss;
		int charAsInt = (int)c % 256;
		
		if (charAsInt < 0)
			charAsInt = charAsInt + 256;

		ss << hex << charAsInt;
		finalHexStr += ss.str();
	}

	return finalHexStr;
}

string convertHexToBin(string input)
{
	string binStr = "";

	for (int i = 0; i < input.length(); i++)
	{
		string hex = input.substr(i, 1);
		string hexInBinary = padLeftWithZeros(convertBin(stoi(hex, nullptr, 16)), 4);

		binStr += hexInBinary;
	}

	return binStr;
}

string convertBinToHex(string input)
{
	stringstream ss;

	vector<string> chunkedBinList;
	chunkString(input, 4, chunkedBinList);
	
	for (string bin : chunkedBinList)
	{
		int inputBase10 = stoll(bin, nullptr, 2);
		ss << hex << inputBase10;
	}

	return ss.str();
}

string convertHexToString(string input)
{
	vector<string> chunkedHexList;
	string finalStr = "";

	chunkString(input, 2, chunkedHexList);

	for (string hex : chunkedHexList)
	{
		char convertedChar = (char)stoi(hex, nullptr, 16);
		finalStr += convertedChar;
	}

	return finalStr;
}

string convertStringToBin(string input)
{
	string stASCIIHex = convertStringToHex(input);
	string stASCIIBin = convertHexToBin(stASCIIHex);

	vector<string> stASCIIHexList;
	vector<string> stASCIIBinList;
	chunkString(stASCIIHex, 2, stASCIIHexList);
	chunkString(stASCIIBin, 8, stASCIIBinList);

	cout << "Hex: " << joinToString(stASCIIHexList, " ") << endl;
	cout << "Binary: " << joinToString(stASCIIBinList, " ") << endl;
	outputStream << "Hex: " << joinToString(stASCIIHexList, " ") << endl;
	outputStream << "Binary: " << joinToString(stASCIIBinList, " ") << endl;

	return stASCIIBin;
}
/*-------------------------------------------------------------------------------------------------------------------------*/