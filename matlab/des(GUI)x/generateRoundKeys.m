function [ roundKeys, dispStr ] = generateRoundKeys( key, mode )
%generateRoundKeys Generates all necessary round keys based on the original
%key
%   Depending on the cipher mode, the function takes a 1x64 binary char
%   vector as the initial key. Then the function produces all the necessary
%   round keys for the entire DES encryption/decryption process. The
%   function returns a 16x48 binary char vector which represents all the
%   round keys.

    PARITY_BIT_DROP_P_BOX = getParityDropPBox();
    KEY_COMPRESSION_P_BOX = getKeyCompressionPBox();
    SHIFT_ONCE_ROUNDS = getShiftOnceRounds();
    
    cipherKey = applyPBox(key, PARITY_BIT_DROP_P_BOX);
    
    tempShiftedKey = cipherKey;
    for round = 1:16
        [~, tempShiftedKeyCSize] = size(tempShiftedKey);
        
        leftBlock = tempShiftedKey(1:(tempShiftedKeyCSize / 2));
        rightBlock = tempShiftedKey((tempShiftedKeyCSize / 2) + 1:end);
        
        leftShiftedBlock = '';
        rightShiftedBlock = '';
        
        if any(SHIFT_ONCE_ROUNDS == round)
            leftShiftedBlock = shiftBitsLeft(leftBlock, 1);
            rightShiftedBlock = shiftBitsLeft(rightBlock, 1);
        else
            leftShiftedBlock = shiftBitsLeft(leftBlock, 2);
            rightShiftedBlock = shiftBitsLeft(rightBlock, 2);
        end
        
        tempShiftedKey = horzcat(leftShiftedBlock, rightShiftedBlock);
        
        compressedKey = applyPBox(tempShiftedKey, KEY_COMPRESSION_P_BOX);
        roundKeys(round,:) = compressedKey;
    end
    
    if (mode == CipherMode.DECRYPT)
        roundKeys = flip(roundKeys);
    end
    
    dispStr = '';
    for round = 1:16
        chunkedRoundKey = splitAndJoinVectorToString(roundKeys(round, :), 6, ' ');
        %dispStr = horzcat(dispStr, sprintf('%s\n', ['Round ' padString(num2str(round), '0', 2, 0) ' Key: ' chunkedRoundKey]));
    end
end

