import encrypt.TritemiusEncryptor;

public class Main {
    public static void main(String[] args) {
        TritemiusEncryptor encryptor = new TritemiusEncryptor();
        Controller controller = new Controller(encryptor);
        MyForm form = new MyForm(controller);
        form.pack();
        form.setVisible(true);
        System.exit(0);
    }
}
