/**
 *
 */
package net.unbewaff.wicketcrudr.providers;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface IEditorProvider<T> extends Serializable {

    /**
     * Creates a FormComponent. This FormComponent has to match the Markup of the Container created by ISurroundingContainerProvider.newSurroundingContainer.
     * It is highly recommended to return instances implementing the following methods as shown:
     * {@code
     *      @Override
     *      public <C> IConverter<C> getConverter(final Class<C> type) {
     *          IConverter<C> c = parent.getConverter(type);
     *          return c != null ? c : super.getConverter(type);
     *      }
     *
     *      @Override
     *      protected void onModelChanged() {
     *          super.onModelChanged();
     *          parent.onModelChanged();
     *      }
     *
     *      @Override
     *      protected void onModelChanging() {
     *          super.onModelChanging();
     *          parent.onModelChanging();
     *      }
     *  }
     * @param parent the enclosing AjaxEditableContainer Instance
     * @param componentId The ID of the FormComponent. Right now it's always "editor"
     * @param model The Model to be used by the FormComponent
     * @return a new FormComponent.
     */

	public FormComponent<T> newEditor(IEditorFacade parent, String componentId, IModel<T> model);

}
