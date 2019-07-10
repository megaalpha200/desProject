function [plainTextHexStr, plainTextStr, dispStr] = decrypt( cipherTextBin, initialKeyBin, swapLastRound )
%encrypt Formal gateway to the DES decryption process.
%   This function is the formal gateway to the DES decryption process. It
%   takes a 1xN binary char vector as the ciphertext, a 1xM binary char
%   vector as the initial key, and a boolean value used to determine wether
%   or not to swap the last round. The function returns a 1xK chunked hex
%   char vector and a 1xL string char vector.

    [plainTextBin, dispStr] = desPrep(CipherMode.DECRYPT, cipherTextBin, initialKeyBin, swapLastRound);
    plainTextHex = convertBinToHex(plainTextBin);
    plainTextStr = convertHexToString(plainTextHex);
    
    plainTextHexStr = splitAndJoinVectorToString(plainTextHex, 2, ' ');
    dispStr = horzcat(dispStr, sprintf('%s\n', ['Final PlainText (Hex): ' plainTextHexStr]));
    dispStr = horzcat(dispStr, sprintf('%s\n', ['Final PlainText (String): ' plainTextStr]));
end

