import encrypt.Dictionary;
import encrypt.GammaEncryptor;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MyForm extends JDialog {
    private JPanel contentPane;
    private JButton openFileButton;
    private JButton buttonSaveAs;
    private JTextArea textArea;
    private JButton code;
    private JButton decode;
    private JButton aboutButton;
    private JButton exitButton;
    private JComboBox languageSelector;
    private JTextField motto;
    private JFileChooser jFileChooser;

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
                if (((String) languageSelector.getSelectedItem()).equals("eng")) {
                    GammaEncryptor.power = Dictionary.ENGALPHABET.size();
                    Dictionary.CURRENTALPHABET = Dictionary.ENGALPHABET;
                } else {
                    GammaEncryptor.power = Dictionary.UKRALPHABET.size();
                    Dictionary.CURRENTALPHABET = Dictionary.UKRALPHABET;
                }
                ArrayList<Character> keys = controller.verifyKeyValue(textArea.getText(), motto.getText());
                String codedText = controller.onCode(textArea.getText(), keys);
                textArea.setText(codedText);
            }
        });

        decode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Character> keys = controller.verifyKeyValue(textArea.getText(), motto.getText());
                String decodedText = controller.onDecode(textArea.getText(), keys);
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

        motto.setEditable(true);
        languageSelectorTune();
    }

    private void languageSelectorTune() {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        defaultComboBoxModel.addElement("eng");
        defaultComboBoxModel.addElement("ukr");
        languageSelector.setModel(defaultComboBoxModel);
    }

    private void onAbout() {
        JOptionPane.showMessageDialog(this, "Developed by: Antonkin Dmytro TM-51", "About", JOptionPane.PLAIN_MESSAGE);
    }

}
