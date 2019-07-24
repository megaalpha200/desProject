function [cipherTextHexStr, cipherTextStr, dispStr] = encrypt( plainTextBin, initialKeyBin, swapLastRound )
%encrypt Formal gateway to the DES encryption process.
%   This function is the formal gateway to the DES encryption process. It
%   takes a 1xN binary char vector as the plaintext, a 1xM binary char
%   vector as the initial key, and a boolean value used to determine wether
%   or not to swap the last round. The function returns a 1xK chunked hex
%   char vector and a 1xL Base64 string char vector.
    dispStr = '';
    
    [cipherTextBin, dispStr] = desPrep(CipherMode.ENCRYPT, plainTextBin, initialKeyBin, swapLastRound);
    cipherTextHex = convertBinToHex(cipherTextBin);
    cipherTextStr = convertHexToBase64String(cipherTextHex);
    
    cipherTextHexStr = splitAndJoinVectorToString(cipherTextHex, 2, ' ');
    %dispStr = horzcat(dispStr, sprintf('%s\n', ['Final CipherText (Hex): ' cipherTextHexStr]));
    %dispStr = horzcat(dispStr, sprintf('%s\n', ['Final CipherText (Base64 String): ' cipherTextStr]));
end

