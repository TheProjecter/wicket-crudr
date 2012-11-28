package net.unbewaff.petclinic.editowner;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Editor.EditorType;
import net.unbewaff.wicketcrudr.annotations.Position;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

public class OwnerEditWrapper implements Serializable {
	
	private static final long serialVersionUID = -1310738561110224139L;
	private Owner owner;
	
	public OwnerEditWrapper(Owner owner) {
		this.owner = owner;
	}

	/**
	 * @return
	 * @see net.unbewaff.petclinic.entities.Owner#getId()
	 */
	public Integer getId() {
		return owner.getId();
	}

	/**
	 * @return
	 * @see net.unbewaff.petclinic.entities.Owner#getFirstName()
	 */
	@Editor
	@Lister
	@Position(1)
	public String getFirstName() {
		return owner.getFirstName();
	}

	/**
	 * @param firstName
	 * @see net.unbewaff.petclinic.entities.Owner#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		owner.setFirstName(firstName);
	}

	/**
	 * @return
	 * @see net.unbewaff.petclinic.entities.Owner#getLastName()
	 */
	@Editor
	@Lister
	@Position(2)
	public String getLastName() {
		return owner.getLastName();
	}

	/**
	 * @param lastName
	 * @see net.unbewaff.petclinic.entities.Owner#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		owner.setLastName(lastName);
	}

	/**
	 * @return
	 * @see net.unbewaff.petclinic.entities.Owner#getAddress()
	 */
	@Editor
	@Lister
	@Position(3)
	public String getAddress() {
		return owner.getAddress();
	}

	/**
	 * @param address
	 * @see net.unbewaff.petclinic.entities.Owner#setAddress(java.lang.String)
	 */
	public void setAddress(String address) {
		owner.setAddress(address);
	}

	/**
	 * @return
	 * @see net.unbewaff.petclinic.entities.Owner#getCity()
	 */
	@Editor
	@Lister
	@Position(4)
	public String getCity() {
		return owner.getCity();
	}

	/**
	 * @param city
	 * @see net.unbewaff.petclinic.entities.Owner#setCity(java.lang.String)
	 */
	public void setCity(String city) {
		owner.setCity(city);
	}

	/**
	 * @return
	 * @see net.unbewaff.petclinic.entities.Owner#getPhone()
	 */
	@Editor
	@Lister
	@Position(5)
	public String getPhone() {
		return owner.getPhone();
	}

	/**
	 * @param phone
	 * @see net.unbewaff.petclinic.entities.Owner#setPhone(java.lang.String)
	 */
	public void setPhone(String phone) {
		owner.setPhone(phone);
	}

	/**
	 * @return
	 * @see net.unbewaff.petclinic.entities.Owner#getPets()
	 */
	@Editor(editAs=EditorType.PALETTE)
	@Lister
	@InnerType(type=Pet.class)
	@Position(6)
	public Set<Pet> getPets() {
		return owner.getPets();
	}

	public String toString() {
		return getLastName() + " " + getFirstName();
	}
}