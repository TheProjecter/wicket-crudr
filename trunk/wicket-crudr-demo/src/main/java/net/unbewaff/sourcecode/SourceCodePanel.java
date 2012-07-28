/**
 *
 */
package net.unbewaff.sourcecode;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author davidh
 *
 */
public class SourceCodePanel extends Panel implements Serializable {

	private IModel<String> model;

	/**
	 * @param id
	 * @param model
	 */
	public SourceCodePanel(String id, IModel<String> model) {
		super(id, model);
		this.model = model;
	}

	@Override
	protected void onInitialize() {
		Label code = new Label("code", model);
		add(code);
		code.setEscapeModelStrings(false);
		//code.setRenderBodyOnly(true);
		super.onInitialize();
	}

}
