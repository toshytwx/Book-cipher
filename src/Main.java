import encrypt.BookEncryptor;

public class Main {
    public static void main(String[] args) {
        BookEncryptor encryptor = new BookEncryptor();
        Controller controller = new Controller(encryptor);
        MyForm form = new MyForm(controller);
        form.pack();
        form.setVisible(true);
        System.exit(0);
    }
}
