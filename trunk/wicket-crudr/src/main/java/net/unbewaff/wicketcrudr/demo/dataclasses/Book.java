/**
 *
 */
package net.unbewaff.wicketcrudr.demo.dataclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Book implements Serializable {

    private static final long serialVersionUID = -848050107889157627L;
    private Integer id;
    private String title;
    final private List<Author> authors = new ArrayList<Author>();
    private String iSBN10;
    private String iSBN13;
    private Person borrowedTo;
    final private List<Person> borrowingHistory = new ArrayList<Person>();
    private Storage location;
    final private List<Review> reviews = new ArrayList<Review>();
    private Date datePublished;
    private String versionCovered;
    private String publisherUrl;
    private Integer pages;
    private BookType type;
    private String language;

    public Book() {
        // empty
    }

    /**
     * @param id
     * @param title
     * @param iSBN10
     * @param iSBN13
     * @param borrowedTo
     * @param location
     * @param datePublished
     * @param versionCovered
     * @param publisherUrl
     * @param pages
     * @param type
     * @param language
     */
    public Book(Integer id, String title, String iSBN10, String iSBN13, Person borrowedTo, Storage location,
            Date datePublished, String versionCovered, String publisherUrl, Integer pages, BookType type,
            String language) {
        this.id = id;
        this.title = title;
        this.iSBN10 = iSBN10;
        this.iSBN13 = iSBN13;
        this.borrowedTo = borrowedTo;
        this.location = location;
        this.datePublished = datePublished;
        this.versionCovered = versionCovered;
        this.publisherUrl = publisherUrl;
        this.pages = pages;
        this.type = type;
        this.language = language;
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

    public String getiSBN10() {
        return iSBN10;
    }

    public void setiSBN13(String iSBN) {
        this.iSBN10 = iSBN;
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

    public Storage getLocation() {
        return location;
    }

    public void setLocation(Storage location) {
        this.location = location;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public String getVersionCovered() {
        return versionCovered;
    }

    public void setVersionCovered(String versionCovered) {
        this.versionCovered = versionCovered;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    public void setPublisherUrl(String publisherUrl) {
        this.publisherUrl = publisherUrl;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getiSBN13() {
        return iSBN13;
    }

    public void setiSBN10(String iSBN10) {
        this.iSBN10 = iSBN10;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Book [id=").append(id).append(", title=").append(title).append(", authors=").append(authors)
                .append(", iSBN10=").append(iSBN10).append(", iSBN13=").append(iSBN13).append(", borrowedTo=")
                .append(borrowedTo).append(", borrowingHistory=").append(borrowingHistory).append(", location=")
                .append(location).append(", reviews=").append(reviews).append(", datePublished=").append(datePublished)
                .append(", versionCovered=").append(versionCovered).append(", publisherUrl=").append(publisherUrl)
                .append(", pages=").append(pages).append(", type=").append(type).append(", language=").append(language)
                .append("]");
        return builder.toString();
    }

}
