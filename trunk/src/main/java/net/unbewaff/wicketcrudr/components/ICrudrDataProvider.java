/**
 *
 */
package net.unbewaff.wicketcrudr.components;

import java.io.Serializable;
import java.util.List;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public interface ICrudrDataProvider<T> extends Serializable {

    public abstract List<T> getList();

    public abstract T newInstance();

    public abstract Class<T> getType();

    public abstract Serializable getId();

}
