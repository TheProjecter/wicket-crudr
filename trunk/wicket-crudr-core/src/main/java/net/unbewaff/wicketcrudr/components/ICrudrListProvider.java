/**
 *
 */
package net.unbewaff.wicketcrudr.components;

import java.io.Serializable;
import java.util.List;

/**
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
public interface ICrudrListProvider<T extends Serializable> {

	public abstract List<T> getList();

}