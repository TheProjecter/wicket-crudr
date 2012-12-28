/**
 *
 */
package net.unbewaff.petclinic.listowner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.petclinic.WebSession;
import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.annotations.InnerPrototype;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.annotations.Order;
import net.unbewaff.wicketcrudr.annotations.type.Prototype;
import net.unbewaff.wicketcrudr.annotations.type.Prototypes;
import net.unbewaff.wicketcrudr.tools.wrappinglist.AWrappingList;
import net.unbewaff.wicketcrudr.tools.wrappinglist.IWrapper;
import net.unbewaff.wicketcrudr.tools.wrappinglist.IWrapperFactory;

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
 * @author David Hendrix (Nicktarix)
 *
 */
public class DisplayOwner extends WebPage implements Serializable {

	private static final long serialVersionUID = 8992622237513231584L;
	private static final transient Logger logger = Logger.getLogger(DisplayOwner.class);

	/**
	 * @param id
	 */
	public DisplayOwner() {
		this(((WebSession)WebSession.get()).getOwners().get(0));
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
		final Component wmc = new AutoDisplay<OwnerWrapper>("owner", (IModel<OwnerWrapper>) getDefaultModel(), OwnerWrapper.class);
		ddc.add(new OnChangeAjaxBehavior() {
			
			private static final long serialVersionUID = 220633626349616188L;
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				boolean show = getDefaultModel() != null && getDefaultModelObject() != null;
				target.add(wmc);
				logger.debug("First pet of selected owner (" + ((OwnerWrapper)wmc.getDefaultModelObject()).getFirstName() + ") in onUpdate: " + ((OwnerWrapper)wmc.getDefaultModelObject()).getPets().get(0).getHumanReadableId()); 
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
		@Order(2)
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
		@Order(1)
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
		@Order(3)
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
		@Order(4)
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
		@Order(5)
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
		@Order(6)
		@InnerPrototype(type=PetWrapper.class)
		public List<PetWrapper> getPets() {
			logger.debug("Retrieving " + owner.getPets().size() + " pets for " + owner.getFirstName() + " " + owner.getLastName() + ".");

			return new AWrappingList<PetWrapper, Pet>() {
				@Override
				protected List<Pet> getBaseList() {
					return owner.getPets();
				}

				@Override
				protected IWrapperFactory<PetWrapper, Pet> getWrapperFactory() {
					return new IWrapperFactory<DisplayOwner.PetWrapper, Pet>() {

						@Override
						public PetWrapper newWrapper(Pet target) {
							return new PetWrapper(target);
						}
					};
				}
			};
		}
	}

	@Prototype(Prototypes.LONG)
	public static class PetWrapper implements Serializable, IWrapper<Pet> {
		
		private static final long serialVersionUID = 4972449001878482038L;
		private Pet pet;
		
		public PetWrapper(Pet pet) {
			this.pet = pet;
		}
		
		public Pet exposeObject() {
			return pet;
		}

		/**
		 * @return
		 * @see net.unbewaff.petclinic.entities.Pet#getName()
		 */
		@Lister
		@Order(1)
		public String getHumanReadableId() {
			return pet.getName() + " (" + pet.getType().getName() + ")";
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Pet: " + getHumanReadableId() + " - Owner: " + pet.getOwner();
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((pet == null) ? 0 : pet.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			PetWrapper other = (PetWrapper) obj;
			if (pet == null) {
				if (other.pet != null) {
					return false;
				}
			} else if (!pet.equals(other.pet)) {
				return false;
			}
			return true;
		}
	}
}
