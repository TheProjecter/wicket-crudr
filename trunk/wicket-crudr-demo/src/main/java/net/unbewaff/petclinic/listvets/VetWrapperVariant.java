/**
 *
 */
package net.unbewaff.petclinic.listvets;

import java.io.Serializable;
import java.util.Set;

import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.petclinic.listvets.VetWrapper.SpecialitiesWrapper;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.InnerType.DisplayType;
import net.unbewaff.wicketcrudr.annotations.Lister;

/**
 * @author davidh
 *
 */
public class VetWrapperVariant extends VetWrapper implements Serializable {

	/**
	 * @param vet
	 */
	public VetWrapperVariant(Veterinarian vet) {
		super(vet);
	}

	@Override
	@Lister(resourcePrefix="VetWrapper")
	@InnerType(resourcePrefix="Speciality", type=SpecialitiesWrapper.class, displayAs=DisplayType.CONCATENATED)
	public Set<SpecialitiesWrapper> getSpecialities() {
		return super.getSpecialities();
	}

}
