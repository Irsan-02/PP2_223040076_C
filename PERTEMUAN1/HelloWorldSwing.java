package PERTEMUAN1;

import javax.swing.*;

public class HelloWorldSwing {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("My First Project With Java Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello, Irsan Moch. Taufik Febrian");
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}