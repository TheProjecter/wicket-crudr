package net.unbewaff.petclinic.listvets;

import java.io.Serializable;
import java.util.Set;

import net.unbewaff.petclinic.entities.Specialities;
import net.unbewaff.petclinic.entities.Veterinarian;

public class VetWrapper implements Serializable {

	private final Veterinarian vet;

	public Integer getId() {
		return vet.getId();
	}

	public void setId(Integer id) {
		vet.setId(id);
	}

	public String getFirstName() {
		return vet.getFirstName();
	}

	public void setFirstName(String firstName) {
		vet.setFirstName(firstName);
	}

	public String getLastName() {
		return vet.getLastName();
	}

	public void setLastName(String lastName) {
		vet.setLastName(lastName);
	}

	public Set<Specialities> getSpecialities() {
		return vet.getSpecialities();
	}

	public String toString() {
		return vet.toString();
	}

	public VetWrapper(Veterinarian vet) {
		super();
		this.vet = vet;
	}

}
