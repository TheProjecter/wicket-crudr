package net.unbewaff.petclinic.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Pet implements Serializable {

    private static final transient DateTimeFormatter df = DateTimeFormat.forPattern("mm/dd/yyyy");

    private static final transient Logger logger = Logger.getLogger(Pet.class);
    private static final long serialVersionUID = -2551188438430930513L;
    private Integer id;
    private String name;
    private Date birthDate;
    private Type type;
    private Owner owner;

    public Pet() {
        // intentionally empty
    }

    /**
     * @param id
     * @param name
     * @param birthDate
     * @param type
     * @param owner
     * @throws ParseException
     */
    public Pet(Integer id, String name, String birthDate, Type type, Owner owner) {
        this.id = id;
        this.name = name;
        this.birthDate = df.parseLocalDate(birthDate).toDate();
        this.type = type;
        this.owner = owner;
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

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate
     *            the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return the owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}