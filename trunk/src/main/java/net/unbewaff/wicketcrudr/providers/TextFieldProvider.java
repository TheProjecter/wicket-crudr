package net.unbewaff.wicketcrudr.providers;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

/**
 * A simple implementation creating a TextField (In combination with a {@link SimpleLabelProvider} and a
 * {@link TextFieldPanelProvider} this mimicks the behaviour of a basic {@link AjaxEditableLink}.
 *
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
public class TextFieldProvider<T> implements IEditorProvider<T> {

	private static final long serialVersionUID = -7231019074574082991L;

	public FormComponent<T> newEditor(String componentId, IModel<T> model) {
		return new TextField<T>(componentId, model);
	}

}
