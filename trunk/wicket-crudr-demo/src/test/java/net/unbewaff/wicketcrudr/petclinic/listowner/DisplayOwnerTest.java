package net.unbewaff.wicketcrudr.petclinic.listowner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import net.unbewaff.petclinic.WicketApplication;
import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.petclinic.entities.Type;
import net.unbewaff.petclinic.listowner.DisplayOwner;
import net.unbewaff.petclinic.wrappers.OwnerWrapper;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class DisplayOwnerTest {

	private static WicketTester tester = new WicketTester(new WicketApplication());
	private final static transient Logger logger = Logger.getLogger(DisplayOwnerTest.class);
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
		tester.startPage(new DisplayOwner());
		tester.assertRenderedPage(DisplayOwner.class);
		tester.assertInvisible("owner");
		tester.debugComponentTrees();
	}
	
	@Test
	public void testSelectingOwner() {
		DisplayOwner page = new DisplayOwner(o);
		tester.startPage(page);
		DropDownChoice<OwnerWrapper> ddc = (DropDownChoice<OwnerWrapper>)tester.getComponentFromLastRenderedPage("form:select");
		assertTrue(ddc.getChoices().size() > 0);
		assertEquals("Franklin", ddc.getChoices().get(0).getLastName());
		assertEquals("Davis", ddc.getChoices().get(3).getLastName());
		assertEquals("Black", ddc.getChoices().get(6).getLastName());
		Component owner = tester.getComponentFromLastRenderedPage("owner");
		assertTrue(owner.isVisible());
		tester.debugComponentTrees();
	}

}
