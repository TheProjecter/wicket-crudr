package net.unbewaff.petclinic.wrappers;

import java.io.Serializable;

import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.tools.IWrapper;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Position;

public class PetWrapper implements Serializable, IWrapper<Pet> {
	
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
	public Pet getObject() {
		return pet;
	}
}