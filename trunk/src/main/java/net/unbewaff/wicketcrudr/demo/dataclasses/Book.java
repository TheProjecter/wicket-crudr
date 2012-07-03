/**
 *
 */
package net.unbewaff.wicketcrudr.demo.dataclasses;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Book implements Serializable {

    private static final long serialVersionUID = -8471080642718753265L;
    private Integer id;
    private String title;
    private List<Author> authors;
    private String iSBN;
    private Person borrowedTo;
    private List<Person> borrowingHistory;
    private Storage location;
    private List<Review> reviews;
    private Date datePublished;

    public Book() {
        //empty
    }

    /**
     * @param id
     * @param title
     * @param authors
     * @param iSBN
     * @param borrowedTo
     * @param borrowingHistory
     * @param location
     */
    public Book(Integer id, String title, List<Author> authors, String iSBN, Person borrowedTo,
            List<Person> borrowingHistory, Storage location, List<Review> reviews, Date datePublished) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.iSBN = iSBN;
        this.borrowedTo = borrowedTo;
        this.borrowingHistory = borrowingHistory;
        this.location = location;
        this.reviews = reviews;
        this.datePublished = datePublished;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
    public String getiSBN() {
        return iSBN;
    }
    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }
    public Person getBorrowedTo() {
        return borrowedTo;
    }
    public void setBorrowedTo(Person borrowedTo) {
        this.borrowedTo = borrowedTo;
    }
    public List<Person> getBorrowingHistory() {
        return borrowingHistory;
    }
    public void setBorrowingHistory(List<Person> borrowingHistory) {
        this.borrowingHistory = borrowingHistory;
    }
    public Storage getLocation() {
        return location;
    }
    public void setLocation(Storage location) {
        this.location = location;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }


}
