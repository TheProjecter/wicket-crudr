package net.unbewaff.petclinic.wrappers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Position;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

class PetWrapper implements Serializable, ICrudrListProvider<Pet> {
	
	private static final long serialVersionUID = 4972449001878482038L;
	private Pet pet;
	private Owner owner;
	
	public PetWrapper(Pet pet, Owner owner) {
		this.pet = pet;
	}

	/**
	 * @return
	 * @see net.unbewaff.petclinic.entities.Pet#getName()
	 */
	@Lister
	@Position(1)
	public String getHumanReadableId() {
		return pet.getName() + " (" + pet.getType() + ")";
	}


	@Override
	public List<Pet> getList() {
		return new ArrayList<Pet>(owner.getPets());
	}
}