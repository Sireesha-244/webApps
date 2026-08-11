package com.bookstore.serviceimpl;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {

        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            return book.get();
        }

        return null;
    }

    @Override
    public Book updateBook(Long id, Book book) {

        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {

            Book oldBook = existingBook.get();

            oldBook.setTitle(book.getTitle());
            oldBook.setAuthor(book.getAuthor());
            oldBook.setPrice(book.getPrice());

            return bookRepository.save(oldBook);
        }

        return null;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}