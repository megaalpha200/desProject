import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import desfunctions.DesMain;
import desfunctions.DesMainFunctions;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Date;

import static desfunctions.DesMainFunctions.CipherMode.DECRYPT;
import static desfunctions.DesMainFunctions.CipherMode.ENCRYPT;
import static desfunctions.DesMainFunctions.TextMode.HEX;
import static desfunctions.DesMainFunctions.TextMode.STRING;
import static desfunctions.DesMainFunctions.debugOutput;

public class DesForm {
    private JPanel desPanel;
    private JRadioButton inputTextStrRadioButton;
    private JRadioButton inputTextHexRadioButton;
    private JTextArea inputTextTextField;
    private JTextField keyTextField;
    private JRadioButton keyStrRadioButton;
    private JRadioButton keyHexRadioButton;
    private JButton executeButton;
    private JTextField outputTextHexTextField;
    private JTextArea outputTextStrTextField;
    private JRadioButton swapYesRadioButton;
    private JRadioButton swapNoRadioButton;
    private JRadioButton encryptRadioButton;
    private JRadioButton decryptRadioButton;
    private JLabel titleLabel;
    private JLabel inputTextLabel;
    private JLabel keyLabel;
    private JLabel outputTextHexLabel;
    private JLabel outputTextStrLabel;
    private JLabel swapLabel;
    private JTextArea debugTextArea;
    private JLabel debugLabel;
    private JButton outputTextHexCopyButton;
    private JButton outputTextStrCopyButton;

