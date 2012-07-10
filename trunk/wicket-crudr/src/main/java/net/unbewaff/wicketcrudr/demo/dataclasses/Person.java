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
public class Person implements Serializable{

    private static final long serialVersionUID = 1736652919290485292L;
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private final Set<Book> ownedBooks = new HashSet<Book>();
    private final Set<Book> borrowedBooks = new HashSet<Book>();

    public Person() {
        //empty
    }

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param address
     */
    public Person(Integer id, String firstName, String lastName, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Book> getOwnedBooks() {
        return ownedBooks;
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Person [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
                .append(lastName).append(", address=").append(address).append(", ownedBooks=").append(ownedBooks)
                .append(", borrowedBooks=").append(borrowedBooks).append("]");
        return builder.toString();
    }

}
