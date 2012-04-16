/**
 *
 */
package net.unbewaff.wicketcrudr.providers;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface ILabelProvider<T> extends Serializable {

    /**
     * Create the display component for displaying certain Model objects in BrixxLists. The easiest implementation
     * would be a simple Label but this can be extended according to any needs.
     * @param componentId The ID for the created Component. Currently it's allways "label"
     * @param model the data to display
     * @return The displaying Component
     */
public Component newLabel(String componentId, IModel<T> model);

}
