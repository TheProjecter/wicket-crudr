package net.unbewaff.wicketcrudr.providers;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * Base Implementation to create a simple {@link Label} to display the data.
 *
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
public class SimpleLabelProvider<T> implements ILabelProvider<T> {

	private static final long serialVersionUID = -7292107981087842284L;

	public Component newLabel(String componentId, IModel<T> model) {
		return new Label(componentId, model);
	}

}
