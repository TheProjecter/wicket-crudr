/**
 * 
 */
package net.unbewaff.wicketcrudr.borders;

import java.io.Serializable;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.WebXmlFile;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class TableBorder extends StyleableBorder implements Serializable {

	private static final long serialVersionUID = 7155177388080632229L;
	private IModel<String> borderCss = Model.of("autoDisplaytable");
	private String fragmentId = "tableCellsFragment";

	/**
	 * @param id
	 */
	public TableBorder(String id) {
		super(id);
	}

	/**
	 * @param id
	 * @param model
	 */
	public TableBorder(String id, IModel<String> borderCss) {
		super(id, borderCss);
		
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.Component#onInitialize()
	 */
	@Override
	protected void onInitialize() {
		super.onInitialize();
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.borders.StyleableBorder#getBorderCss()
	 */
	@Override
	public IModel<String> getBorderCss()  {
		return borderCss;
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.borders.StyleableBorder#getFragmentId()
	 */
	@Override
	public String getFragmentId() {
		return fragmentId;
	}

}
