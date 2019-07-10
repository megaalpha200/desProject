function [ finalOutputText, dispStr ] = desBinEncryptDecrypt( mode, inputTextBin, roundKeys, swapLastRound )
%desBinEncryptDecrypt Takes one 64 bit block and applies the entire DES
%process on it
%   The function takes a 1x64 binary char vector as the input text, a 16x48
%   binary char vector as the roundKeys, and a boolean value used to 
%   determine whether the last round should be swapped or not. Depending on
%   the cipher mode, the function applies the entire DES process to a 
%   single block of 64 bits accordingly. The function returns a 1x64
%   binary char vector as the output text.

    INITIAL_P_BOX = getInitialPBox();
    FINAL_P_BOX = getFinalPBox();

    dispStr='';
    
    inputTextPostInitPBox = applyPBox(inputTextBin(1,:), INITIAL_P_BOX);
    
    tempRoundInputText = inputTextPostInitPBox;
    for round = 1:16
        dispStr = horzcat(dispStr, sprintf('%s\n', ['Round ' num2str(round) '...']));
        
        roundFuncDispStr = '';
        if (mode == CipherMode.ENCRYPT && round == 16) || (mode == CipherMode.DECRYPT && round == 1)
            [tempRoundInputText, roundFuncDispStr] = roundFunction(mode, tempRoundInputText, roundKeys(round,:), swapLastRound);
        else
            [tempRoundInputText, roundFuncDispStr] = roundFunction(mode, tempRoundInputText, roundKeys(round,:), true);
        end
        
        dispStr = horzcat(dispStr, roundFuncDispStr);
        
        chunkedTempRoundInputText = splitAndJoinVectorToString(convertBinToHex(tempRoundInputText), 8, ' ');
        if (mode == CipherMode.ENCRYPT); outputTypeDisp = 'CipherText'; else outputTypeDisp = 'PlainText'; end
        dispStr = horzcat(dispStr, sprintf('%s\n', [outputTypeDisp ' after Round ' padString(num2str(round), '0', 2, 0) ': ' chunkedTempRoundInputText]));
    end
    
    dispStr = horzcat(dispStr, sprintf('\n'));
    finalOutputText = applyPBox(tempRoundInputText, FINAL_P_BOX);
end

