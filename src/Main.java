import encrypt.Dictionary;
import encrypt.GammaEncryptor;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GammaEncryptor encryptor = new GammaEncryptor();
        Controller controller = new Controller(encryptor);
        MyForm form = new MyForm(controller);
        form.pack();
        form.setVisible(true);
        System.exit(0);
    }
}
