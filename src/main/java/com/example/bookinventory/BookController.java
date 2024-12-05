package com.example.bookinventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam String genre,
                          @RequestParam String publicationDate, @RequestParam String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPublicationDate(LocalDate.parse(publicationDate));
        book.setIsbn(isbn);
        bookService.addBook(book);
        return "redirect:/";
    }

    @GetMapping("/filter")
    public String filterBooks(@RequestParam String title, @RequestParam String author,
                              @RequestParam String genre, Model model) {
        model.addAttribute("books", bookService.filterBooks(title, author, genre));
        return "index";
    }
}
