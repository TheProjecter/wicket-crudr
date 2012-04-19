package net.unbewaff.wicketcrudr.providers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * A simple implementation returning a {@link Panel} with Markup to accept a {@link TextField}. USed together
 * with a {@link SimpleLabelProvider} and a {@link TextFieldProvider} this mimics the behavior of a {@link AjaxEditableLink}
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class TextFieldPanelProvider implements ISurroundingContainerProvider {

	private static final long serialVersionUID = -4861783108809481116L;

	public WebMarkupContainer newSurroundingContainer(String componentId) {
		return new TextFieldPanel(componentId);
	}

	public WebMarkupContainer show(AjaxRequestTarget target, WebMarkupContainer container) {
		if (container == null) {
			throw new IllegalArgumentException("Can't set a non existing Container visible.");
		}
		if (!container.isVisible()) {
			container.setVisible(true);
			if (target != null) {
                target.add(container);
            }
		}
		return container;
	}

	public WebMarkupContainer hide(AjaxRequestTarget target, WebMarkupContainer container) {
		if (container == null) {
			throw new IllegalArgumentException("Can't set a non existing Container invisible.");
		}
		if (container.isVisible()) {
			container.setVisible(false);
            if (target != null) {
                target.add(container);
            }
		}
		return container;
	}
}
