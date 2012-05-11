package net.unbewaff.wicketcrudr.providers.editorpanel;


import java.util.Collection;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * A simple implementation returning a {@link Panel} with Markup to accept a {@link CheckBox}.
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class PalettePanelProvider implements ISurroundingContainerProvider {

	private static final long serialVersionUID = -4861783108809481116L;

	public WebMarkupContainer newSurroundingContainer(String componentId) {
		return new Palette(componentId, null, null, 5, false);
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
