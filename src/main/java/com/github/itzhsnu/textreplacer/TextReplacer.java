package com.github.itzhsnu.textreplacer;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextReplacer extends JFrame implements ActionListener {

    //Field and Buttons
    private final JTextArea base = new JTextArea();
    private final JTextArea changed = new JTextArea();
    private final JTextArea changeBase = new JTextArea();
    private final JTextArea changeOut = new JTextArea();
    private final JCheckBox check_Up_and_Low = new JCheckBox("Check Uppercase and Lowercase");

    public TextReplacer() {
        setTitle("Text Replacer 1.0.1");
        setBounds(100, 100, 750, 490);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel baseL = new JLabel("Base Text");
        baseL.setBounds(10, 10, 80, 20);

        JScrollPane scrollBase = new JScrollPane(base);
        scrollBase.setBounds(10, 35, 480, 150);

        JLabel changedL = new JLabel("Changed Text");
        changedL.setBounds(10, 200, 80, 20);

        JScrollPane scrollChanged = new JScrollPane(changed);
        scrollChanged.setBounds(10, 225, 480, 150);

        JLabel changeBaseL = new JLabel("Change Base");
        changeBaseL.setBounds(520, 10, 80, 20);

        JScrollPane scrollChangeBase = new JScrollPane(changeBase);
        scrollChangeBase.setBounds(520, 35, 200, 150);

        JLabel changeOutL = new JLabel("Change Out");
        changeOutL.setBounds(520, 200, 80, 20);

        JScrollPane scrollChangeOut = new JScrollPane(changeOut);
        scrollChangeOut.setBounds(520, 225, 200, 150);

        JButton change = new JButton("Change");
        change.setBounds(10, 395, 80, 40);
        change.addActionListener(this);

        check_Up_and_Low.setBounds(120, 405, 220, 20);

        getContentPane().add(baseL);
        getContentPane().add(scrollBase);
        getContentPane().add(changeBaseL);
        getContentPane().add(scrollChangeBase);
        getContentPane().add(changedL);
        getContentPane().add(scrollChanged);
        getContentPane().add(changeOutL);
        getContentPane().add(scrollChangeOut);
        getContentPane().add(change);
        getContentPane().add(check_Up_and_Low);
    }

    public static void main(String[] args) {
        new TextReplacer().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; changeBase.getLineCount() > i; ++i) {
            if (changeOut.getLineCount() >= i) {
                Document docIn = changeBase.getDocument();
                Element elementIn = docIn.getDefaultRootElement().getElement(i);

                Document docOut = changeOut.getDocument();
                Element elementOut = docOut.getDefaultRootElement().getElement(i);

                try {
                String in = docIn.getText(elementIn.getStartOffset(), elementIn.getEndOffset() - elementIn.getStartOffset() - 1);
                String out = docOut.getText(elementOut.getStartOffset(), elementOut.getEndOffset() - elementOut.getStartOffset() - 1);

                if (!in.isEmpty()) {
                    String baseText = base.getText();
                    if (i >= 1) baseText = changed.getText();
                    String change = baseText.replaceAll(in, out);
                    if (!check_Up_and_Low.isSelected()) change = baseText.replaceAll("(?i)" + in, out);
                    changed.setText(change);
                }

                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
