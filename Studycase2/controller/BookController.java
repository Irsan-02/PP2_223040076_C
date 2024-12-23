package Studycase2.controller;

import Studycase2.model.Book;
import Studycase2.view.BookView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookController {
    private Connection connection;
    private BookView view;

    public BookController(BookView view) {
        this.view = view;
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database.");
        }
    }

    public void addBook(String title, String author, int year) {
        String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, year);
            stmt.executeUpdate();
            view.showMessage("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            view.showMessage("Failed to add book.");
        }
    }

    public void updateBook(int id, String title, String author, int year) {
        String sql = "UPDATE books SET title = ?, author = ?, year = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, year);
            stmt.setInt(4, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                view.showMessage("Book updated successfully!");
            } else {
                view.showMessage("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            view.showMessage("Failed to update book.");
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                view.showMessage("Book deleted successfully!");
            } else {
                view.showMessage("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            view.showMessage("Failed to delete book.");
        }
    }

    public List<Book> listBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
