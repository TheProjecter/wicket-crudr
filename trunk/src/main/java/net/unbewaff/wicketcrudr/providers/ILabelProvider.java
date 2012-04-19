/**
 *
 */
package net.unbewaff.wicketcrudr.providers;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.components.ILabelFacade;

import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface ILabelProvider<T> extends Serializable {

    /**
     * Create the display component for displaying certain Model objects in BrixxLists. The easiest implementation
     * would be a simple Label but this can be extended according to any needs.
     * It is highly recommended to return components implementing the following methods as shown
     * {@code
     *      @Override
     *      public <C> IConverter<C> getConverter(final Class<C> type) {
     *          IConverter<C> c = parent.getConverter(type);
     *          return c != null ? c : super.getConverter(type);
     *      }
     *
     *      @Override
     *      public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
     *          Object modelObject = getDefaultModelObject();
     *          if ((modelObject == null) || "".equals(modelObject)) {
     *              replaceComponentTagBody(markupStream, openTag, parent.defaultNullLabel());
     *          } else {
     *              super.onComponentTagBody(markupStream, openTag);
     *          }
     *      }
     *}
     * @param parent The enclosing AjaxEditableLabelContainer instance
     * @param componentId The ID for the created Component. Currently it's allways "label"
     * @param model the data to display
     * @return The displaying Component
     */
public WebComponent newLabel(ILabelFacade parent, String componentId, IModel<T> model);

}
