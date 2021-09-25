package org.echocat.kata.java.part1.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.csv.CSVRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class Magazine extends BaseProduct {
    private String title;
    private String isbn;
    private List<String> authorEmails;
    private List<Author> authors;
    private Date publishedAt;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static final Function<CSVRecord, Magazine> fromCsvRecord = (record) ->
            new Magazine(record.get("\uFEFFtitle"),
                    record.get("isbn"),
                    record.get("authors"),
                    record.get("publishedAt")
            );

    public Magazine(String title, String isbn, String authorEmails, String publishedAt) {
        this.title = title;
        this.isbn = isbn;
        this.authorEmails = Arrays.asList(authorEmails.split(","));
        this.publishedAt = parseDate(publishedAt);
    }

    private static Date parseDate(String record) {
        try {
            return dateFormat.parse(record);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Magazine resolveAuthors(List<Author> authors) {
        this.authors = authors.stream()
                .filter(author -> authorEmails.contains(author.getEmail()))
                .collect(Collectors.toList());
        return this;
    }

    public void prettyPrint() {
        final String authorsOut = authors.stream().map(Author::prettyOut).collect(Collectors.joining("\n"));
        System.out.println("Magazine\n" +
                "\ttitle='" + title + "'\n" +
                "\tisbn='" + isbn + "'\n" +
                "\tauthors=\n" + authorsOut + "\n" +
                "\tpublishedAt='" + publishedAt);
    }

}