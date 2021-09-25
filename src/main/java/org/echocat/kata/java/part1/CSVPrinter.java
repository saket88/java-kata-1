package org.echocat.kata.java.part1;

import lombok.NonNull;
import org.echocat.kata.java.part1.models.Author;
import org.echocat.kata.java.part1.models.BaseProduct;
import org.echocat.kata.java.part1.models.Book;
import org.echocat.kata.java.part1.models.Magazine;
import org.echocat.kata.java.part1.parser.Parser;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVPrinter {
    private final Parser parser = new Parser("org/echocat/kata/java/part1/data/", ';');
    private String authorsFile;
    private String booksFile;
    private String magazinesFile;
    private List<Author> authors;
    private List<Book> books;
    private List<Magazine> magazines;

    public CSVPrinter() {
        authorsFile = "authors.csv";
        booksFile = "books.csv";
        magazinesFile = "magazines.csv";
        loadDataFomFiles();
    }

    public CSVPrinter(@NonNull String authorsFile,
                              @NonNull String booksFile,
                              @NonNull String magazinesFile) {
        this.authorsFile = authorsFile;
        this.booksFile = booksFile;
        this.magazinesFile = magazinesFile;
        loadDataFomFiles();
    }

    public void loadDataFomFiles() {
        authors = parser.parse(authorsFile, Author.fromCsvRecord);
        books = parser.parse(booksFile, Book.fromCsvRecord)
                .stream()
                .map(book -> book.resolveAuthors(authors))
                .collect(Collectors.toList());
        magazines = parser.parse(magazinesFile, Magazine.fromCsvRecord)
                .stream()
                .map(magazine -> magazine.resolveAuthors(authors))
                .collect(Collectors.toList());
    }

    private Stream<BaseProduct> printedProductStream() {
        return Stream.concat(books.stream(), magazines.stream());
    }

    public List<BaseProduct> getProducts() {
        return printedProductStream()
                .collect(Collectors.toList());
    }

    public List<BaseProduct> findByIsbn(String isbn) {
        return printedProductStream()
                .filter(item -> item.getIsbn().equals(isbn))
                .collect(Collectors.toList());
    }

    public List<BaseProduct> findByAuthor(String authorEmail) {
        return printedProductStream()
                .filter(item -> item.getAuthorEmails().contains(authorEmail))
                .collect(Collectors.toList());
    }

    public List<BaseProduct> getSortedByTitle() {
        return printedProductStream()
                .sorted(Comparator.comparing(BaseProduct::getTitle))
                .collect(Collectors.toList());
    }

    public void addBook(@NonNull Book book) {
        books.add(book.resolveAuthors(authors));
    }

    public void addMagazine(@NonNull Magazine magazine) {
        magazines.add(magazine.resolveAuthors(authors));
    }
}
