/**
 *
 */
package net.unbewaff.petclinic.listvets.separated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.unbewaff.petclinic.WebSession;
import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.petclinic.listvets.VetWrapper;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.InnerType.DisplayType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

/**
 * @author davidh
 *
 */
public class VetWrapperVariant extends VetWrapper implements Serializable, ICrudrListProvider<VetWrapperVariant> {

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

	
	public Map<Integer, String> getSearchMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (VetWrapperVariant vWv : getList()) {
			map.put(vWv.getId(), vWv.getLastName() + " " + vWv.getFirstName());
		}
		return map;
	}

	@Override
	public List<VetWrapperVariant> getList() {
		List<VetWrapperVariant> list = new ArrayList<VetWrapperVariant>();
		for (Veterinarian vet : ((WebSession)WebSession.get()).getVeterinarians()) {
			list.add(new VetWrapperVariant(vet));
		}
		return list;
	}

}
