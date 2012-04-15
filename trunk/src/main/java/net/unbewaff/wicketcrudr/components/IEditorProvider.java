/**
 * 
 */
package net.unbewaff.wicketcrudr.components;

import java.io.Serializable;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author nicktar
 *
 */
public interface IEditorProvider<T> extends Serializable {
	
	public FormComponent<T> newEditor(MarkupContainer parent, String componentId, IModel<T> model);

}
