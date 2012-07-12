/**
 *
 */
package net.unbewaff.wicketcrudr.components;

import java.io.Serializable;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface ICrudrDataProvider<T extends Serializable> extends Serializable, ICrudrListProvider<T> {

    public abstract T newInstance();

    public abstract Class<T> getType();

    public abstract Serializable getId();

}
