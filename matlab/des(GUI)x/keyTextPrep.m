function [ initialKeyBin, dispStr ] = keyTextPrep( initialKey, textMode )
%keyTextPrep Prepares initial key for the DES
%encryption/decryption process by converting it into binary.
%   The function takes a 1xN string or hex char vector as the intial key,
%   and a text mode value. It prepares intial key for 
%   the DES encryption/decryption process. It returns the prepared intial
%   key as a 1xM binary char vector.

    dispStr = '';

    %dispStr = sprintf('\n%s\n', ['Initial Key: ' initialKey]);
    %dispStr = horzcat(dispStr, sprintf('%s\n', 'Initial Key Hex and Binary Representations:'));
    
    initialKeyBin = '';
    convertedKeyDispStr = '';
    switch textMode
        case TextMode.STRING
            [initialKeyBin, convertedKeyDispStr] = convertStringToBin(initialKey);
        case TextMode.HEX
            initialKey = initialKey(find(~isspace(initialKey)));
            initialKeyBin = convertHexToBin(initialKey);
            
            chunkedInitialKeyHex = splitAndJoinVectorToString(initialKey, 2, ' ');
            chunkedInitialKeyBin = splitAndJoinVectorToString(initialKeyBin, 8, ' ');
            convertedKeyDispStr = horzcat(convertedKeyDispStr, sprintf('%s\n', ['Hex: ' chunkedInitialKeyHex]));
            convertedKeyDispStr = horzcat(convertedKeyDispStr, sprintf('%s\n', ['Binary: ' chunkedInitialKeyBin]));
        otherwise
            error('An unexpected error has occured!');
    end
    
    %dispStr = horzcat(dispStr, convertedKeyDispStr);
end

