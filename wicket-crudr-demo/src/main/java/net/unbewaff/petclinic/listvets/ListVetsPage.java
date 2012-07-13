/**
 *
 */
package net.unbewaff.petclinic.listvets;

import java.util.List;

import net.unbewaff.petclinic.WebSession;
import net.unbewaff.petclinic.entities.Veterinarian;
import net.unbewaff.wicketcrudr.components.AutoLister;
import net.unbewaff.wicketcrudr.components.ICrudrListProvider;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author DavidH
 *
 */
public class ListVetsPage extends WebPage {

	/**
	 *
	 */
	public ListVetsPage() {
	}

	/**
	 * @param model
	 */
	public ListVetsPage(IModel<?> model) {
		super(model);
	}

	/**
	 * @param parameters
	 */
	public ListVetsPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		VetListProvider listProvider = new VetListProvider(((WebSession)getSession()).getVeterinarians());
		add(new AutoLister<VetWrapper>("vetsList", Model.of(listProvider), 5, VetWrapper.class));
		super.onInitialize();
	}

}
