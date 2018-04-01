package encrypt;

import java.util.ArrayList;

public interface IEncryptor {
    String onCode(String incomingText, ArrayList<Character> keys);
    String onDecode(String incomingText, ArrayList<Character> keys);
}
