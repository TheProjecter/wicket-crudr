/**
 *
 */
package net.unbewaff.petclinic.listvets;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
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
		super.onInitialize();
	}

}
