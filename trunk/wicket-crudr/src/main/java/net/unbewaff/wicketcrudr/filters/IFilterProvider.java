/**
 *
 */
package net.unbewaff.wicketcrudr.filters;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface IFilterProvider extends Serializable {

    public abstract Component getFilter (String componentId, Form form);
}
