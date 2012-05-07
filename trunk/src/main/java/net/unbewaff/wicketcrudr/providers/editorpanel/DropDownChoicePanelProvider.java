package net.unbewaff.wicketcrudr.providers.editorpanel;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * A simple implementation returning a {@link Panel} with Markup to accept a {@link CheckBox}.
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class DropDownChoicePanelProvider implements ISurroundingContainerProvider {

    private static final long serialVersionUID = -1846120701517882100L;

    public WebMarkupContainer newSurroundingContainer(String componentId) {
		return new DropDownChoicePanel(componentId);
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
