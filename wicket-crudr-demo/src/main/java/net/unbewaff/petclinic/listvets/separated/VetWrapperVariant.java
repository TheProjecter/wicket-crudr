/**
 *
 */
package net.unbewaff.petclinic.listvets.separated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.petclinic.WebSession;
import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.petclinic.listvets.VetWrapper;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.member.InnerPrototype.DisplayType;
import net.unbewaff.wicketcrudr.annotations.member.StringResource;
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
    @StringResource("VetWrapper")
    @InnerPrototype(resourcePrefix="Speciality", value=SpecialitiesWrapper.class, displayAs=DisplayType.CONCATENATED)
    public List<SpecialitiesWrapper> getSpecialities() {
        return super.getSpecialities();
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
