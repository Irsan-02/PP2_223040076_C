package PERTEMUAN6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryApp extends JFrame {

    private DefaultTableModel tableModel;

    public LibraryApp() {
        // Setting up the main frame
        setTitle("Aplikasi Perpustakaan");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creating the menu bar and menus
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuForm = new JMenu("Form");
        JMenuItem menuItemExit = new JMenuItem("Exit");
        JMenuItem menuItemOpenForm = new JMenuItem("Tambah Buku");

        menuFile.add(menuItemExit);
        menuForm.add(menuItemOpenForm);

        menuBar.add(menuFile);
        menuBar.add(menuForm);

        setJMenuBar(menuBar);

        // Adding action to open form
        menuItemOpenForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFormDialog();
            }
        });

        menuItemExit.addActionListener(e -> System.exit(0));

        // Creating a table to display book data
        tableModel = new DefaultTableModel(new String[]{"Judul Buku", "Pengarang", "Genre", "Rating"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void openFormDialog() {
        JDialog formDialog = new JDialog(this, "Form Tambah Buku", true);
        formDialog.setSize(400, 300);
        formDialog.setLayout(new GridLayout(5, 2));

        // Components for form input
        JTextField txtTitle = new JTextField();
        JTextField txtAuthor = new JTextField();

        JComboBox<String> genreComboBox = new JComboBox<>(new String[]{"Fiksi", "Non-Fiksi", "Edukasi", "Fantasi", "Sejarah"});

        JSlider ratingSlider = new JSlider(1, 5, 3);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);

        JButton btnSave = new JButton("Save");

        // Adding components to the form dialog
        formDialog.add(new JLabel("Judul Buku:"));
        formDialog.add(txtTitle);

        formDialog.add(new JLabel("Pengarang:"));
        formDialog.add(txtAuthor);

        formDialog.add(new JLabel("Genre:"));
        formDialog.add(genreComboBox);

        formDialog.add(new JLabel("Rating (1-5):"));
        formDialog.add(ratingSlider);

        formDialog.add(new JLabel(""));
        formDialog.add(btnSave);

        // Save button action to add data to the table
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = txtTitle.getText();
                String author = txtAuthor.getText();
                String genre = (String) genreComboBox.getSelectedItem();
                int rating = ratingSlider.getValue();

                // Add data to table
                tableModel.addRow(new Object[]{title, author, genre, rating});
                formDialog.dispose();
            }
        });

        formDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LibraryApp().setVisible(true);
        });
    }
}
