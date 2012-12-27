/**
 * 
 */
package net.unbewaff.wicketcrudr.petclinic.listowner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.unbewaff.petclinic.WebSession;
import net.unbewaff.petclinic.entities.Owner;
import net.unbewaff.petclinic.listowner.DisplayOwner.OwnerWrapper;
import net.unbewaff.wicketcrudr.AutoDisplay;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author DavidH
 *
 */
public class ShowPetsTestPage extends WebPage implements Serializable {

	/**
	 * @param model
	 */
	public ShowPetsTestPage(IModel<?> model) {
		super(model);
	}

	@Override
	protected void onInitialize() {
		List<OwnerWrapper> list = new ArrayList<OwnerWrapper>();
		for (Owner o: ((WebSession)getSession()).getOwners()) {
			list.add(new OwnerWrapper(o));
		}
		Form form = new Form("form") {
		};
		final DropDownChoice<OwnerWrapper> ddc = new DropDownChoice<OwnerWrapper>("select", (IModel<OwnerWrapper>) getDefaultModel(), list);
		ddc.setOutputMarkupId(true);
		form.add(ddc);
		form.add(new Button("submit"));
		add(form);
		final Component wmc = new AutoDisplay<OwnerWrapper>("owner", (IModel<OwnerWrapper>) getDefaultModel(), OwnerWrapper.class);
		super.onInitialize();
		add(wmc);
	}

}
