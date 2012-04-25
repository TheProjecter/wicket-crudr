/**
 *
 */
package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface ILabelModelProvider<T> extends Serializable {

    public abstract IModel<T> newLabelModel(IModel<T> model);
}
