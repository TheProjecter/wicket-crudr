/**
 *
 */
package net.unbewaff.wicketcrudr.providers;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface IEditorProvider<T> extends Serializable {

    /**
     * Creates a FormComponent. This FormComponent has to match the Markup of the Container created by ISurroundingContainerProvider.newSurroundingContainer.
     * @param componentId The ID of the FormComponent. Right now it's always "editor"
     * @param model The Model to be used by the FormComponent
     * @return a new FormComponent.
     */

	public FormComponent<T> newEditor(String componentId, IModel<T> model);

}
