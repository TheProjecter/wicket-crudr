package net.unbewaff.petclinic.wrappers;

import java.io.Serializable;

import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Position;
import net.unbewaff.wicketcrudr.annotations.ResourceKey;
import net.unbewaff.wicketcrudr.tools.wrappinglist.IWrapper;

public class PetWrapper implements Serializable, IWrapper<Pet> {
	
	private static final long serialVersionUID = 4972449001878482038L;
	private Pet pet;
	
	public PetWrapper(Pet pet) {
		this.pet = pet;
	}

	/**
	 * @return
	 * @see net.unbewaff.petclinic.entities.Pet#getName()
	 */
	@Lister
	@Position(1)
	@ResourceKey
	public String getHumanReadableId() {
		return pet.getName() + " (" + pet.getType().getName() + ")";
	}

	@Override
	public Pet getObject() {
		return pet;
	}

	@Override
	public String toString() {
		return pet.toString();
	}
}