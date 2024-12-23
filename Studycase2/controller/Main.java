package Studycase2.controller;

/**
 *
 * @author irsan
 */
import Studycase2.controller.BookController;
import Studycase2.view.BookView;

public class Main {
    public static void main(String[] args) {
        BookView view = new BookView();
        new BookController(view);
        view.setVisible(true);
    }
}
