/**
 *
 */
package net.unbewaff.petclinic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.annotations.Lister;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.datablocks.DataBlockFactory;
import net.unbewaff.wicketcrudr.datablocks.IDataBlock;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author davidh
 *
 */
/**
 * @author davidh
 *
 */
public class DisplayOwner extends WebPage implements Serializable {

	private IModel<OwnerWrapper> model = new Model<OwnerWrapper>();

	/**
	 * @param id
	 */
	public DisplayOwner() {
		super();
	}

	@Override
	protected void onInitialize() {
		final DropDownChoice<OwnerWrapper> ddc = new DropDownChoice<OwnerWrapper>("select", model, new OwnerWrapper(null).getList());
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
	public class OwnerWrapper implements ICrudrListProvider<OwnerWrapper>, Serializable{

		private Owner data;

		/**
		 * Initializes a wrapper
		 *
		 * @param data
		 */
		public OwnerWrapper(Owner data) {
			this.data = data;
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
		@Lister(position = 2)
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
		@Lister(position = 1)
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
		@Lister(position = 3)
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
		@Lister(position = 4)
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
		@Lister(position = 5)
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

		@Override
		public List<OwnerWrapper> getList() {
			List<OwnerWrapper> owners = new ArrayList<OwnerWrapper>();
			for (Owner o:((WebSession)WebSession.get()).getOwners()) {
				owners.add(new OwnerWrapper(o));
			}
			return owners;
		}
		
		public String toString() {
			return getLastName() + " " + getFirstName();
		}
	}

}
