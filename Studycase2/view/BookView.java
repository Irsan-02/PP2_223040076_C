package Studycase2.view;

import Studycase2.controller.BookController;
import Studycase2.model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BookView extends JFrame {
    private JTextField titleField, authorField, yearField;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private BookController controller;
    private int selectedBookId = -1;

    public BookView() {
        setTitle("Daftar Buku");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new BookController(this);

        // Layout
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Form
        titleField = new JTextField();
        authorField = new JTextField();
        yearField = new JTextField();

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Author:"));
        formPanel.add(authorField);
        formPanel.add(new JLabel("Year:"));
        formPanel.add(yearField);

        // Buttons
        JButton addButton = new JButton("Add Book");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton listButton = new JButton("List Books");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);

        // Table
        tableModel = new DefaultTableModel(new String[]{"No", "Title", "Author", "Year"}, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Events
        addButton.addActionListener(e -> addBook());
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
        listButton.addActionListener(e -> listBooks());
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = bookTable.getSelectedRow();
                if (selectedRow != -1) {
                    List<Book> books = controller.listBooks();
                    if (selectedRow < books.size()) {
                        selectedBookId = books.get(selectedRow).getId();
                        titleField.setText(books.get(selectedRow).getTitle());
                        authorField.setText(books.get(selectedRow).getAuthor());
                        yearField.setText(String.valueOf(books.get(selectedRow).getYear()));
                    }
                }
            }
        });

        // Assemble
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);
        add(panel);

        listBooks();
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());
        controller.addBook(title, author, year);
        listBooks();
        clearFields();
    }

    private void updateBook() {
        if (selectedBookId != -1) {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());
            controller.updateBook(selectedBookId, title, author, year);
            listBooks();
            clearFields();
        } else {
            showMessage("Please select a book to update.");
        }
    }

    private void deleteBook() {
        if (selectedBookId != -1) {
            controller.deleteBook(selectedBookId);
            listBooks();
            clearFields();
        } else {
            showMessage("Please select a book to delete.");
        }
    }

    private void listBooks() {
        tableModel.setRowCount(0);
        List<Book> books = controller.listBooks();
        int index = 1;
        for (Book book : books) {
            tableModel.addRow(new Object[]{index++, book.getTitle(), book.getAuthor(), book.getYear()});
        }
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
        selectedBookId = -1;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookView().setVisible(true));
    }
}
