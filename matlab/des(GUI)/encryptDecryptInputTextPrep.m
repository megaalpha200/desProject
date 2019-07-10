function [ inputTextBin, dispStr ] = encryptDecryptInputTextPrep( inputText, cipherMode,  textMode)
%encryptDecryptInputTextPrep Prepares input text for the DES
%encryption/decryption process by converting it into binary.
%   The function takes a 1xN string or hex char vector as the input text,
%   a cipher mode value and a text mode value. It prepares input text for 
%   the DES encryption/decryption process. It returns the prepared input
%   text as a 1xM binary char vector.

    inputTextBin = '';    
    dispStr = '';
    
    convertedTextDispStr = '';
    if (cipherMode == CipherMode.ENCRYPT); inputTextType = 'PlainText'; else inputTextType = 'CipherText'; end;
    dispStr = horzcat(dispStr, sprintf('%s\n', [inputTextType ': ' inputText]));
    dispStr = horzcat(dispStr, sprintf('%s\n', [inputTextType ' Hex and Binary Representations:']));
        
    if cipherMode == CipherMode.ENCRYPT && textMode == TextMode.STRING
        [inputTextBin, convertedTextDispStr] = convertStringToBin(inputText);
    elseif cipherMode == CipherMode.DECRYPT && textMode == TextMode.STRING
        [inputTextBin, convertedTextDispStr] = convertBase64StringToBin(inputText);
    elseif textMode == TextMode.HEX
            inputText = inputText(find(~isspace(inputText)));
            inputTextBin = convertHexToBin(inputText);
            
            chunkedInputTextHex = splitAndJoinVectorToString(inputText, 2, ' ');
            chunkedInputTextBin = splitAndJoinVectorToString(inputTextBin, 8, ' ');
            convertedTextDispStr = horzcat(convertedTextDispStr, sprintf('%s\n', ['Hex: ' chunkedInputTextHex]));
            convertedTextDispStr = horzcat(convertedTextDispStr, sprintf('%s\n', ['Binary: ' chunkedInputTextBin]));
    else
        error('An unexpected error has occured!');
    end
    
    dispStr = horzcat(dispStr, convertedTextDispStr);
end

