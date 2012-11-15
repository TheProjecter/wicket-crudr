package net.unbewaff.petclinic.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.petclinic.WebSession;
import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.wrappers.OwnerWrapper;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

public class DemoDao implements Serializable, ICrudrListProvider<OwnerWrapper> {

	@Override
	public List<OwnerWrapper> getList() {
		List<OwnerWrapper> owners = new ArrayList<OwnerWrapper>();
		for (Owner o:((WebSession)WebSession.get()).getOwners()) {
			owners.add(new OwnerWrapper(o));
		}
		return owners;
	}
	

}
