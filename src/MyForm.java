import encrypt.TritemiusEncryptor;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
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
    private JFormattedTextField linearFunc;
    private JFormattedTextField unlinearFunc;
    private JTextField motto;
    private JComboBox keyTypeSelector;
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
                TritemiusEncryptor.power = ((String) languageSelector.getSelectedItem()).equals("eng") ? TritemiusEncryptor.ENG : TritemiusEncryptor.UKR;
                ArrayList<Integer> keys = controller.verifyKeyValue(textArea.getText(), linearFunc.getText(), unlinearFunc.getText(), motto.getText());
                String codedText = controller.onCode(textArea.getText(), keys);
                textArea.setText(codedText);
            }
        });

        decode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> keys = controller.verifyKeyValue(textArea.getText(), linearFunc.getText(), unlinearFunc.getText(), motto.getText());
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

        keyTypeSelector.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                setKeySelector();
            }
        });
        linearFunc.setEditable(false);
        unlinearFunc.setEditable(false);
        motto.setEditable(false);
        linearFuncTune();
        unlinearFuncTune();
        languageSelectorTune();
        keyTypeSelectorTune();
    }

    private void setKeySelector() {
        String selectorValue = (String) keyTypeSelector.getSelectedItem();
        switch (selectorValue)
        {
            case "Linear":
            {
                linearFunc.setEditable(true);
                unlinearFunc.setText("");
                motto.setText("");
                unlinearFunc.setEditable(false);
                motto.setEditable(false);
                break;
            }
            case "Unlinear":
            {
                unlinearFunc.setEditable(true);
                linearFunc.setText("");
                motto.setText("");
                linearFunc.setEditable(false);
                motto.setEditable(false);
                break;
            }
            case "Motto":
            {
                motto.setEditable(true);
                linearFunc.setText("");
                unlinearFunc.setText("");
                linearFunc.setEditable(false);
                unlinearFunc.setEditable(false);
                break;
            }
        }
    }

    private void keyTypeSelectorTune() {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        defaultComboBoxModel.addElement("Linear");
        defaultComboBoxModel.addElement("Unlinear");
        defaultComboBoxModel.addElement("Motto");
        keyTypeSelector.setModel(defaultComboBoxModel);
    }

    private void linearFuncTune() {
        try {
            MaskFormatter linearFormatter = new MaskFormatter("#p+#");
            linearFormatter.setPlaceholderCharacter('x');
            DefaultFormatterFactory defaultFormatterFactory = new DefaultFormatterFactory(linearFormatter);
            linearFunc.setFormatterFactory(defaultFormatterFactory);
            linearFunc.setColumns(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unlinearFuncTune() {
        try {
            MaskFormatter linearFormatter = new MaskFormatter("#p+#p+#");
            linearFormatter.setPlaceholderCharacter('x');
            DefaultFormatterFactory defaultFormatterFactory = new DefaultFormatterFactory(linearFormatter);
            unlinearFunc.setFormatterFactory(defaultFormatterFactory);
            unlinearFunc.setColumns(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
