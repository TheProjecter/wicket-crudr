/**
 *
 */
package net.unbewaff.wicketcrudr.demo.dataclasses;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Author extends Person implements Serializable{

    private static final long serialVersionUID = -2679210042496392816L;
    private final Set<Book> booksWritten = new HashSet<Book>();

    public Author(Integer id, String firstName, String lastName, String address) {
        super(id, firstName, lastName, address);
    }

    public Set<Book> getBooksWritten() {
        return booksWritten;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Author [").append(", getId()=").append(getId())
                .append(", getFirstName()=").append(getFirstName()).append(", getLastName()=").append(getLastName())
                .append(", getAddress()=").append(getAddress()).append(", getOwnedBooks()=").append(getOwnedBooks())
                .append(", getBorrowedBooks()=").append(getBorrowedBooks()).append("]");
        return builder.toString();
    }

}
