function [ result, dispStr ] = innerRoundFunction( input, key )
%innerRoundFunction DES inner round function
%   Takes a 1x32 binary char vector and returns a 1x32 binary char vector
%   after passing the input through an expansion P-Box, then the key is 
%   applied (XORed) and every 6 bits goes through a corresponding S-Box, 
%   until finally going through a straight P-Box.

    STRAIGHT_P_BOX = getStraightPBox();
    EXPANSION_P_BOX = getExpansionPBox();
    S_BOX_GROUP = getSBoxGroup();

    expandedInput = applyPBox(input, EXPANSION_P_BOX);
    dispStr = sprintf('%s\n', ['Expansion Result: ' convertBinToHex(expandedInput)]);
    inputWithAppliedKey = xorBinaryBlocks(expandedInput, key);
    dispStr = horzcat(dispStr, sprintf('%s\n', ['Applied Key Result: ' convertBinToHex(inputWithAppliedKey)]));
    
    chunkedInputWithAppliedKey = splitStringToRowVector(inputWithAppliedKey, 6);
    [chunkedInputWithAppliedKeyRSize, ~] = size(chunkedInputWithAppliedKey);
    
    postSBoxResults = '';
    for i = 1:chunkedInputWithAppliedKeyRSize
        sBoxResult = applySBox(chunkedInputWithAppliedKey(i,:), S_BOX_GROUP(:,:,i));
        
        dispStr = horzcat(dispStr, sprintf('%s\n', ['S-Box : ' num2str(i) ' input: ' chunkedInputWithAppliedKey(i,:)]));
        dispStr = horzcat(dispStr, sprintf('%s\n', ['S-Box : ' num2str(i) ' output: ' sBoxResult]));
        
        postSBoxResults = horzcat(postSBoxResults, sBoxResult);
    end
    
    result = applyPBox(postSBoxResults, STRAIGHT_P_BOX);
end

