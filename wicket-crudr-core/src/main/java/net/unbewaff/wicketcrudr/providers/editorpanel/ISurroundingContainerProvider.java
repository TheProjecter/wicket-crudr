package net.unbewaff.wicketcrudr.providers.editorpanel;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.providers.editor.IEditorProvider;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;

/**
 * Implementations provide {@link WebMarkupContainer}s that in turn provide the Markup for FormComponents created
 * by implementations if {@link IEditorProvider}s
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public interface ISurroundingContainerProvider extends Serializable {

        /**
         * Create a Container to provide matching Markup to FormComponents.
         * @param componentId The ID for the container. Currently it's allways "editor"
         * @param editor TODO
         * @return The container
         */
        public WebMarkupContainer newSurroundingContainer(String componentId, FormComponent<?> editor);

        /**
         * Show the container and add it to the AjaxRequestTarget
         * @param target the AjaxRequestTarget
         * @param container the container to show
         * @return this for chaining
         */
        public WebMarkupContainer show(AjaxRequestTarget target, WebMarkupContainer container);

        /**
         * Hide the container and add it to the AjaxRequestTarget
         * @param target the AjaxRequestTarget
         * @param container the container to hide
         * @return this for chaining
         */
        public WebMarkupContainer hide(AjaxRequestTarget target, WebMarkupContainer container);
}
