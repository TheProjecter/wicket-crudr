/**
 *
 */
package net.unbewaff.petclinic.listvets;

import java.util.List;

import net.unbewaff.wicketcrudr.AutoLister;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author DavidH
 *
 */
public class ListVetsPage extends WebPage {

	private static final long serialVersionUID = 8414097723123946594L;

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
		VetListProvider listProvider = new VetListProvider();
		add(new AutoLister<VetWrapper>("vetsList", Model.<List<VetWrapper>>of(listProvider), 5, VetWrapper.class));
		super.onInitialize();
	}

}
