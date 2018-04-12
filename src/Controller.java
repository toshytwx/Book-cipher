import encrypt.IEncryptor;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Controller {
    private IEncryptor encryptor;

    public Controller(IEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String onCode(String incomingText, char[][] keys) {
        return encryptor.onEncrypt(incomingText, keys);
    }

    public String onDecode(String incomingText, char[][] keys) {
        return encryptor.onDecrypt(incomingText, keys);
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

    public char[][] verifyKeyValue(String text, File selectedFile) {
        char[] poemTextLowerCase = text.toLowerCase().toCharArray();
        char[][] result = null;
        try {
            int rowsCount = getLinesCount(selectedFile);
            int columnsCount = ((poemTextLowerCase.length % rowsCount) != 0) ? poemTextLowerCase.length / rowsCount + 1 : poemTextLowerCase.length / rowsCount;
            int letter = 0;
            result = new char[rowsCount][columnsCount];
            for (int row = 0; row < rowsCount; row++) {
                for (int column = 0; column < columnsCount; column++) {
                    result[row][column] = letter >= poemTextLowerCase.length ? ' ' : poemTextLowerCase[letter];
                    letter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int getLinesCount(File file) throws IOException
    {
        try (Stream<String> lines = Files.lines(file.toPath())) {
            return (int) lines.count();
        }
    }
}
