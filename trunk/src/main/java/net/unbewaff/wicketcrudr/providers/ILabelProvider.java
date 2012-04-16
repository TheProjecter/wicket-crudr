/**
 * 
 */
package net.unbewaff.wicketcrudr.providers;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * @author nicktar
 *
 */
public interface ILabelProvider<T> extends Serializable {
	
	public Component newLabel(String componentId, IModel<T> model);

}
