/**
 *
 */
package net.unbewaff.petclinic.listowner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.unbewaff.petclinic.WebSession;
import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.annotations.InnerType.DisplayType;
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
	private IModel<OwnerWrapper> model = new Model<OwnerWrapper>();

	/**
	 * @param id
	 */
	public DisplayOwner() {
		model.setObject(new OwnerWrapper(((WebSession)getSession()).getOwners().get(1)));
	}

	public DisplayOwner(Owner o) {
		model = new Model<OwnerWrapper>(new OwnerWrapper(o));
	}
	
	public DisplayOwner(PageParameters params) {
	}

	@Override
	protected void onInitialize() {
		List<OwnerWrapper> list = new ArrayList<OwnerWrapper>();
		for (Owner o: ((WebSession)getSession()).getOwners()) {
			list.add(new OwnerWrapper(o));
		}
		final DropDownChoice<OwnerWrapper> ddc = new DropDownChoice<OwnerWrapper>("select", model, list);
		ddc.setOutputMarkupId(true);
		final Component wmc = new AutoDisplay<OwnerWrapper>("owner", model, OwnerWrapper.class);
		ddc.add(new OnChangeAjaxBehavior() {
			
			private static final long serialVersionUID = 220633626349616188L;
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				boolean show = model.getObject() != null;
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
		private Owner data;
		private static final transient Logger logger = Logger.getLogger(OwnerWrapper.class);

		/**
		 * Initializes a wrapper
		 *
		 * @param data
		 */
		public OwnerWrapper(Owner data) {
			this.data = data;
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
			return data.getId();
		}

		/**
		 * Sets the owners id
		 * @param id
		 */
		public void setId(Integer id) {
			data.setId(id);
		}

		/**
		 * @return the owners First Name
		 */
		@Lister
		@Position(2)
		public String getFirstName() {
			return data.getFirstName();
		}

		/**
		 * Sets the owners First Name
		 * @param firstName
		 */
		public void setFirstName(String firstName) {
			data.setFirstName(firstName);
		}

		/**
		 * @return the owners Last Name
		 */
		@Lister
		@Position(1)
		public String getLastName() {
			return data.getLastName();
		}


		/**
		 * sets the owners Last Name
		 * @param lastName
		 */
		public void setLastName(String lastName) {
			data.setLastName(lastName);
		}

		/**
		 * @return the owners address
		 */
		@Lister
		@Position(3)
		public String getAddress() {
			return data.getAddress();
		}

		/**
		 * Sets the owners address
		 * @param address
		 */
		public void setAddress(String address) {
			data.setAddress(address);
		}

		/**
		 * @return the owners city
		 */
		@Lister
		@Position(4)
		public String getCity() {
			return data.getCity();
		}

		/**
		 * sets the owners city
		 * @param city
		 */
		public void setCity(String city) {
			data.setCity(city);
		}

		/**
		 * @return the owners phone number
		 */
		@Lister
		@Position(5)
		public String getPhone() {
			return data.getPhone();
		}

		/**
		 * stes the owners phone number
		 * @param phone
		 */
		public void setPhone(String phone) {
			data.setPhone(phone);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			return data.equals(obj);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		public int hashCode() {
			return data.hashCode();
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
		@InnerType(type=Pet.class)
		public Set<Pet> getPets() {
			logger.debug("Retrieving " + data.getPets().size() + " pets for " + data.getFirstName() + " " + data.getLastName() + ".");
			return data.getPets();
		}
	}

	class PetWrapper implements Serializable {
		
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
		public String getHumanReadableId() {
			return pet.getName() + " (" + pet.getType() + ")";
		}
	}
}
