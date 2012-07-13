package net.unbewaff.petclinic.listvets;

import java.io.Serializable;
import java.util.Set;

import net.unbewaff.petclinic.entities.Specialities;
import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.wicketcrudr.annotations.Lister;

public class VetWrapper implements Serializable {

	private final Veterinarian vet;

	public VetWrapper(Veterinarian vet) {
		super();
		this.vet = vet;
	}

	public Integer getId() {
		return vet.getId();
	}

	public void setId(Integer id) {
		vet.setId(id);
	}

	@Lister(position=2)
	public String getFirstName() {
		return vet.getFirstName();
	}

	public void setFirstName(String firstName) {
		vet.setFirstName(firstName);
	}

	@Lister(position=1)
	public String getLastName() {
		return vet.getLastName();
	}

	public void setLastName(String lastName) {
		vet.setLastName(lastName);
	}

	@Lister
	public Set<Specialities> getSpecialities() {
		return vet.getSpecialities();
	}

	public String toString() {
		return vet.toString();
	}

}
