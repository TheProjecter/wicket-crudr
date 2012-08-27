/**
 * 
 */
package net.unbewaff.petclinic.editowner;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.entities.Pet;
import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.annotations.Editor;
import net.unbewaff.wicketcrudr.annotations.Editor.EditorType;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author DavidH
 *
 */
public class EditOwner extends WebPage {
	
	private IModel<OwnerEditWrapper> model = new Model<OwnerEditWrapper>();

	/**
	 * 
	 */
	public EditOwner() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param model
	 */
	public EditOwner(IModel<?> model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parameters
	 */
	public EditOwner(PageParameters parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	protected void onInitialize() {
		final DropDownChoice<OwnerEditWrapper> ddc = new DropDownChoice<OwnerEditWrapper>("select", model, new OwnerEditWrapper(null).getList());
		final Component wmc = new AutoDisplay<OwnerEditWrapper>("owner", model, OwnerEditWrapper.class);
		ddc.setOutputMarkupId(true);
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
	
	static class OwnerEditWrapper implements  ICrudrListProvider<OwnerEditWrapper>, Serializable {
		
		private Owner owner;
		
		public OwnerEditWrapper(Owner owner) {
			this.owner = owner;
		}

		@Override
		public List<OwnerEditWrapper> getList() {
			return null;
		}

		/**
		 * @return
		 * @see net.unbewaff.petclinic.entities.Owner#getId()
		 */
		public Integer getId() {
			return owner.getId();
		}

		/**
		 * @return
		 * @see net.unbewaff.petclinic.entities.Owner#getFirstName()
		 */
		@Editor
		public String getFirstName() {
			return owner.getFirstName();
		}

		/**
		 * @param firstName
		 * @see net.unbewaff.petclinic.entities.Owner#setFirstName(java.lang.String)
		 */
		public void setFirstName(String firstName) {
			owner.setFirstName(firstName);
		}

		/**
		 * @return
		 * @see net.unbewaff.petclinic.entities.Owner#getLastName()
		 */
		@Editor
		public String getLastName() {
			return owner.getLastName();
		}

		/**
		 * @param lastName
		 * @see net.unbewaff.petclinic.entities.Owner#setLastName(java.lang.String)
		 */
		public void setLastName(String lastName) {
			owner.setLastName(lastName);
		}

		/**
		 * @return
		 * @see net.unbewaff.petclinic.entities.Owner#getAddress()
		 */
		@Editor
		public String getAddress() {
			return owner.getAddress();
		}

		/**
		 * @param address
		 * @see net.unbewaff.petclinic.entities.Owner#setAddress(java.lang.String)
		 */
		public void setAddress(String address) {
			owner.setAddress(address);
		}

		/**
		 * @return
		 * @see net.unbewaff.petclinic.entities.Owner#getCity()
		 */
		@Editor
		public String getCity() {
			return owner.getCity();
		}

		/**
		 * @param city
		 * @see net.unbewaff.petclinic.entities.Owner#setCity(java.lang.String)
		 */
		public void setCity(String city) {
			owner.setCity(city);
		}

		/**
		 * @return
		 * @see net.unbewaff.petclinic.entities.Owner#getPhone()
		 */
		@Editor
		public String getPhone() {
			return owner.getPhone();
		}

		/**
		 * @param phone
		 * @see net.unbewaff.petclinic.entities.Owner#setPhone(java.lang.String)
		 */
		public void setPhone(String phone) {
			owner.setPhone(phone);
		}

		/**
		 * @return
		 * @see net.unbewaff.petclinic.entities.Owner#getPets()
		 */
		@Editor(editAs=EditorType.PALETTE)
		public Set<Pet> getPets() {
			return owner.getPets();
		}
	}
}
