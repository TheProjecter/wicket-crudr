package net.unbewaff.petclinic.wrappers;

import java.io.Serializable;
import java.util.List;

import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype.DisplayType;
import net.unbewaff.wicketcrudr.annotations.member.Order;
import net.unbewaff.wicketcrudr.tools.wrappinglist.AWrappingList;
import net.unbewaff.wicketcrudr.tools.wrappinglist.IWrapperFactory;

import org.apache.log4j.Logger;

/**
 * @author Nicktarix (David Hendrix)
 *
 */
public class OwnerWrapper implements Serializable {

    private static final long serialVersionUID = -5188229327429353036L;
    private Owner owner;
    private static final transient Logger logger = Logger.getLogger(OwnerWrapper.class);

    /**
     * Initializes a wrapper
     *
     * @param data
     */
    public OwnerWrapper(Owner data) {
        this.owner = data;
    }

    /**
     * @return the owners id
     */
    public Integer getId() {
        return owner.getId();
    }

    /**
     * Sets the owners id
     * @param id
     */
    public void setId(Integer id) {
        owner.setId(id);
    }

    /**
     * @return the owners First Name
     */
    @Order(2)
    public String getFirstName() {
        return owner.getFirstName();
    }

    /**
     * Sets the owners First Name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        owner.setFirstName(firstName);
    }

    /**
     * @return the owners Last Name
     */
    @Order(1)
    public String getLastName() {
        return owner.getLastName();
    }


    /**
     * sets the owners Last Name
     * @param lastName
     */
    public void setLastName(String lastName) {
        owner.setLastName(lastName);
    }

    /**
     * @return the owners address
     */
    @Order(3)
    public String getAddress() {
        return owner.getAddress();
    }

    /**
     * Sets the owners address
     * @param address
     */
    public void setAddress(String address) {
        owner.setAddress(address);
    }

    /**
     * @return the owners city
     */
    @Order(4)
    public String getCity() {
        return owner.getCity();
    }

    /**
     * sets the owners city
     * @param city
     */
    public void setCity(String city) {
        owner.setCity(city);
    }

    /**
     * @return the owners phone number
     */
    @Order(5)
    public String getPhone() {
        return owner.getPhone();
    }

    /**
     * stes the owners phone number
     * @param phone
     */
    public void setPhone(String phone) {
        owner.setPhone(phone);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return owner.equals(obj);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return owner.hashCode();
    }

    @Override
    public String toString() {
        return getLastName() + " " + getFirstName();
    }

    /**
     * @return
     * @see net.unbewaff.petclinic.entities.Owner#getPets()
     */
    @Order(6)
    @InnerPrototype(displayAs=DisplayType.CONCATENATED, separator="<br />", type=PetWrapper.class)
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
}