/**
 *
 */
package net.unbewaff.wicketcrudr.datablocks;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface IDataBlock<T extends Serializable> {

	public abstract Component getValue(String componentId, IModel<T> rowModel);

	public abstract Component getLabel(String componentId, IModel<T> model);

}
