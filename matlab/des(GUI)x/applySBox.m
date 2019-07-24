function output = applySBox( input, sBox )
%applySBox Takes an input and applies a S-Box to it
%   Takes a 1x6 binary char vector and applies a 4x16 double vector (S-Box)
%   to the input. The result is a permutated 1x4 binary char vector.
   
    boxRow = bin2dec([input(1) input(end)]) + 1;
    boxCol = bin2dec(input(2:end-1)) + 1;
    
    output = padString(dec2bin(sBox(boxRow, boxCol)), '0', 4, 0);
end

