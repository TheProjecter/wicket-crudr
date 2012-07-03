/**
 *
 */
package net.unbewaff.wicketcrudr.demo.dataclasses;

import java.io.Serializable;
import java.util.Set;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Author extends Person implements Serializable{

    private static final long serialVersionUID = -2679210042496392816L;
    private Set<Book> booksWritten;

    public Author(Integer id, String firstName, String lastName, String address, Set<Book> ownedBooks,
            Set<Book> borrowedBooks, Set<Book> booksWritten) {

        super(id, firstName, lastName, address, ownedBooks, borrowedBooks);
        this.booksWritten = booksWritten;
    }

    public Set<Book> getBooksWritten() {
        return booksWritten;
    }

    public void setBooksWritten(Set<Book> booksWritten) {
        this.booksWritten = booksWritten;
    }

}
