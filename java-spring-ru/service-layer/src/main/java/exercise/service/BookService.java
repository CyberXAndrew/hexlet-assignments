package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository repository;
    @Autowired
    private BookMapper mapper;

    public List<BookDTO> getAllBooks() {
        List<Book> books = repository.findAll();
        return books.stream()
                .map(mapper::map)
                .toList();
    }

    public BookDTO getBook(Long id) {
        Book book = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book with id " + id + " not found"));
        return mapper.map(book);
    }

    public BookDTO createBook(BookCreateDTO createDTO) {
        try {
            Author author = authorRepository.findById(createDTO.getAuthorId()).get();
            Book book = mapper.map(createDTO);
            book.setAuthor(author);
            repository.save(book);
            return mapper.map(book);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public BookDTO updateBook(Long id, BookUpdateDTO updateDTO) {
        try {
            Book book = repository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Book with id " + id + " not found"));
            mapper.update(updateDTO, book);
            repository.save(book);
            return mapper.map(book);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
    // END
}
