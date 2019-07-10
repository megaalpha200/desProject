function [ result, dispStr ] = roundFunction( mode, input, key, swap )
%roundFunction DES overall round function (encryption/decryption)
%   Depending on the cipher mode, the function takes a 1x64 binary char
%   vector as input and a 1x48 binary char vector as a key and performs the
%   appropriate actions on the input. It also takes a boolean value to
%   determine whether the leftmost 32 bits should be swaped with the
%   rightmost 32 bits of the output result before returning said result.

    [~, inputCSize] = size(input);
    leftInput = input(1:(inputCSize / 2));
    rightInput = input((inputCSize / 2) + 1:end);
    
    dispStr = sprintf('%s\n', ['Pre-Round Input: ' convertBinToHex(leftInput) ' ' convertBinToHex(rightInput)]);
    
    leftOutput = '';
    rightOutput = '';
    innerRoundDispStr = '';
    
    if mode == CipherMode.ENCRYPT && swap
        leftOutput = rightInput;
        [innerRoundRes, innerRoundDispStr] = innerRoundFunction(rightInput, key(1,:));
        rightOutput = xorBinaryBlocks(leftInput, innerRoundRes);
    elseif mode == CipherMode.DECRYPT && swap
        [innerRoundRes, innerRoundDispStr] = innerRoundFunction(leftInput, key(1,:));
        leftOutput = xorBinaryBlocks(rightInput, innerRoundRes);
        rightOutput = leftInput;
    else
        [innerRoundRes, innerRoundDispStr] = innerRoundFunction(rightInput, key(1,:));
        leftOutput = xorBinaryBlocks(leftInput, innerRoundRes);
        rightOutput = rightInput;
    end
    
    dispStr = horzcat(dispStr, innerRoundDispStr);
    
    result = horzcat(leftOutput, rightOutput);
end

