/**
 *
 */
package net.unbewaff.wicketcrudr.datablocks;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface IMultipleStyledColumn<T> extends IColumn<T> {

	/**
	 * @return
	 */
	public abstract String getCssClassForHeader();

	/**
	 * @return
	 */
	public abstract String getCssClassForBody();

	/**
	 * @return
	 */
	public abstract String getCssClassForFooter();
}
