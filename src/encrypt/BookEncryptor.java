package encrypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BookEncryptor implements IEncryptor {
    @Override
    public String onEncrypt(String incomingText, char[][] keys) {
        StringBuilder sb = new StringBuilder();
        int rows = keys.length;
        int columns = keys[0].length;
        int letter = 0;
        String numRow = "";
        String numCol = "";
        Random random = new Random();
        char[] chars = incomingText.toLowerCase().toCharArray();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (chars[letter] == keys[i][j]) {
                    if (String.valueOf(i).length() == 1 && i != 9) {
                        numRow = "0" + String.valueOf(i + 1);
                    } else {
                        numRow = String.valueOf(i + 1);
                    }
                    if (String.valueOf(j).length() == 1 && j != 9) {
                        numCol = "0" + String.valueOf(j + 1);
                    } else {
                        numCol = String.valueOf(j + 1);
                    }
                    sb.append(numRow).append("/").append(numCol).append(", ");
                    letter++;
                    if (letter >= chars.length) {
                        return sb.toString();
                    } else if (letter < chars.length && j == columns - 1 && i == rows - 1) {
                        letter++;
                    } else {
                        i = 0;
                        j = 0;
                    }
                    if (letter != chars.length - 1 && chars[letter] == chars[letter + 1] || chars[letter] == chars[letter - 1]) {
                        i = rows == 0 ? random.nextInt(rows + 1) : random.nextInt(rows);
                    }
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String onDecrypt(String incomingText, char[][] keys) {
        StringBuilder sb = new StringBuilder();
        char[] chars = incomingText.toLowerCase().toCharArray();
        int keyRow = -1;
        int keyColumn = -1;
        for (int i = 0; i < chars.length; i+=4) {
            keyRow = Integer.parseInt(String.valueOf(chars[i]) + String.valueOf(chars[i + 1]));
            keyColumn = Integer.parseInt(String.valueOf(chars[i + 2]) + String.valueOf(chars[i + 3]));
            sb.append(keys[keyRow - 1][keyColumn -1]);
        }
        return sb.toString();
    }

}
