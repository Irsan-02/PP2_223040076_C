package Pertemuan4;

import javax.swing.*;
import java.awt.event.*;

public class WindowListenerExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame ("WindowListener Example");
        JLabel label = new JLabel("Lakukan Operasi pada Jendela.");
        label.setBounds(50,50,300,30);

        frame.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {
                label.setText("Window Opened. ");
            }

            public void windowClosing(WindowEvent e) {
                label.setText("Window Closing. ");
            }

            public void windowClosed(WindowEvent e) {
                label.setText("Window CLosed. ");
            }
            public void windowIconified(WindowEvent e) {
                label.setText("Window Minimized. ");
            }
            public void windowDeiconified(WindowEvent e) {
                label.setText("Window Restored. ");
            }
            public void windowActivated(WindowEvent e) {
                label.setText("Window Activated. ");
            }
            public void windowDeactivated(WindowEvent e) {
                label.setText("Window Deactivate. ");
            }
        }); 

        frame.add(label);
        frame.setSize(400,200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}