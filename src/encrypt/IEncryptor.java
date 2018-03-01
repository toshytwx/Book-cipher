package encrypt;

public interface IEncryptor {
    String onCode(String incomingText, Integer key);
    String onDecode(String incomingText, Integer key);
}
