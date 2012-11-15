package net.unbewaff.petclinic.wrappers;

import java.io.Serializable;
import java.util.Set;

import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Position;
import net.unbewaff.wicketcrudr.annotations.InnerType.DisplayType;

/**
 * @author Nicktarix (David Hendrix)
 *
 */
public class OwnerWrapper implements Serializable {

	private static final long serialVersionUID = -5188229327429353036L;
	private Owner data;

	/**
	 * Initializes a wrapper
	 *
	 * @param data
	 */
	public OwnerWrapper(Owner data) {
		this.data = data;
	}

	/**
	 * @return the owners id
	 */
	public Integer getId() {
		return data.getId();
	}

	/**
	 * Sets the owners id
	 * @param id
	 */
	public void setId(Integer id) {
		data.setId(id);
	}

	/**
	 * @return the owners First Name
	 */
	@Lister
	@Position(2)
	public String getFirstName() {
		return data.getFirstName();
	}

	/**
	 * Sets the owners First Name
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		data.setFirstName(firstName);
	}

	/**
	 * @return the owners Last Name
	 */
	@Lister
	@Position(1)
	public String getLastName() {
		return data.getLastName();
	}


	/**
	 * sets the owners Last Name
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		data.setLastName(lastName);
	}

	/**
	 * @return the owners address
	 */
	@Lister
	@Position(3)
	public String getAddress() {
		return data.getAddress();
	}

	/**
	 * Sets the owners address
	 * @param address
	 */
	public void setAddress(String address) {
		data.setAddress(address);
	}

	/**
	 * @return the owners city
	 */
	@Lister
	@Position(4)
	public String getCity() {
		return data.getCity();
	}

	/**
	 * sets the owners city
	 * @param city
	 */
	public void setCity(String city) {
		data.setCity(city);
	}

	/**
	 * @return the owners phone number
	 */
	@Lister
	@Position(5)
	public String getPhone() {
		return data.getPhone();
	}

	/**
	 * stes the owners phone number
	 * @param phone
	 */
	public void setPhone(String phone) {
		data.setPhone(phone);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return data.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return data.hashCode();
	}

	public String toString() {
		return getLastName() + " " + getFirstName();
	}

	/**
	 * @return
	 * @see net.unbewaff.petclinic.entities.Owner#getPets()
	 */
	@Lister
	@Position(6)
	@InnerType(displayAs=DisplayType.CONCATENATED, separator="<br />", type=PetWrapper.class)
	public Set<Pet> getPets() {
		return data.getPets();
	}
}