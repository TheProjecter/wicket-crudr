/**
 *
 */
package net.unbewaff.wicketcrudr.dataproviders;

import org.apache.wicket.markup.repeater.data.IDataProvider;

/**
 * Addition to IDataProvider to provide the ability to manipulate the backing list of a Datatable from within.
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public interface IManageableDataProvider<T> extends IDataProvider<T> {

    public void delete(T object);

    public void up(T object);

    public void down(T object);

    public void first(T object);

    public void last(T object);

    public boolean isFirst(T object);

    public boolean isLast(T object);

    public void add(T object);

}
