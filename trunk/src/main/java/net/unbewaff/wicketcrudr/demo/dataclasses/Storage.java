/**
 *
 */
package net.unbewaff.wicketcrudr.demo.dataclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Storage implements Serializable{

    private static final long serialVersionUID = 8070724796186002491L;
    private Integer id;
    private String cupboard;
    private String board;
    private final List<Book> books = new ArrayList<Book>();

    public Storage() {
        //empty
    }

    /**
     * @param id
     * @param cupboard
     * @param board
     * @param books
     */
    public Storage(Integer id, String cupboard, String board) {
        this.id = id;
        this.cupboard = cupboard;
        this.board = board;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupboard() {
        return cupboard;
    }

    public void setCupboard(String cupboard) {
        this.cupboard = cupboard;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public List<Book> getBooks() {
        return books;
    }


}
