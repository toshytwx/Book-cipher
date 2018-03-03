package encrypt;

import java.util.ArrayList;

public interface IEncryptor {
    String onCode(String incomingText, ArrayList<Integer> keys);
    String onDecode(String incomingText, ArrayList<Integer> keys);
}
