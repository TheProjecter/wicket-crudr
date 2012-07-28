/**
 *
 */
package net.unbewaff.petclinic.listvets.separated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

/**
 * @author DavidH
 *
 */
public class VetVariantListProvider implements ICrudrListProvider<VetWrapperVariant>, Serializable {

	private final List<VetWrapperVariant> list = new ArrayList<VetWrapperVariant>();

	public VetVariantListProvider(List<Veterinarian> list) {
		for (Veterinarian vet : list) {
			this.list.add(new VetWrapperVariant(vet));
		}
	}
	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.components.ICrudrListProvider#getList()
	 */
	@Override
	public List<VetWrapperVariant> getList() {
		return list;
	}

}
