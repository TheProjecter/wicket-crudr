package net.unbewaff.wicketcrudr.petclinic.listowner;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.unbewaff.petclinic.WicketApplication;
import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.petclinic.entities.Type;
import net.unbewaff.petclinic.wrappers.PetWrapper;
import net.unbewaff.tools.AWrappingList;
import net.unbewaff.tools.IWrapperFactory;
import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.petclinic.listowner.ShowPetsTest.OwnersPetsWrapper;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
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
		OwnersPetsWrapper wrapper = new OwnersPetsWrapper(o);
		final AutoDisplay<OwnersPetsWrapper> component = new AutoDisplay<OwnersPetsWrapper>("test", new Model<OwnersPetsWrapper>(wrapper), OwnersPetsWrapper.class);
		component.add(new AjaxEventBehavior("onclick") {

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				target.add(component);
			}
			
		});
		tester.startComponentInPage(component);
		tester.debugComponentTrees();
		tester.assertLabel("test:border:border_body:view:1:fragmentContainer:label", "Pets");
		tester.assertLabel("test:border:border_body:view:1:fragmentContainer:value:body:list:1:text", "Binky (Skelettal Horse)");
		Pet p2 = new Pet();
		p2.setBirthDate(new Date());
		p2.setId(214);
		p2.setName("Albert");
		Type type = new Type();
		type.setId(12);
		type.setName("Minion");
		p2.setType(type);
		o.getPets().add(p2);
		tester.executeAjaxEvent(component, "onclick");
		logger.debug(tester.getResponse());
	}
	
	public class OwnersPetsWrapper implements Serializable {
		
		private static final long serialVersionUID = 2965653174774793043L;
		private Owner o;

		public OwnersPetsWrapper(Owner o) {
			this.o = o;
		}

		@Lister
		@InnerType(type=PetWrapper.class)
		public List<PetWrapper> getPets() {

			return new AWrappingList<PetWrapper, Pet>() {
				@Override
				protected List<Pet> getBaseList() {
					return o.getPets();
				}

				@Override
				protected IWrapperFactory<PetWrapper, Pet> getWrapperFactory() {
					return new IWrapperFactory<PetWrapper, Pet>() {

						@Override
						public PetWrapper newWrapper(Pet target) {
							return new PetWrapper(target);
						}
					};
				}			
			};
		}
		
	}

}