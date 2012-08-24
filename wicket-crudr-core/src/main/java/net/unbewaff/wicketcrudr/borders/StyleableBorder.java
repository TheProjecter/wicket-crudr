/**
 * 
 */
package net.unbewaff.wicketcrudr.borders;

import java.io.Serializable;

import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public abstract class StyleableBorder extends Border {

	/**
	 * @param id
	 */
	public StyleableBorder(String id) {
		super(id);
	}

	/**
	 * @param id
	 * @param model
	 */
	public StyleableBorder(String id, IModel<?> model) {
		super(id, model);
	}

	/**
	 * @return
	 */
	public abstract IModel<String> getBorderCss();
	
	public abstract String getFragmentId(); 

}