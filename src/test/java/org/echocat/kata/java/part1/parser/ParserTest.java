package org.echocat.kata.java.part1.parser;

import org.echocat.kata.java.part1.models.Author;
import org.echocat.kata.java.part1.models.Book;
import org.echocat.kata.java.part1.models.Magazine;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class ParserTest {

    @Test
    public void parseBooks(){
        Parser parser= new Parser("org/echocat/kata/java/part1/data/", ';');

        List<Book> books=parser.parse("books.csv", Book.fromCsvRecord);

        assertThat(books.size(),is(8));
    }

    @Test
    public void parseMagzines(){
        Parser parser= new Parser("org/echocat/kata/java/part1/data/", ';');

        List<Magazine> magazines=parser.parse("magazines..csv", Magazine.fromCsvRecord);

        assertThat(magazines.size(),is(6));
    }



    @Test
    public void parseAuthors(){
        Parser parser= new Parser("org/echocat/kata/java/part1/data/", ';');

        List<Author> authors=parser.parse("authors.csv", Author.fromCsvRecord);

        assertThat(authors.size(),is(6));
    }
}
