package ru.newrishman.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.newrishman.library.domain.Author;
import ru.newrishman.library.domain.Book;
import ru.newrishman.library.service.AuthorService;
import ru.newrishman.library.service.BookService;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@Controller
public class LibraryController {

    private AuthorService authorService;
    private BookService bookService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookService.getAllBooks());
        return "books";
    }

    @RequestMapping(value = "authors", method = RequestMethod.GET)
    public String listAuthors(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("listAuthors", authorService.getAllAuthors());
        return "authors";
    }

    @RequestMapping(value = "books/{id}", method = RequestMethod.GET)
    public String listBooksByAuthor(@PathVariable("id") long id, Model model) {
        Author author = authorService.getAuthorById(id);

        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookService.findBookByAuthor(author.getName()));
        return "books";
    }

    @RequestMapping(value = "authors/{id}", method = RequestMethod.GET)
    public String listAuthorsByBook(@PathVariable("id") long id, Model model) {
        Book book = bookService.getBookById(id);

        model.addAttribute("author", new Author());
        model.addAttribute("listAuthors", authorService.findAuthorByBook(book.getTitle()));
        return "authors";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        if (book.getId() == 0) {
            bookService.addBook(book);
        } else {
            bookService.updateBook(book);
        }
        return "redirect:/books";
    }

    @RequestMapping(value = "/books/upload", method = RequestMethod.POST)
    @Transactional
    public String saveBook(@RequestParam("name") String authorName,
                           @RequestParam("title") String title,
                           @RequestParam("file") MultipartFile file) throws IOException {

        //проверяем наличие книги в БД
        Book book;
        Book searchB = bookService.findBookByTitle(title);
        if (searchB == null) {
            book = new Book(title, file.getBytes());
        } else {
            book = searchB;
        }
        Set<Book> books = new HashSet<>();
        books.add(book);

        //проверяем наличие автора в БД
        Author author;
        Author searchA = authorService.findAuthorByName(authorName);
        if (searchA == null) {
            author = new Author(authorName);

            Set<Author> authors = new HashSet<>();
            authors.add(author);

            author.setBooks(books);
            book.setAuthors(authors);
        } else {
            author = searchA;
            author.getBooks().add(book);
        }

        bookService.addBook(book);
        authorService.addAuthor(author);

        return "redirect:/books";
    }

    @RequestMapping(value = "/authors/add", method = RequestMethod.POST)
    public String addAuthor(@ModelAttribute("author") Author author) {
        if (author.getId() == 0) {
            if (authorService.findAuthorByName(author.getName()) == null) {
                authorService.addAuthor(author);
            }
        } else {
            authorService.updateAuthor(author);
        }
        return "redirect:/authors";
    }

    @RequestMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping("/authors/delete/{id}")
    public String deleteAuthors(@PathVariable("id") long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }

    @RequestMapping("books/edit/{id}")
    public String editBook(@PathVariable("id") long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("listBooks", bookService.getAllBooks());
        return "books";
    }

    @RequestMapping("authors/edit/{id}")
    public String editAuthor(@PathVariable("id") long id, Model model) {
        model.addAttribute("author", authorService.getAuthorById(id));
        model.addAttribute("listAuthors", authorService.getAllAuthors());
        return "authors";
    }

    @GetMapping("/download/{id}")
    public void downloadPDFResource(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        Book book = bookService.getBookById(id);
        response.setContentType("application/txt");
        response.setHeader("Content-Disposition", "attachment; filename=" + book.getTitle() + ".txt");

        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = book.getBytes();
        outStream.write(buffer);
        outStream.flush();
    }
}
