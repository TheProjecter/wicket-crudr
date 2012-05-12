/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editorpanel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class ModalWindowProvider implements ISurroundingContainerProvider {

    private static final long serialVersionUID = -7075953061297907623L;
    private ModalWindow window;
    private final ISurroundingContainerProvider contentProvider;
    private WebMarkupContainer content;

    /**
     * @param contentProvider
     */
    public ModalWindowProvider(ISurroundingContainerProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.editorpanel.ISurroundingContainerProvider#newSurroundingContainer(java.lang.String)
     */
    @Override
    public WebMarkupContainer newSurroundingContainer(String componentId, FormComponent<?> editor) {
        window = new ModalWindow(componentId);
        content = contentProvider.newSurroundingContainer(window.getContentId(), editor);
        window.setContent(content);
        return window;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.editorpanel.ISurroundingContainerProvider#show(org.apache.wicket.ajax.AjaxRequestTarget, org.apache.wicket.markup.html.WebMarkupContainer)
     */
    @Override
    public WebMarkupContainer show(AjaxRequestTarget target, WebMarkupContainer container) {
        if (container == null || content == null) {
            throw new IllegalStateException("Can't show uninitialized content");
        }
        if (container instanceof ModalWindow) {
            ModalWindow modalWindow = (ModalWindow) container;
            modalWindow.show(target);
            contentProvider.show(target, content);
        } else {
            throw new IllegalArgumentException("ModalWindowProvider can't show non-ModalWindows");
        }
        return container;
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.editorpanel.ISurroundingContainerProvider#hide(org.apache.wicket.ajax.AjaxRequestTarget, org.apache.wicket.markup.html.WebMarkupContainer)
     */
    @Override
    public WebMarkupContainer hide(AjaxRequestTarget target, WebMarkupContainer container) {
        if (container == null || content == null) {
            throw new IllegalStateException("Can't show uninitialized content");
        }
        if (container instanceof ModalWindow) {
            ModalWindow modalWindow = (ModalWindow) container;
            modalWindow.close(target);
            contentProvider.hide(target, content);
        } else {
            throw new IllegalArgumentException("ModalWindowProvider can't hide non-ModalWindows");
        }
        return container;
    }

}
