import encrypt.CaesarEncryptor;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MyForm extends JDialog {
    private JPanel contentPane;
    private JButton openFileButton;
    private JButton buttonSaveAs;
    private JTextArea textArea;
    private JButton code;
    private JButton decode;
    private JButton aboutButton;
    private JButton exitButton;
    private JSpinner keyValue;
    private JComboBox comboBox;
    private JFormattedTextField linearFunc;
    private JFormattedTextField unlinearFunc;
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
                CaesarEncryptor.power = ((String) comboBox.getSelectedItem()).equals("eng") ? 128 : 1103;
                String codedText = controller.onCode(textArea.getText(),(Integer) keyValue.getValue());
                textArea.setText(codedText);
            }
        });

        decode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String decodedText = controller.onDecode(textArea.getText(),(Integer) keyValue.getValue());
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

        keyValueTune();
        comboBoxTune();
    }

    private void keyValueTune() {
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 255, 1);
        keyValue.setModel(spinnerModel);
    }

    private void comboBoxTune() {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        defaultComboBoxModel.addElement("eng");
        defaultComboBoxModel.addElement("ukr");
        comboBox.setModel(defaultComboBoxModel);
    }

    private void onAbout() {
        JOptionPane.showMessageDialog(this, "Developed by: Antonkin Dmytro TM-51", "About", JOptionPane.PLAIN_MESSAGE);
    }

}
