/**
 * 
 */
package net.unbewaff.wicketcrudr.components;

import java.util.List;

/**
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
public interface ICrudrListProvider<T> {

	public abstract List<T> getList();

}