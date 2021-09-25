package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.models.BaseProduct;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class CSVPrinterTest {





    @Test
    public void findByIsbn() {
         CSVPrinter csvPrinter= new CSVPrinter();
        final String isbn = "4545-8558-3232";
        final List<BaseProduct> byIsbn = csvPrinter.findByIsbn(isbn);
        assertThat(byIsbn.size(),is(equalTo(1)));
        assertThat(byIsbn.get(0).getIsbn(),is(equalTo(isbn)));

    }

    @Test
    public void findByAuthor() {
        CSVPrinter csvPrinter= new CSVPrinter();
        final String authorEmail = "null-gustafsson@echocat.org";
        final List<BaseProduct> byAuthor = csvPrinter.findByAuthor(authorEmail);
        assertThat(byAuthor.size(),is(equalTo(2)));
        byAuthor.forEach( product -> {
            assertThat(product.getAuthorEmails(),hasItem(authorEmail));

        });
    }

    @Test
    public void getSortedByTitle() {
        CSVPrinter csvPrinter= new CSVPrinter();
        final List<BaseProduct> sortedByTitle = csvPrinter.getSortedByTitle();
        assertThat(sortedByTitle.size(),is(equalTo(14)));
        final List<String> titles = sortedByTitle.stream().map(BaseProduct::getTitle)
                .collect(Collectors.toList());
        final List<String> sortedTitles = titles.stream().sorted().collect(Collectors.toList());

        assertThat(titles,is(equalTo(sortedTitles)));
    }
}
