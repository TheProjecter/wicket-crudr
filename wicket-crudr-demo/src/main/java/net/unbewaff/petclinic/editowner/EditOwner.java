/**
 * 
 */
package net.unbewaff.petclinic.editowner;


import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.wicketcrudr.AutoDisplay;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author DavidH
 *
 */
public class EditOwner extends WebPage {
	
	private IModel<OwnerEditWrapper> model = new Model<OwnerEditWrapper>();

	/**
	 * @param model
	 */
	public EditOwner(IModel<Owner> model) {
		super(model);
		this.model = Model.of(new OwnerEditWrapper(model.getObject())); 
	}

	protected void onInitialize() {
		final DropDownChoice<OwnerEditWrapper> ddc = new DropDownChoice<OwnerEditWrapper>("select", model, model.getObject().getList());
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
}
