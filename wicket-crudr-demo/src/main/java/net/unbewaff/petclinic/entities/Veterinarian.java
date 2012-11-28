package net.unbewaff.petclinic.entities;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Veterinarian other = (Veterinarian) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}