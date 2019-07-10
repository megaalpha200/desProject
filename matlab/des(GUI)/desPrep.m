function [ finalOutputText, dispStr ] = desPrep( mode, inputText, key, swapLastRound )
%desPrep Prepares the input text for the DES encrypting/decrypting
%   This function takes a 1xN binary char vector as the input text, a 1xM
%   binary char vector for the initial key, and a boolean value used to
%   determine whether the last round should be swapped or not. The function
%   takes the input text and breaks it up into 1x64 binary char vectors,
%   where each will then be passed through the entire DES
%   encryption/decryption process via the desBinEncryptDecrypt() function.
%   It also generates the keys for each round using the initial key.
%   The function returns a 1xK binary char vector as the final output text.

    finalOutputText = '';
    [~, inputTextCSize] = size(inputText);
    inputTextMaxSize = ceil(inputTextCSize / 64) * 64;
    paddedInputText = padString(inputText, '0', inputTextMaxSize, 1);
    inputTextBinChunked = splitStringToRowVector(paddedInputText(1,:), 64);
    
    if (mode == CipherMode.ENCRYPT); inputTypeDisp = 'PlainText'; else inputTypeDisp = 'CipherText'; end;
    dispStr = sprintf('%s\n', [inputTypeDisp ' Binary: ' joinVectorToString(inputTextBinChunked, ' | ')]);
    
    paddedKeyBin = padString(key, '0', 64, 1);
    
    [roundKeys, roundKeysDispStr] = generateRoundKeys(paddedKeyBin, mode);
    dispStr = horzcat(dispStr, sprintf('%s\n', roundKeysDispStr));
    
    [inputTextBinChunkedRSize, ~] = size(inputTextBinChunked);
    for block = 1:inputTextBinChunkedRSize
        [desBlockRes, desBlockResDisp] = desBinEncryptDecrypt(mode, inputTextBinChunked(block, :), roundKeys, swapLastRound); 
        finalOutputText = horzcat(finalOutputText, desBlockRes);
        dispStr = horzcat(dispStr, sprintf('%s\n', ['For block ' num2str(block) '...']), desBlockResDisp);
    end
end

