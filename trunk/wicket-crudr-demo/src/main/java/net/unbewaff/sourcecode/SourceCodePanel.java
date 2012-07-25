/**
 *
 */
package net.unbewaff.sourcecode;

import java.io.Serializable;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * @author davidh
 *
 */
public class SourceCodePanel extends Panel implements Serializable {

	private IModel<String> model;
	private final String syntax;

	/**
	 * @param id
	 * @param model
	 */
	public SourceCodePanel(String id, IModel<String> model, String syntax) {
		super(id, model);
		this.model = model;
		this.syntax = syntax;
	}

	@Override
	protected void onInitialize() {
		WebMarkupContainer container = new WebMarkupContainer("container");
		container.add(new AttributeAppender("class", Model.of("brush: " + syntax), ", "));
		add(container);
		Label code = new Label("code", new PropertyModel<String>(this, "escapedSource"));
		container.add(code);
		code.setEscapeModelStrings(false);
		code.setRenderBodyOnly(true);
		super.onInitialize();
	}

	public String getEscapedSource() {
		String escapedSource = model.getObject().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		return escapedSource;
	}

}
