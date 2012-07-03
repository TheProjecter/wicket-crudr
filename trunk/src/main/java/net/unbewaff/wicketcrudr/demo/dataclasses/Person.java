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
public class Person implements Serializable{

    private static final long serialVersionUID = 1736652919290485292L;
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private Set<Book> ownedBooks;
    private Set<Book> borrowedBooks;

    public Person() {
        //empty
    }

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param address
     * @param ownedBooks
     * @param borrowedBooks
     */
    public Person(Integer id, String firstName, String lastName, String address, Set<Book> ownedBooks,
            Set<Book> borrowedBooks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.ownedBooks = ownedBooks;
        this.borrowedBooks = borrowedBooks;
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

    public void setOwnedBooks(Set<Book> ownedBooks) {
        this.ownedBooks = ownedBooks;
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
