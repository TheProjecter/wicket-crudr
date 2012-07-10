/**
 *
 */
package net.unbewaff.wicketcrudr.demo.petclinic.dataclasses;

import java.io.Serializable;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Owner implements Serializable {

    private static final long serialVersionUID = 6241124402970129344L;
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phone;

    public Owner() {
        // intentionally empty
    }

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param address
     * @param city
     * @param phone
     */
    public Owner(Integer id, String firstName, String lastName, String address, String city, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
