package net.unbewaff.petclinic.listvets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.unbewaff.petclinic.entities.Specialities;
import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.member.Order;
import net.unbewaff.wicketcrudr.annotations.member.StringResource;
import net.unbewaff.wicketcrudr.annotations.type.Prototype;
import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;
import net.unbewaff.wicketcrudr.tools.wrappinglist.IWrapper;

@Prototype
public class VetWrapper implements Serializable, IWrapper<Veterinarian> {

    private static final long serialVersionUID = 911673107682330559L;
    private final Veterinarian vet;

    public VetWrapper(Veterinarian vet) {
        this.vet = vet;
    }

    @Override
    public Veterinarian exposeObject() {
        return vet;
    }

    public Integer getId() {
        return vet.getId();
    }

    public void setId(Integer id) {
        vet.setId(id);
    }

    @Order(2)
    @StringResource("VetWrapper")
    public String getFirstName() {
        return vet.getFirstName();
    }

    public void setFirstName(String firstName) {
        vet.setFirstName(firstName);
    }

    @Order(1)
    @StringResource("VetWrapper")
    public String getLastName() {
        return vet.getLastName();
    }

    public void setLastName(String lastName) {
        vet.setLastName(lastName);
    }

    @InnerPrototype(resourcePrefix="Speciality", type=SpecialitiesWrapper.class)
    public List<SpecialitiesWrapper> getSpecialities() {
        List<SpecialitiesWrapper> list = new ArrayList<SpecialitiesWrapper>();
        for (Specialities speciality : vet.getSpecialities()) {
            list.add(new SpecialitiesWrapper(speciality));
        }
        return list;
    }

    @Override
    public String toString() {
        return vet.toString();
    }

    @Prototype
    public static class SpecialitiesWrapper implements ICrudrDataProvider<Specialities>{

        private static final long serialVersionUID = 1734840726953537324L;
        private final Specialities speciality;

        public SpecialitiesWrapper(Specialities speciality) {
            this.speciality = speciality;
        }

        @Override
        public List<Specialities> getList() {
            return Arrays.asList(Specialities.values());
        }

        @Override
        public Specialities newInstance() {
            return null;
        }

        @Override
        public Class<Specialities> getType() {
            return Specialities.class;
        }

        @Override
        public Serializable getId() {
            return speciality;
        }

        @Override
        public final String toString() {
            return speciality.name();
        }


    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (vet == null ? 0 : vet.hashCode());
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
        VetWrapper other = (VetWrapper) obj;
        if (vet == null) {
            if (other.vet != null) {
                return false;
            }
        } else if (!vet.equals(other.vet)) {
            return false;
        }
        return true;
    }

}
