/**
 *
 */
package net.unbewaff.petclinic.listowner;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.unbewaff.petclinic.WebSession;
import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.tools.IWrapper;
import net.unbewaff.tools.WrappingList;
import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Position;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author davidh
 *
 */
/**
 * @author davidh
 *
 */
public class DisplayOwner extends WebPage implements Serializable {

	private static final long serialVersionUID = 8992622237513231584L;

	/**
	 * @param id
	 */
	public DisplayOwner() {
		super(new Model<OwnerWrapper>());
		setDefaultModelObject(new OwnerWrapper(((WebSession)getSession()).getOwners().get(1)));
	}

	public DisplayOwner(Owner o) {
		super(new Model<OwnerWrapper>(new OwnerWrapper(o)));
	}
	
	public DisplayOwner(PageParameters params) {
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void onInitialize() {
		List<OwnerWrapper> list = new ArrayList<OwnerWrapper>();
		for (Owner o: ((WebSession)getSession()).getOwners()) {
			list.add(new OwnerWrapper(o));
		}
		final DropDownChoice<OwnerWrapper> ddc = new DropDownChoice<OwnerWrapper>("select", (IModel<OwnerWrapper>) getDefaultModel(), list);
		ddc.setOutputMarkupId(true);
		final Component wmc = new AutoDisplay<OwnerWrapper>("owner", (IModel<OwnerWrapper>) getDefaultModelObject(), OwnerWrapper.class);
		ddc.add(new OnChangeAjaxBehavior() {
			
			private static final long serialVersionUID = 220633626349616188L;
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				boolean show = getDefaultModel() != null && getDefaultModelObject() != null;
				target.add(wmc);
				wmc.setVisible(show);

			}
		});
		add(ddc);
		add(wmc);
		super.onInitialize();
	}

	/**
	 * @author Nicktarix (David Hendrix)
	 *
	 */
	public static class OwnerWrapper implements Serializable {

		private static final long serialVersionUID = -5188229327429353036L;
		private Owner owner;
		private static final transient Logger logger = Logger.getLogger(OwnerWrapper.class);

		/**
		 * Initializes a wrapper
		 *
		 * @param data
		 */
		public OwnerWrapper(Owner data) {
			this.owner = data;
			if (!logger.isDebugEnabled()) {
				logger.error("NO DEBUG");
				logger.setLevel(Level.DEBUG);
			}
			logger.debug("Created warpper for " + data);
			logger.debug("Owner has " + data.getPets().size() + " pets.");
		}

		/**
		 * @return the owners id
		 */
		public Integer getId() {
			return owner.getId();
		}

		/**
		 * Sets the owners id
		 * @param id
		 */
		public void setId(Integer id) {
			owner.setId(id);
		}

		/**
		 * @return the owners First Name
		 */
		@Lister
		@Position(2)
		public String getFirstName() {
			return owner.getFirstName();
		}

		/**
		 * Sets the owners First Name
		 * @param firstName
		 */
		public void setFirstName(String firstName) {
			owner.setFirstName(firstName);
		}

		/**
		 * @return the owners Last Name
		 */
		@Lister
		@Position(1)
		public String getLastName() {
			return owner.getLastName();
		}


		/**
		 * sets the owners Last Name
		 * @param lastName
		 */
		public void setLastName(String lastName) {
			owner.setLastName(lastName);
		}

		/**
		 * @return the owners address
		 */
		@Lister
		@Position(3)
		public String getAddress() {
			return owner.getAddress();
		}

		/**
		 * Sets the owners address
		 * @param address
		 */
		public void setAddress(String address) {
			owner.setAddress(address);
		}

		/**
		 * @return the owners city
		 */
		@Lister
		@Position(4)
		public String getCity() {
			return owner.getCity();
		}

		/**
		 * sets the owners city
		 * @param city
		 */
		public void setCity(String city) {
			owner.setCity(city);
		}

		/**
		 * @return the owners phone number
		 */
		@Lister
		@Position(5)
		public String getPhone() {
			return owner.getPhone();
		}

		/**
		 * stes the owners phone number
		 * @param phone
		 */
		public void setPhone(String phone) {
			owner.setPhone(phone);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			return owner.equals(obj);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		public int hashCode() {
			return owner.hashCode();
		}

		public String toString() {
			return getLastName() + " " + getFirstName();
		}

		/**
		 * @return
		 * @see net.unbewaff.petclinic.entities.Owner#getPets()
		 */
		@Lister
		@Position(6)
		@InnerType(type=PetWrapper.class)
		public List<PetWrapper> getPets() {
			logger.debug("Retrieving " + owner.getPets().size() + " pets for " + owner.getFirstName() + " " + owner.getLastName() + ".");
			Constructor<PetWrapper> constructor = null;
			try {
				constructor = PetWrapper.class.getConstructor(Pet.class);
			} catch (SecurityException e) {
				logger.error(e);
			} catch (NoSuchMethodException e) {
				logger.error(e);
			}
			return new WrappingList<PetWrapper, Pet>(constructor) {
				@Override
				protected List<Pet> getBaseList() {
					return owner.getPets();
				}
			};
		}
	}

	class PetWrapper implements Serializable, IWrapper<Pet> {
		
		private static final long serialVersionUID = 4972449001878482038L;
		private Pet pet;
		
		public PetWrapper(Pet pet) {
			this.pet = pet;
		}
		
		public Pet getObject() {
			return pet;
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
	}
}
