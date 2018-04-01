package encrypt;

import java.util.ArrayList;
import java.util.Arrays;

public class GammaEncryptor implements IEncryptor {
    public static int power = -1;

    @Override
    public String onCode(String incomingText, ArrayList<Character> keys) {
        StringBuilder sb = new StringBuilder();
        if (incomingText != null || !incomingText.equals("") || keys != null) {
            char[] incomingChars = incomingText.toCharArray();
            for (int i = 0; i < incomingChars.length; i++) {
                int index = Dictionary.CURRENTALPHABET.indexOf(incomingChars[i]) + Dictionary.CURRENTALPHABET.indexOf(keys.get(i)) % power;
                sb.append(Dictionary.CURRENTALPHABET.get(index));
            }
        }
        return sb.toString();
    }

    @Override
    public String onDecode(String incomingText, ArrayList<Character> keys) {
        StringBuilder sb = new StringBuilder();
        if (incomingText != null || !incomingText.equals("") || keys != null) {
            char[] incomingChars = incomingText.toCharArray();
            for (int i = 0; i < incomingChars.length; i++) {
                int index = (Dictionary.CURRENTALPHABET.indexOf(incomingChars[i]) + power - Dictionary.CURRENTALPHABET.indexOf(keys.get(i))) % power;
                sb.append(Dictionary.CURRENTALPHABET.get(index));
            }
        }
        return sb.toString();
    }

    public ArrayList<Character> getKeyFromMotto(String input, String motto) {
        ArrayList<Character> result = new ArrayList<>();
        char[] inputAsArray = input.toCharArray();
        char[] mottoAsArray = motto.toCharArray();
        for (int i = 0; i < inputAsArray.length; i++) {
            char key = '?';
            if (inputAsArray.length <= mottoAsArray.length) {
                key = mottoAsArray[i];
            } else if (inputAsArray.length > mottoAsArray.length) {
                int count = inputAsArray.length / mottoAsArray.length + 1;
                int newMottoAsArrayLength = mottoAsArray.length * count;
                char[] newMottoAsArray = new char[newMottoAsArrayLength];
                System.arraycopy(mottoAsArray, 0, newMottoAsArray, 0, mottoAsArray.length);
                for (int j = 1; j < count; j++) {
                    System.arraycopy(newMottoAsArray, 0, newMottoAsArray, mottoAsArray.length, mottoAsArray.length * j);
                }
                key = newMottoAsArray[i];
            }
            result.add(key);
        }
        return result;
    }
}
