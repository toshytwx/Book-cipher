package encrypt;

import java.util.ArrayList;

public class TritemiusEncryptor implements IEncryptor {
    public static int power = -1;
    public static final int ENG = 128;
    public static final int UKR = 65355;

    @Override
    public String onCode(String incomingText, ArrayList<Integer> keys) {
        StringBuilder sb = new StringBuilder();
        if (incomingText != null || !incomingText.equals("") || keys != null) {
            char[] incomingChars = incomingText.toCharArray();
            for (int i = 0; i < incomingChars.length; i++) {
                char c = (char) (((int) incomingChars[i] + keys.get(i)) % power);
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @Override
    public String onDecode(String incomingText, ArrayList<Integer> keys) {
        StringBuilder sb = new StringBuilder();
        if (incomingText != null || !incomingText.equals("") || keys != null) {
            char[] incomingChars = incomingText.toCharArray();
            for (int i = 0; i < incomingChars.length; i++) {
                char c = (char) (((int) incomingChars[i] + power - (keys.get(i) % power)) % power);
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public ArrayList<Integer> getKeyFromLinearFunc(String input, char A, char B) {
        ArrayList<Integer> result = new ArrayList<>();
        char[] inputAsArray = input.toCharArray();
        for (int i = 0; i < inputAsArray.length; i++) {
            int key = (int) A * i + (int) B;
            result.add(key);
        }
        return result;
    }

    public ArrayList<Integer> getKeyFromUnlinearFunc(String input, char A, char B, char C) {
        ArrayList<Integer> result = new ArrayList<>();
        char[] inputAsArray = input.toCharArray();
        for (int i = 0; i < inputAsArray.length; i++) {
            int key = (int) A * i * i + (int) B * i + (int) C;
            result.add(key);
        }
        return result;
    }

    public ArrayList<Integer> getKeyFromMotto(String input, String motto) {
        ArrayList<Integer> result = new ArrayList<>();
        char[] inputAsArray = input.toCharArray();
        char[] mottoAsArray = motto.toCharArray();
        for (int i = 0; i < inputAsArray.length; i++) {
            int key = -1;
            if (inputAsArray.length <= mottoAsArray.length) {
                key = (int) mottoAsArray[i];
            } else if (inputAsArray.length > mottoAsArray.length) {
                int count = inputAsArray.length / mottoAsArray.length + 1;
                int newMottoAsArrayLength = mottoAsArray.length * count;
                char[] newMottoAsArray = new char[newMottoAsArrayLength];
                System.arraycopy(mottoAsArray, 0, newMottoAsArray, 0, mottoAsArray.length);
                for (int j = 1; j < count; j++) {
                    System.arraycopy(newMottoAsArray, 0, newMottoAsArray, mottoAsArray.length, mottoAsArray.length * j);
                }
                key = (int) newMottoAsArray[i];
            }
            result.add(key);
        }
        return result;
    }
}
