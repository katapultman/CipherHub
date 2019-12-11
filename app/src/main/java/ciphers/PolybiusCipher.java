package ciphers;

import android.util.Log;

public class PolybiusCipher extends Ciphers {

    private static Character[][] PolybiusSquare;

    public PolybiusCipher(Character[][] PolybiusSquare) {
        PolybiusCipher.PolybiusSquare = PolybiusSquare;
    }

    private boolean isCharacterInSquare(Character character) {
        for(int i = 0; i < PolybiusSquare.length; i++) {
            for(int j = 0; j < PolybiusSquare[0].length; j++) {
                if(PolybiusSquare[i][j] == character) return true;
            }
        }
        return false;
    }

    public String PolybiusEncode(String base) {
        encodedText = "";

        //TO-DO: Put these variables in superclass Ciphers
        //add functionality for user to choose lowercase or uppercase -> done

        for(int i = 0; i < base.length(); i++) {

            currentCharacter = base.charAt(i);

            if(!isCharacterInSquare((char) currentCharacter) && characterValidator.isInvalidCharacter(currentCharacter)) continue; // add as customization feature -> add invalid character or skip?
            if(characterValidator.isSpecialCharacter(currentCharacter)) encodedText += (char)currentCharacter;
            else {
                if(characterValidator.isLowercaseLetter(currentCharacter) && !isCharacterInSquare((char) currentCharacter)) currentCharacter = characterValidator.convertToUppercase(currentCharacter);
                else if(characterValidator.isCapitalLetter(currentCharacter) && !isCharacterInSquare((char) currentCharacter)) currentCharacter = characterValidator.convertToLowercase(currentCharacter);
                encodedText += (char) characterValidator.convertToASCIINumber(characterValidator.findElementRow(PolybiusSquare, (char)currentCharacter));
                encodedText += (char) characterValidator.convertToASCIINumber(characterValidator.findElementColumn(PolybiusSquare, (char)currentCharacter));
            }
        }

        return encodedText;
    }

    public String PolybiusDecode(String base, String inputText) {
        decodedText = "";
        Log.d("Input; ", inputText);
        //evaluate exception in activity file -> if(base.length() % 2 != 0) -> done

        for(int i = 0, counter = 0; i + 1 < base.length(); i += 2, counter++) {
            currentCharacter = base.charAt(i);
            //fix seg fault when trying to decrement i for ignoring space -> done

            if(characterValidator.isSpecialCharacter(currentCharacter)) {
                decodedText += (char) currentCharacter;
                i--;
                continue;
            }

            int nextCharacter = base.charAt(i + 1);

            if(characterValidator.isSpecialCharacter(nextCharacter)) {
                decodedText += (char) nextCharacter;
                continue;
            }

            if(!characterValidator.isNumber(currentCharacter) || !characterValidator.isNumber(nextCharacter) ||
                    characterValidator.isInvalidCharacter(currentCharacter) || characterValidator.isInvalidCharacter(nextCharacter)) continue;


            int x = characterValidator.convertToNumber(currentCharacter), y = characterValidator.convertToNumber(nextCharacter);
            if(x > 6 || y > 6) continue;
            //Fix uppercase and lowercase bug -> done
            if(characterValidator.isCapitalLetter(inputText.charAt(counter)) ||
                    characterValidator.isNumber(PolybiusSquare[x - 1][y - 1])) decodedText += PolybiusSquare[x - 1][y - 1];
            else decodedText += (char)characterValidator.convertToLowercase(PolybiusSquare[x - 1][y - 1]);
        }

        return decodedText;
    }

}
