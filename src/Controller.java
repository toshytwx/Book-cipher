import encrypt.IEncryptor;
import encrypt.TritemiusEncryptor;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Controller {
    private IEncryptor encryptor;

    public Controller(IEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String onCode(String incomingText, ArrayList<Integer> keys) {
        return encryptor.onCode(incomingText, keys);
    }

    public String onDecode(String incomingText, ArrayList<Integer> keys) {
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

    public ArrayList<Integer> verifyKeyValue(String input, String linearFunc, String unlinearFunc, String motto)
    {
        TritemiusEncryptor tritemiusEncryptor = (TritemiusEncryptor) encryptor;
        char A = linearFunc.charAt(0);
        char B = linearFunc.charAt(3);
        char C = unlinearFunc.charAt(6);
        boolean isLinear = (A != 'x') && (B != 'x');
        if (A == 'x' && B == 'x') {
            A = unlinearFunc.charAt(0);
            B = unlinearFunc.charAt(3);
        }
        boolean isUnlinear = (A != 'x') && (B != 'x') && (C != 'x');
        if (isLinear) {
            return tritemiusEncryptor.getKeyFromLinearFunc(input, A, B);
        } else if (isUnlinear) {
            return tritemiusEncryptor.getKeyFromUnlinearFunc(input, A, B, C);
        } else {
            return tritemiusEncryptor.getKeyFromMotto(input, motto);
        }
    }
}
