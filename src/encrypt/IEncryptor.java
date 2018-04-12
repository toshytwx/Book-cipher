package encrypt;

import java.util.ArrayList;

public interface IEncryptor {
    String onEncrypt(String incomingText, char[][] keys);
    String onDecrypt(String incomingText, char[][] keys);
}
