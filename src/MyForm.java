import javax.swing.*;
import java.awt.event.*;

public class MyForm extends JDialog {
    private JPanel contentPane;
    private JButton openFileButton;
    private JButton buttonSaveAs;
    private JTextArea textArea;
    private JButton code;
    private JButton decode;
    private JButton aboutButton;
    private JButton exitButton;
    private JTextArea keyPoem;
    private JButton openPoemFile;
    private JFileChooser jFileChooser;
    private JFileChooser poemJFileChooser;

    public MyForm(Controller controller) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(openFileButton);

        openFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jFileChooser = new JFileChooser();
                String output = controller.onOpenFile(jFileChooser);
                textArea.setText(output);
            }
        });

        buttonSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jFileChooser = new JFileChooser();
                String input = textArea.getText();
                controller.onSaveFile(jFileChooser, input);
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        code.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char[][] keys = controller.verifyKeyValue(keyPoem.getText(), poemJFileChooser.getSelectedFile());
                String codedText = controller.onCode(textArea.getText(), keys);
                textArea.setText(codedText);
            }
        });

        decode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char[][] keys = controller.verifyKeyValue(keyPoem.getText(), poemJFileChooser.getSelectedFile());
                String textToDecode = textArea.getText().replaceAll("[,/ ]", "");
                String decodedText = controller.onDecode(textToDecode, keys);
                textArea.setText(decodedText);
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAbout();
            }
        });

        openPoemFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poemJFileChooser = new JFileChooser();
                String output = controller.onOpenFile(poemJFileChooser);
                keyPoem.setText(output);
            }
        });
    }

    private void onAbout() {
        JOptionPane.showMessageDialog(this, "Developed by: Antonkin Dmytro TM-51", "About", JOptionPane.PLAIN_MESSAGE);
    }

}
