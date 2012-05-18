package net.unbewaff.wicketcrudr.providers.editorpanel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
class PasswordPanelProvider implements ISurroundingContainerProvider {
    private static final long serialVersionUID = 1L;

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

    @Override
    public WebMarkupContainer newSurroundingContainer(String componentId, FormComponent<?> editor) {
        PasswordPanel passwordPanel = new PasswordPanel(componentId);
        return passwordPanel;
    }
}