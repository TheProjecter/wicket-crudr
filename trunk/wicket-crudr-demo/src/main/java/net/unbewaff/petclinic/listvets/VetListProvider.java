/**
 *
 */
package net.unbewaff.petclinic.listvets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

/**
 * @author DavidH
 *
 */
public class VetListProvider implements ICrudrListProvider<VetWrapper>, Serializable {

	private final List<VetWrapper> list = new ArrayList<VetWrapper>();

	public VetListProvider(List<Veterinarian> list) {
		for (Veterinarian vet : list) {
			this.list.add(new VetWrapper(vet));
		}
	}
	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.components.ICrudrListProvider#getList()
	 */
	@Override
	public List<VetWrapper> getList() {
		return list;
	}

}
