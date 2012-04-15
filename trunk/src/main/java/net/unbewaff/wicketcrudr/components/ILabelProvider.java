/**
 * 
 */
package net.unbewaff.wicketcrudr.components;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * @author nicktar
 *
 */
public interface ILabelProvider<T> extends Serializable {
	
	public Component newLabel(MarkupContainer parent, String componentId, IModel<T> model);

}
