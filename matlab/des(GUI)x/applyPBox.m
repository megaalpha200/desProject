function output = applyPBox( input, pBox )
%applyPBox Takes an input and applies a P-Box to it
%   Takes a 1xN binary char vector and applies a 1xM double vector (P-Box)
%   to the input. The result is a permutated 1xM binary char vector.

    output = '';
    [~, pBoxCSize] = size(pBox);
    
    output = padString(output, '0', pBoxCSize, 0);
    for i = 1:pBoxCSize
        output(i) = input(pBox(i));
    end
end

