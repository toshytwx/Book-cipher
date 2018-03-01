import encrypt.IEncryptor;
import encrypt.TritemiusEncryptor;

import javax.swing.*;
import java.io.*;

public class Controller {
    private IEncryptor encryptor;

    public Controller(IEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String onCode(String incomingText, Integer key) {
        return encryptor.onCode(incomingText, key);
    }

    public String onDecode(String incomingText, Integer key) {
        return encryptor.onDecode(incomingText, key);
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

    public Integer verifyKeyValue(String input, String linearFunc, String unlinearFunc, String motto)
    {
        TritemiusEncryptor tritemiusEncryptor = (TritemiusEncryptor) encryptor;
        char A = linearFunc.charAt(0);
        char B = linearFunc.charAt(3);
        char C = linearFunc.charAt(6);
        boolean isLinear = (A != 'x') && (B != 'x');
        boolean isUnlinear = isLinear && (C != 'x');
        if (isLinear) {
            return tritemiusEncryptor.getKeyFromLinearFunc(input, A, B);
        } else if (isUnlinear) {
            return tritemiusEncryptor.getKeyFromUnlinearFunc(input, A, B, C);
        } else {
            return tritemiusEncryptor.getKeyFromMotto(input, motto);
        }
    }
}
