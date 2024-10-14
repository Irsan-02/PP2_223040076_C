import javax.swing.*;
import java.awt.*;

public class BiodataForm extends JFrame {

    public BiodataForm() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        JLabel labelJudul = new JLabel("Form Biodata");
        c.gridx = 1; c.gridy = 0; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(labelJudul, c);

        JLabel labelNama = new JLabel("Nama:");
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(labelNama, c);

        JTextField fieldNama = new JTextField(15);
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(fieldNama, c);
        
        JLabel labelHp = new JLabel("Nomor HP:");
        c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(labelHp, c);
        
        JTextField fieldHp = new JTextField(15);
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(fieldHp, c);
        
        JLabel labelGender = new JLabel("Jenis Kelamin:");
        c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(labelGender, c);
        
        JRadioButton maleButton = new JRadioButton("Laki-Laki");
        JRadioButton femaleButton = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        
        c.gridx = 0; c.gridy = 6; c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(genderPanel, c);
        
        JCheckBox foreignerCheckBox = new JCheckBox("Warga Negara Asing");
        c.gridx = 0; c.gridy = 7; c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(foreignerCheckBox, c);
        
        JButton saveButton = new JButton("Simpan");
        c.gridx = 1; c.gridy = 8; c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(saveButton, c);

        this.add(mainPanel);

        this.setSize(400, 400);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BiodataForm h = new BiodataForm();
                h.setVisible(true);
            }
        });
    }
}