/**
 *
 */
package net.unbewaff.wicketcrudr.demo.dataclasses;

import java.io.Serializable;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Review implements Serializable {

    private static final long serialVersionUID = -6673657806033836477L;
    private Integer id;
    private Book book;
    private Person reviewer;
    private Rating rating;
    private String comment;

    public Review() {
        //empty
    }

    /**
     * @param id
     * @param book
     * @param reviewer
     * @param rating
     * @param comment
     */
    public Review(Integer id, Book book, Person reviewer, Rating rating, String comment) {
        this.id = id;
        this.book = book;
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Person getReviewer() {
        return reviewer;
    }

    public void setReviewer(Person reviewer) {
        this.reviewer = reviewer;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
