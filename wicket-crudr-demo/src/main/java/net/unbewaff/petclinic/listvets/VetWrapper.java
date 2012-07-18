package net.unbewaff.petclinic.listvets;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.unbewaff.petclinic.entities.Specialities;
import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.ResourceKey;
import net.unbewaff.wicketcrudr.components.ICrudrDataProvider;

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

	@Lister(innerType=SpecialitiesWrapper.class, innerResourcePrefix="Speciality")
	public Set<SpecialitiesWrapper> getSpecialities() {
		Set<SpecialitiesWrapper> list = new HashSet<SpecialitiesWrapper>();
		for (Specialities speciality : vet.getSpecialities()) {
			list.add(new SpecialitiesWrapper(speciality));
		}
		return list;
	}

	public String toString() {
		return vet.toString();
	}

	public static class SpecialitiesWrapper implements ICrudrDataProvider<Specialities>{
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

		@ResourceKey
		public final String name() {
			return speciality.name();
		}


	}

}