    public DesForm() {

        executeButton.addActionListener(e -> {
            try {
                String inputText = inputTextTextField.getText();
                String inputKey = keyTextField.getText();
                DesMainFunctions.TextMode textModeInput = (inputTextStrRadioButton.isSelected()) ? STRING : HEX;
                DesMainFunctions.TextMode textModeKey = (keyStrRadioButton.isSelected()) ? STRING : HEX;
                DesMainFunctions.CipherMode cipherMode = (encryptRadioButton.isSelected()) ? ENCRYPT : DECRYPT;
                boolean swapLastRound = swapYesRadioButton.isSelected();

                Date startTime = new Date();

                String inputTextBin = DesMain.encryptDecryptInputTextPrep(inputText, cipherMode, textModeInput);
                String keyTextBin = DesMain.keyTextPrep(inputKey, textModeKey);

                String completionMessage = "";
                Pair<String, String> outputTextHexStringPair = null;
                switch (cipherMode) {
                    case ENCRYPT:
                        outputTextHexStringPair = DesMain.encrypt(inputTextBin, keyTextBin, swapLastRound);
                        completionMessage = "The PlainText has been successfully Encrypted!";
                        break;
                    case DECRYPT:
                        outputTextHexStringPair = DesMain.decrypt(inputTextBin, keyTextBin, swapLastRound);
                        completionMessage = "The CipherText has been successfully Decrypted!";
                        break;
                    default:
                        throw new Exception("An unexpected error has occurred!");
                }

                if (outputTextHexStringPair == null || outputTextHexStringPair.getKey().equals(""))
                    throw new Exception("Please enter inputs!");

                Date endTime = new Date();
                double timeDiff = (endTime.getTime() - startTime.getTime()) + 0.0;
                debugOutput.append("\nElapsed Time: ").append(timeDiff).append(" milliseconds\n\n");

                String outputTextHex = outputTextHexStringPair.getKey();
                String outputTextStr = outputTextHexStringPair.getValue();
                outputTextHexTextField.setText(outputTextHex);
                outputTextStrTextField.setText(outputTextStr);

                String debugStr = debugOutput.toString();
                debugTextArea.setText(debugStr);
                debugOutput = new StringBuilder();

                JOptionPane.showMessageDialog(null, completionMessage, "Complete!", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        });
        outputTextHexCopyButton.addActionListener(e -> {
            String outputHexStr = outputTextHexTextField.getText();
            StringSelection stringSelection = new StringSelection(outputHexStr);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            JOptionPane.showMessageDialog(null, "Text copied!", "Copied!", JOptionPane.INFORMATION_MESSAGE);
        });
        outputTextStrCopyButton.addActionListener(e -> {
            String outputStr = outputTextStrTextField.getText();
            StringSelection stringSelection = new StringSelection(outputStr);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            JOptionPane.showMessageDialog(null, "Text copied!", "Copied!", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DES");
        frame.setContentPane(new DesForm().desPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        desPanel = new JPanel();
        desPanel.setLayout(new GridLayoutManager(11, 6, new Insets(0, 0, 0, 0), -1, -1));
        desPanel.setMaximumSize(new Dimension(800, 500));
        desPanel.setMinimumSize(new Dimension(800, 500));
        desPanel.setPreferredSize(new Dimension(800, 500));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$(null, -1, 16, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setText("DES Encryptor/Decryptor using Java");
        desPanel.add(titleLabel, new GridConstraints(0, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inputTextLabel = new JLabel();
        inputTextLabel.setText("Input:");
        desPanel.add(inputTextLabel, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(31, 46), null, 1, false));
        inputTextStrRadioButton = new JRadioButton();
        inputTextStrRadioButton.setSelected(true);
        inputTextStrRadioButton.setText("String");
        desPanel.add(inputTextStrRadioButton, new GridConstraints(1, 4, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(54, 46), null, 0, false));
        inputTextHexRadioButton = new JRadioButton();
        inputTextHexRadioButton.setText("Hexadecimal");
        desPanel.add(inputTextHexRadioButton, new GridConstraints(1, 5, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(91, 46), null, 0, false));
        keyLabel = new JLabel();
        keyLabel.setText("Key:");
        desPanel.add(keyLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        keyTextField = new JTextField();
        desPanel.add(keyTextField, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(350, -1), null, 0, false));
        keyStrRadioButton = new JRadioButton();
        keyStrRadioButton.setSelected(true);
        keyStrRadioButton.setText("String");
        desPanel.add(keyStrRadioButton, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        keyHexRadioButton = new JRadioButton();
        keyHexRadioButton.setText("Hexadecimal");
        desPanel.add(keyHexRadioButton, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        executeButton = new JButton();
        executeButton.setText("Execute");
        desPanel.add(executeButton, new GridConstraints(6, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputTextHexLabel = new JLabel();
        outputTextHexLabel.setText("Output Text (Hex):");
        desPanel.add(outputTextHexLabel, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        outputTextStrLabel = new JLabel();
        outputTextStrLabel.setText("Output Text (String):");
        desPanel.add(outputTextStrLabel, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        outputTextHexTextField = new JTextField();
        outputTextHexTextField.setBackground(new Color(-1));
        outputTextHexTextField.setEditable(false);
        outputTextHexTextField.setEnabled(true);
        desPanel.add(outputTextHexTextField, new GridConstraints(7, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        swapLabel = new JLabel();
        swapLabel.setText("Swap?");
        desPanel.add(swapLabel, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        debugLabel = new JLabel();
        debugLabel.setText("Debug:");
        desPanel.add(debugLabel, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        swapYesRadioButton = new JRadioButton();
        swapYesRadioButton.setText("Yes");
        desPanel.add(swapYesRadioButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        swapNoRadioButton = new JRadioButton();
        swapNoRadioButton.setSelected(true);
        swapNoRadioButton.setText("No");
        desPanel.add(swapNoRadioButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        encryptRadioButton = new JRadioButton();
        encryptRadioButton.setSelected(true);
        encryptRadioButton.setText("Encrypt");
        desPanel.add(encryptRadioButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        decryptRadioButton = new JRadioButton();
        decryptRadioButton.setText("Decrypt");
        desPanel.add(decryptRadioButton, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputTextHexCopyButton = new JButton();
        outputTextHexCopyButton.setText("Copy");
        desPanel.add(outputTextHexCopyButton, new GridConstraints(7, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputTextStrCopyButton = new JButton();
        outputTextStrCopyButton.setText("Copy");
        desPanel.add(outputTextStrCopyButton, new GridConstraints(8, 5, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        desPanel.add(scrollPane1, new GridConstraints(10, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 300), null, 0, false));
        debugTextArea = new JTextArea();
        debugTextArea.setEditable(false);
        scrollPane1.setViewportView(debugTextArea);
        final JScrollPane scrollPane2 = new JScrollPane();
        desPanel.add(scrollPane2, new GridConstraints(1, 1, 2, 3, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(350, 46), null, 0, false));
        inputTextTextField = new JTextArea();
        scrollPane2.setViewportView(inputTextTextField);
        final JScrollPane scrollPane3 = new JScrollPane();
        desPanel.add(scrollPane3, new GridConstraints(8, 1, 1, 4, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(350, 20), null, 0, false));
        outputTextStrTextField = new JTextArea();
        outputTextStrTextField.setBackground(new Color(-1));
        outputTextStrTextField.setEditable(false);
        scrollPane3.setViewportView(outputTextStrTextField);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(inputTextStrRadioButton);
        buttonGroup.add(inputTextHexRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(keyStrRadioButton);
        buttonGroup.add(keyHexRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(swapYesRadioButton);
        buttonGroup.add(swapNoRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(encryptRadioButton);
        buttonGroup.add(decryptRadioButton);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return desPanel;
    }

}
