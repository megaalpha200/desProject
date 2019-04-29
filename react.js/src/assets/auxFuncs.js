export const string_chop = (str, size) => {
    if (str == null) return [];
    str = String(str);
    size = ~~size;
    return size > 0 ? str.match(new RegExp('.{1,' + size + '}', 'g')) : [str];
  }

  export const shiftBitsLeft = (input, shiftBy) => {
    const modShiftVal = shiftBy % (input.length);
    let shiftedString = "";
  
    shiftedString = shiftedString + input.substring(modShiftVal);
  
    for(let i = 0; i < modShiftVal; i++) {
        shiftedString = shiftedString + input[i];
    }
  
    return shiftedString;
  }