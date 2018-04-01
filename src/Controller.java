import encrypt.IEncryptor;
import encrypt.GammaEncryptor;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Controller {
    private IEncryptor encryptor;

    public Controller(IEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String onCode(String incomingText, ArrayList<Character> keys) {
        return encryptor.onCode(incomingText, keys);
    }

    public String onDecode(String incomingText, ArrayList<Character> keys) {
        return encryptor.onDecode(incomingText, keys);
    }

    public String onOpenFile(JFileChooser jFileChooser) {
        StringBuilder sb = new StringBuilder();
        int res = jFileChooser.showDialog(null, "Open file");
        if (res == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                int c;
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public void onSaveFile(JFileChooser jFileChooser, String input) {
        int res = jFileChooser.showDialog(null, "Save file");
        if (res == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            jFileChooser.setSelectedFile(file);
            try (BufferedWriter writer = new BufferedWriter((new FileWriter(file)))) {
                writer.write(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Character> verifyKeyValue(String input, String motto)
    {
        GammaEncryptor gammaEncryptor = (GammaEncryptor) encryptor;
        return gammaEncryptor.getKeyFromMotto(input, motto);

    }
}
