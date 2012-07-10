package net.unbewaff.wicketcrudr.providers.editorpanel;


import net.unbewaff.wicketcrudr.providers.editor.Palette;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * A simple implementation returning a {@link Panel} with Markup to accept a {@link CheckBox}.
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public abstract class PalettePanelProvider<T> implements ISurroundingContainerProvider {

	private static final long serialVersionUID = -4861783108809481116L;

	@SuppressWarnings({"unchecked", "rawtypes"})
    public WebMarkupContainer newSurroundingContainer(String componentId, FormComponent<?> editor) {
		PalettePanel palette = new PalettePanel(componentId, null, null, 5, false) {

            private static final long serialVersionUID = -952266298018564628L;

            @Override
			protected IChoiceRenderer<?> newChoiceRenderer() {
				return newChoicesRenderer();
			}

		};
		palette.setRecorderComponent((Palette<?>)editor);
		return palette;
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

	public abstract IChoiceRenderer<T> newChoicesRenderer();
}
