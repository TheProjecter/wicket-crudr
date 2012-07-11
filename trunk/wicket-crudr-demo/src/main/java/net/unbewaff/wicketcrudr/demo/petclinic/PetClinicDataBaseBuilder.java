/**
 *
 */
package net.unbewaff.wicketcrudr.demo.petclinic;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class PetClinicDataBaseBuilder {
    static final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);

    private List<Owner> owners = new ArrayList<Owner>();
    private List<Type> types = new ArrayList<Type>();
    private List<Veterinarian> vets = new ArrayList<Veterinarian>();
    private List<Pet> pets = new ArrayList<Pet>();

    public void testDataCreation() throws ParseException {
        vets.add(new Veterinarian(1, "James", "Carter"));
        vets.add(new Veterinarian(2, "Helen", "Leary"));
        vets.add(new Veterinarian(3, "Linda", "Douglas"));
        vets.add(new Veterinarian(4, "Rafael", "Ortega"));
        vets.add(new Veterinarian(5, "Henry", "Stevens"));
        vets.add(new Veterinarian(6, "Sharon", "Jenkins"));
        vets.get(1).getSpecialities().add(Specialities.dentistry);
        vets.get(2).getSpecialities().add(Specialities.radiology);
        vets.get(2).getSpecialities().add(Specialities.surgery);
        vets.get(3).getSpecialities().add(Specialities.radiology);
        vets.get(4).getSpecialities().add(Specialities.dentistry);
        types.add(new Type(1, "cat"));
        types.add(new Type(2, "dog"));
        types.add(new Type(3, "lizard"));
        types.add(new Type(4, "snake"));
        types.add(new Type(5, "bird"));
        types.add(new Type(6, "hamster"));
        owners.add(new Owner(1, "George", "Franklin", "110 W. Liberty St.", "Madison", "6085551023"));
        owners.add(new Owner(2, "Betty", "Davis", "638 Cardinal Ave.", "Sun Prairie", "6085551749"));
        owners.add(new Owner(3, "Eduardo", "Rodriquez", "2693 Commerce St.", "McFarland", "6085558763"));
        owners.add(new Owner(4, "Harold", "Davis", "563 Friendly St.", "Windsor", "6085553198"));
        owners.add(new Owner(5, "Peter", "McTavish", "2387 S. Fair Way", "Madison", "6085552765"));
        owners.add(new Owner(6, "Jean", "Coleman", "105 N. Lake St.", "Monona", "6085552654"));
        owners.add(new Owner(7, "Jeff", "Black", "1450 Oak Blvd.", "Monona", "6085555387"));
        owners.add(new Owner(8, "Maria", "Escobito", "345 Maple St.", "Madison", "6085557683"));
        owners.add(new Owner(9, "David", "Schroeder", "2749 Blackhawk Trail", "Madison", "6085559435"));
        owners.add(new Owner(10, "Carlos", "Estaban", "2335 Independence La.", "Waunakee", "6085555487"));
        pets.add(new Pet(1, "Leo", "09/07/2000", types.get(0), owners.get(0)));
        pets.add(new Pet(2, "Basil", "08/06/2002", types.get(5), owners.get(1)));
        pets.add(new Pet(3, "Rosy", "04/17/2001", types.get(1), owners.get(2)));
        pets.add(new Pet(4, "Jewel", "03/07/2000", types.get(1), owners.get(2)));
        pets.add(new Pet(5, "Iggy", "11/30/2000", types.get(2), owners.get(3)));
        pets.add(new Pet(6, "George", "01/20/2000", types.get(3), owners.get(4)));
        pets.add(new Pet(7, "Samantha", "09/04/1995", types.get(0), owners.get(5)));
        pets.add(new Pet(8, "Max", "09/04/1995", types.get(0), owners.get(5)));
        pets.add(new Pet(9, "Lucky", "08/06/1999", types.get(4), owners.get(6)));
        pets.add(new Pet(10, "Mulligan", "02/24/1997", types.get(1), owners.get(7)));
        pets.add(new Pet(11, "Freddy", "03/09/2000", types.get(4), owners.get(8)));
        pets.add(new Pet(12, "Lucky", "06/24/2000", types.get(1), owners.get(9)));
        pets.add(new Pet(13, "Sly", "06/08/2002", types.get(0), owners.get(9)));
        List<Visit> visits = new ArrayList<Visit>();
        visits.add(new Visit(1, pets.get(6), vets.get(0), "03/04/1996", "rabies shot"));
        visits.add(new Visit(2, pets.get(7), vets.get(2), "03/04/1996", "rabies shot"));
        visits.add(new Visit(3, pets.get(7), vets.get(3), "06/04/1996", "neutered"));
        visits.add(new Visit(4, pets.get(6), vets.get(3), "09/04/1996", "spayed"));
    }

}
