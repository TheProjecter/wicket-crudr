/**
 *
 */
package net.unbewaff.wicketcrudr.demo.dataclasses;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class LibraryGenerator {

    List<Author> authors = new ArrayList<Author>();
    List<Person> persons = new ArrayList<Person>();
    List<Book> books = new ArrayList<Book>();
    List<Review> reviews = new ArrayList<Review>();
    List<Storage> storages = new ArrayList<Storage>();

    @Test
    public void testCreateLibrary() {
        storages.add(new Storage(1, "Computing Books", "1"));
        storages.add(new Storage(2, "Computing Books", "2"));
        Book apacheWicketCookbook = new Book(1, "Apache Wicket Cookbook", "1849511608", "978-1-84951-160-5", null, storages.get(0), new Date(2011, 3, 1), "Wicket 1.4, but most material is applicable to Wicket 1.5", "http://www.packtpub.com/apache-wicket-cookbook/book", 312, BookType.PAPERBACK, "English");
        storages.get(0).getBooks().add(apacheWicketCookbook);
        Set<Book> booksWritten = new HashSet<Book>();
        booksWritten.add(apacheWicketCookbook);
        books.add(apacheWicketCookbook);
        authors.add(new Author(1, "Igor", "Vaynberg", null));
        authors.get(0).getBooksWritten().add(apacheWicketCookbook);
        apacheWicketCookbook.getAuthors().add(authors.get(0));
        authors.add(new Author(2, "Martijn", "Dashorst", null));
        authors.add(new Author(3, "Eelco", "Hillenius", null));
        Book wicketInAction = new Book(2, "Wicket In Action", "1932394982", "978-1932394986", null, storages.get(0), new Date(2008, 9, 9), "Wicket 1.3", "http://manning.com/dashorst/", 392, BookType.PAPERBACK, "English");
        storages.get(0).getBooks().add(wicketInAction);
        authors.get(1).getBooksWritten().add(wicketInAction);
        authors.get(2).getBooksWritten().add(wicketInAction);
        wicketInAction.getAuthors().add(authors.get(1));
        wicketInAction.getAuthors().add(authors.get(2));
        books.add(wicketInAction);
        authors.add(new Author(4, "Jochen", "Mader", null));
        Book wicket = new Book(3, "Wicket", "3868020810", "978-3868020816", null, storages.get(1), new Date(2012, 3, 1), "Wicket 1.5", "http://entwickler-press.de/ep/psecom,id,2,buchid,242,p,0,_language,de.html", 220, BookType.PAPERBACK, "German");
        storages.get(1).getBooks().add(wicket);
        books.add(wicket);
        wicket.getAuthors().add(authors.get(3));
        authors.get(3).getBooksWritten().add(wicket);
        Book enjoy = new Book(4, "Enjoying Web Development with Wicket (2nd edition)", "0557522463", "978-0557522460", null, storages.get(1), new Date(2010, 4, 1), "Wicket 1.4", "http://www.agileskills2.org/EWDW/", 358, BookType.HARDCOVER, "English");
        authors.add(new Author(5, "Kent", "Tong", null));
        enjoy.getAuthors().add(authors.get(4));
        authors.get(4).getBooksWritten().add(enjoy);
        storages.get(1).getBooks().add(enjoy);
        books.add(enjoy);
        Book pro = new Book(5, "Pro Wicket", "1590597222", "978-1590597224", null, storages.get(1), new Date(2006, 9, 7), "Wicket 1.2", "http://www.apress.com/9781590597224", 328, BookType.HARDCOVER, "English");
        authors.add(new Author(6, "Karthik", "Gurumurthy", null));
        pro.getAuthors().add(authors.get(5));
        authors.get(5).getBooksWritten().add(pro);
        storages.get(1).getBooks().add(pro);
        books.add(pro);
        Book wicket2 = new Book(6, "Wicket: Komponentenbasierte Webanwendungen in Java", "1590597222", "978-3-89864-569-0", null, storages.get(1), new Date(2009, 12, 1), "Wicket 1.4", "http://www.dpunkt.de/buecher/2921.html", 350, BookType.HARDCOVER, "German");
        authors.add(new Author(7, "Rolean", "Förther", null));
        authors.add(new Author(8, "Carl-Eric", "Menzel", null));
        authors.add(new Author(9, "Olaf", "Siefart", null));
        wicket2.getAuthors().add(authors.get(6));
        wicket2.getAuthors().add(authors.get(7));
        wicket2.getAuthors().add(authors.get(8));
        authors.get(6).getBooksWritten().add(wicket2);
        authors.get(7).getBooksWritten().add(wicket2);
        authors.get(8).getBooksWritten().add(wicket2);
        storages.get(1).getBooks().add(wicket2);
        books.add(wicket2);
        Book wicket3 = new Book(7, "Thorough use of open source Web application development Wicket", "4798022217", "978-4798022215", null, storages.get(1), new Date(2009, 3, 1), "Wicket 1.4", null, 431, BookType.PAPERBACK, "Japanese");
        authors.add(new Author(10, "Tsutomu", "Yano", null));
        wicket3.getAuthors().add(authors.get(9));
        authors.get(9).getBooksWritten().add(wicket3);
        storages.get(1).getBooks().add(wicket3);
        books.add(wicket3);
        for(Book b : books) {
            System.out.println(b);
        }
    }


}
