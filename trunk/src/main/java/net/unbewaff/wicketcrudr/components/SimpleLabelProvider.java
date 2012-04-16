package net.unbewaff.wicketcrudr.components;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class SimpleLabelProvider<T> implements ILabelProvider<T> {

	private static final long serialVersionUID = -7292107981087842284L;

	public Component newLabel(MarkupContainer parent, String componentId,
			IModel<T> model) {
		return new Label(componentId, model);
	}

}