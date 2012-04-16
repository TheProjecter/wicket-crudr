package net.unbewaff.wicketcrudr.providers;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

public class TextFieldProvider<T> implements IEditorProvider<T> {

	private static final long serialVersionUID = -7231019074574082991L;

	public FormComponent<T> newEditor(String componentId,
			IModel<T> model) {
		return new TextField<T>(componentId, model);
	}
	
}
