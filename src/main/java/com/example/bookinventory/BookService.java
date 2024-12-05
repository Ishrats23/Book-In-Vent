package com.example.bookinventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> filterBooks(String title, String author, String genre) {
        return bookRepository.findByTitleContaining(title)
                .stream()
                .filter(book -> book.getAuthor().contains(author) && book.getGenre().contains(genre))
                .toList();
    }
}
