package net.unbewaff.petclinic.editowner;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.petclinic.wrappers.PetWrapper;
import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Editor.EditorType;
import net.unbewaff.wicketcrudr.annotations.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Order;
import net.unbewaff.wicketcrudr.tools.wrappinglist.AWrappingList;
import net.unbewaff.wicketcrudr.tools.wrappinglist.IWrapperFactory;
import net.unbewaff.wicketcrudr.tools.wrappinglist.WrappingList;

public class OwnerEditWrapper implements Serializable {
	
	private static final long serialVersionUID = -1310738561110224139L;
	private Owner owner;
	private static final transient Logger logger = Logger.getLogger(OwnerEditWrapper.class);
	
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
	@Order(1)
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
	@Order(2)
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
	@Order(3)
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
	@Order(4)
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
	@Order(5)
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
	@Lister
	@Editor(editAs=EditorType.PALETTE)
	@InnerPrototype(type=PetWrapper.class)
	@Order(6)
	public List<PetWrapper> getPets() {
		return new AWrappingList<PetWrapper, Pet>() {
			@Override
			protected List<Pet> getBaseList() {
				return owner.getPets();
			}

			@Override
			protected IWrapperFactory<PetWrapper, Pet> getWrapperFactory() {
				return new IWrapperFactory<PetWrapper, Pet>() {

					@Override
					public PetWrapper newWrapper(Pet target) {
						return new PetWrapper(target);
					}
				};
			}
		};
	}

	public String toString() {
		return getLastName() + " " + getFirstName();
	}
}