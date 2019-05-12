package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Classe pop up de saisie texte swing
 */
public class TestText {

    JFrame frame;
    Vector<Integer> vec;
    JTextField text;
    JLabel lab;
    JButton button;

    String message;

    /**
     *
     */
    public TestText() {
        frame = new JFrame("Message Code-Fort");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(contentPane());
        frame.getRootPane().setDefaultButton(button);
        frame.setVisible(true);
    }

    /**
     * mise en place zone texte et bouton de confirmation de saisie
     * @return
     */
    private Container contentPane() {
        JPanel panel = new JPanel(new BorderLayout());
        text = new JTextField();
        lab = new JLabel("Entrer un message: ");
        button = new JButton("Entrer");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message = text.getText();
                frame.setVisible(false);

                //indiquer si il y a une image
                //[...]

                //Creation du Co 2D selon le texte saisie
                CodeFort qr = new CodeFort(text.getText());
            }
        });
        panel.add(text);
        panel.add(lab, BorderLayout.NORTH);
        panel.add(button, BorderLayout.SOUTH);
        return panel;
    }

   /* public static void main(String[] args)
    {
        String lookAndFeelName = UIManager.getSystemLookAndFeelClassName();

        try {

            UIManager.setLookAndFeel(lookAndFeelName);
        }
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}

        new TestText();
    }*/
}
