import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloGridLayout extends JFrame implements ActionListener {
    private JButton[] buttons;
    private boolean gameOver;

    public HelloGridLayout() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameOver = false;

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].addActionListener(this);
        }

        this.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            this.add(buttons[i]);
        }

        this.setSize(300, 300);
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            JButton button = (JButton) e.getSource();
            if (button.getText().isEmpty()) {
                button.setText("0");
                checkWinner();

                if (!gameOver) {
                    // AI sets "X" in the first empty button
                    for (int i = 0; i < buttons.length; i++) {
                        if (buttons[i].getText().isEmpty()) {
                            buttons[i].setText("X");
                            break;
                        }
                    }
                    checkWinner();
                }
            }
        }
    }

    private void checkWinner() {
        String winner = "";

        if (!buttons[0].getText().isEmpty() &&
                buttons[0].getText().equals(buttons[1].getText()) &&
                buttons[0].getText().equals(buttons[2].getText())) {
            winner = buttons[0].getText();
            buttons[0].setForeground(Color.RED);
            buttons[1].setForeground(Color.RED);
            buttons[2].setForeground(Color.RED);
        } else if (!buttons[3].getText().isEmpty() &&
                buttons[3].getText().equals(buttons[4].getText()) &&
                buttons[3].getText().equals(buttons[5].getText())) {
            winner = buttons[3].getText();
            buttons[3].setForeground(Color.RED);
            buttons[4].setForeground(Color.RED);
            buttons[5].setForeground(Color.RED);
        } else if (!buttons[6].getText().isEmpty() &&
                buttons[6].getText().equals(buttons[7].getText()) &&
                buttons[6].getText().equals(buttons[8].getText())) {
            winner = buttons[6].getText();
            buttons[6].setForeground(Color.RED);
            buttons[7].setForeground(Color.RED);
            buttons[8].setForeground(Color.RED);
        }

        // Check columns
        else if (!buttons[0].getText().isEmpty() &&
                buttons[0].getText().equals(buttons[3].getText()) &&
                buttons[0].getText().equals(buttons[6].getText())) {
            winner = buttons[0].getText();
            buttons[0].setForeground(Color.RED);
            buttons[3].setForeground(Color.RED);
            buttons[6].setForeground(Color.RED);
        } else if (!buttons[1].getText().isEmpty() &&
                buttons[1].getText().equals(buttons[4].getText()) &&
                buttons[1].getText().equals(buttons[7].getText())) {
            winner = buttons[1].getText();
            buttons[1].setForeground(Color.RED);
            buttons[4].setForeground(Color.RED);
            buttons[7].setForeground(Color.RED);
        } else if (!buttons[2].getText().isEmpty() &&
                buttons[2].getText().equals(buttons[5].getText()) &&
                buttons[2].getText().equals(buttons[8].getText())) {
            winner = buttons[2].getText();
            buttons[2].setForeground(Color.RED);
            buttons[5].setForeground(Color.RED);
            buttons[8].setForeground(Color.RED);
        }

        else if (!buttons[0].getText().isEmpty() &&
                buttons[0].getText().equals(buttons[4].getText()) &&
                buttons[0].getText().equals(buttons[8].getText())) {
            winner = buttons[0].getText();
            buttons[0].setForeground(Color.RED);
            buttons[4].setForeground(Color.RED);
            buttons[8].setForeground(Color.RED);
        } else if (!buttons[2].getText().isEmpty() &&
                buttons[2].getText().equals(buttons[4].getText()) &&
                buttons[2].getText().equals(buttons[6].getText())) {
            winner = buttons[2].getText();
            buttons[2].setForeground(Color.RED);
            buttons[4].setForeground(Color.RED);
            buttons[6].setForeground(Color.RED);
        }

        gameOver = !winner.isEmpty();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            HelloGridLayout h = new HelloGridLayout();
            h.setVisible(true);
        });
    }
}