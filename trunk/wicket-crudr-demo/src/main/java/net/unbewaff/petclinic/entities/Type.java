package net.unbewaff.petclinic.entities;

import java.io.Serializable;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Type implements Serializable {

    private static final long serialVersionUID = 3457906006416638325L;
    private Integer id;
    private String name;

    public Type() {
        // intentionally empty
    }

    /**
     * @param id
     * @param name
     */
    public Type(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}