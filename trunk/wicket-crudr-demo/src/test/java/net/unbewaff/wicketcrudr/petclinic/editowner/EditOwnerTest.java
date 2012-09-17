/**
 * 
 */
package net.unbewaff.wicketcrudr.petclinic.editowner;

import java.util.Date;

import net.unbewaff.petclinic.WicketApplication;
import net.unbewaff.petclinic.editowner.EditOwner;
import net.unbewaff.petclinic.editowner.OwnerEditWrapper;
import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.petclinic.entities.Type;
import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.providers.label.IterableLabel;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author DavidH
 *
 */
public class EditOwnerTest {

	private static WicketTester tester = new WicketTester(new WicketApplication());
	private final static transient Logger logger = Logger.getLogger(EditOwnerTest.class);
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
		tester.startPage(new EditOwner(Model.of(o)));
		tester.assertRenderedPage(EditOwner.class);
		tester.debugComponentTrees();
	}
	
	@Test
	public void testAutoDisplayForOwnerEditWrapper() {
		assertNotNull(o.getPets());
		assertEquals(1, o.getPets().size());
		IModel<OwnerEditWrapper> model = Model.of(new OwnerEditWrapper(o));
		Component c = new AutoDisplay<OwnerEditWrapper>("id", model, OwnerEditWrapper.class);
		tester.startComponentInPage(c);
		tester.assertLabel("id:border:border_body:view:1:fragmentContainer:label", "FirstName");
		tester.assertLabel("id:border:border_body:view:1:fragmentContainer:value", "First Name");
		tester.assertLabel("id:border:border_body:view:2:fragmentContainer:label", "LastName");
		tester.assertLabel("id:border:border_body:view:2:fragmentContainer:value", "Maiden Name");
		tester.assertLabel("id:border:border_body:view:3:fragmentContainer:label", "Address");
		tester.assertLabel("id:border:border_body:view:3:fragmentContainer:value", "Owner&#039;s Address");
		tester.assertLabel("id:border:border_body:view:4:fragmentContainer:label", "City");
		tester.assertLabel("id:border:border_body:view:4:fragmentContainer:value", "Owner&#039;s City");
		tester.assertLabel("id:border:border_body:view:5:fragmentContainer:label", "Phone");
		tester.assertLabel("id:border:border_body:view:5:fragmentContainer:value", "555-1234-98765");
		tester.assertLabel("id:border:border_body:view:6:fragmentContainer:label", "Pets");
		tester.assertComponent("id:border:border_body:view:6:fragmentContainer:value", IterableLabel.class);
		tester.assertLabel("id:border:border_body:view:6:fragmentContainer:value:body:list:1:text", "Pet [name=Binky]");

	}


}
