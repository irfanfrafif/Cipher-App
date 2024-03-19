import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingUtilities;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppUI extends JFrame {
    private JTextArea textField1;
    private JTextArea textField2;
    private JButton encryptButton;
    private JButton decryptButton;

    public AppUI() {
        getContentPane().setLayout(new GridBagLayout());
        createView();
        setTitle("Cipher App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void createView() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        getContentPane().add(contentPanel);
        
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        textField1 = new JTextArea(10, 15);
        textField1.setLineWrap(true);
        textField1.setAlignmentX(LEFT_ALIGNMENT);
        JLabel label = new JLabel("Text to encrypt/decrypt:");
        inputPanel.add(label);
        inputPanel.add(textField1);
        formPanel.add(inputPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");

        // Adjust button sizes and alignment
        encryptButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        decryptButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        buttonPanel.add(encryptButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(decryptButton);
        formPanel.add(buttonPanel);


        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        textField2 = new JTextArea(10, 15);
        textField2.setLineWrap(true);
        textField2.setEditable(false);
        textField2.setAlignmentX(LEFT_ALIGNMENT);
        JLabel label2 = new JLabel("Result:");
        outputPanel.add(label2);
        outputPanel.add(textField2);
        formPanel.add(outputPanel);

        contentPanel.add(formPanel);


        JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        getContentPane().add(keyPanel);
        JPanel keyFormPanel = new JPanel();
        keyFormPanel.setLayout(new BoxLayout(keyFormPanel, BoxLayout.X_AXIS));
        JLabel keyLabel = new JLabel("Key:");
        keyFormPanel.add(keyLabel);

        JPanel inputKeyPanel = new JPanel(new FlowLayout());
        JTextField keyField = new JTextField(10);
        JRadioButton hexButton = new JRadioButton("Hex");
        JRadioButton asciiButton = new JRadioButton("ASCII");
        asciiButton.setSelected(true);

        ButtonGroup buttonGroup = new ButtonGroup(); 
        buttonGroup.add(hexButton);
        buttonGroup.add(asciiButton);
        
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField1.getText();
                String key = keyField.getText();
                boolean isHex = hexButton.isSelected();
                String result = Cipher.cipherText(text, key, isHex);
                textField2.setText(result);
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField1.getText();
                String key = keyField.getText();
                boolean isHex = hexButton.isSelected();
                String result = Cipher.decipherText(text, key, isHex);
                textField2.setText(result);
            }
        
        });
        
        inputKeyPanel.add(keyField);
        inputKeyPanel.add(hexButton);
        inputKeyPanel.add(asciiButton);
        keyFormPanel.add(inputKeyPanel);
        keyPanel.add(keyFormPanel);

        contentPanel.add(keyPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new AppUI().setVisible(true);
            }
        });
    }
}
