package net.unbewaff.wicketcrudr.demo.petclinic;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Veterinarian implements Serializable {

    private static final long serialVersionUID = 180656430993292942L;
    private Integer id;
    private String firstName;
    private String lastName;
    private final Set<Specialities> specialities = new HashSet<Specialities>();

    public Veterinarian() {
        // intentionally empty
    }

    /**
     * @param id
     * @param firstName
     * @param lastName
     */
    public Veterinarian(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the specialities
     */
    public Set<Specialities> getSpecialities() {
        return specialities;
    }

}