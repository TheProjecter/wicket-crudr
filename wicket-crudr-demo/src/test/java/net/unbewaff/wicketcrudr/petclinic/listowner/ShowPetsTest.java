package net.unbewaff.wicketcrudr.petclinic.listowner;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import net.unbewaff.petclinic.WicketApplication;
import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.petclinic.entities.Type;
import net.unbewaff.petclinic.listowner.DisplayOwner;
import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.Lister;

import org.apache.log4j.Logger;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class ShowPetsTest {

	private static WicketTester tester = new WicketTester(new WicketApplication());
	private final static transient Logger logger = Logger.getLogger(ShowPetsTest.class);
	private Owner o = new Owner();
	private Pet p = new Pet();

	@Before
	public void setUp() {
		o.setAddress("Owner's Address");
		o.setCity("Owner's City");
		o.setFirstName("First Name");
		o.setId(211);
		o.setLastName("Maiden Name");
		o.setPhone("555-1234-98765");
		p.setBirthDate(new Date());
		p.setId(232);
		p.setName("Binky");
		p.setOwner(o);
		Type type = new Type();
		type.setId(213);
		type.setName("Skelettal Horse");
		p.setType(type);
			
	}

	@Test
	public void testRendering() {
		tester.startComponentInPage(new AutoDisplay("test", new Model<OwnersPetsWrapper>(new OwnersPetsWrapper(o)), OwnersPetsWrapper.class));
		tester.debugComponentTrees();
		tester.assertLabel("test:border:border_body:view:1:fragmentContainer:label", "Pets");
		tester.assertLabel("test:border:border_body:view:1:fragmentContainer:value:body:list:1:text", "Pet [name=Binky]");
	}
	
	public class OwnersPetsWrapper implements Serializable {
		
		private Owner o;

		public OwnersPetsWrapper(Owner o) {
			this.o = o;
		}

		@Lister
		@InnerType(type=Pet.class)
		public Set<Pet> getPets() {
			return o.getPets();
		}
		
	}

}
