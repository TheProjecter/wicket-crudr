/**
 * 
 */
package net.unbewaff.tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

/**
 * @author DavidH
 *
 */
public class BacklinkEditingCollection<T, V> implements Collection<T> {
	
	private Collection<T> data;
	private Field backLink;
	private V parent;
	private transient final static Logger logger = Logger.getLogger(BacklinkEditingCollection.class);

	/**
	 * 
	 */
	public BacklinkEditingCollection(Collection<T> data, Field backlinkSetter, V parent) {
		this.data = data;
		this.backLink = backlinkSetter;
		this.parent = parent;
	}

	/**
	 * @return
	 * @see java.util.Collection#size()
	 */
	public int size() {
		return data.size();
	}

	/**
	 * @return
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty() {
		return data.isEmpty();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return data.contains(o);
	}

	/**
	 * @return
	 * @see java.util.Collection#iterator()
	 */
	public Iterator<T> iterator() {
		return data.iterator();
	}

	/**
	 * @return
	 * @see java.util.Collection#toArray()
	 */
	public Object[] toArray() {
		return data.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.Collection#toArray(T[])
	 */
	public <X> X[] toArray(X[] a) {
		return data.toArray(a);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(T e) {
		try {
			backLink.set(e, parent);
		} catch (IllegalArgumentException e1) {
			logger.error("Swallowed exception: ", e1);
		} catch (IllegalAccessException e1) {
			logger.error("Swallowed exception: ", e1);
		}
		return data.add(e);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		try {
			backLink.set(o, null);
		} catch (IllegalArgumentException e1) {
			logger.error("Swallowed exception: ", e1);
		} catch (IllegalAccessException e1) {
			logger.error("Swallowed exception: ", e1);
		}
		return data.remove(o);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		return data.containsAll(c);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends T> c) {
		for (T o: c) {
			try {
				backLink.set(o, parent);
			} catch (IllegalAccessException e) {
				logger.error("Swallowed exception: ", e);
			} catch (IllegalArgumentException e) {
				logger.error("Swallowed exception: ", e);
			}
		}
		return data.addAll(c);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;
		for (Object o: c) {
			changed = remove(o) || changed;
		}
		return changed;
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> c) {
		boolean changed = false;
		for (T o: data) {
			if (!c.contains(o)) {
				changed = remove(o) || changed;
			}
		}
		return changed;
	}

	/**
	 * 
	 * @see java.util.Collection#clear()
	 */
	public void clear() {
		data.clear();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Collection#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return data.equals(o);
	}

	/**
	 * @return
	 * @see java.util.Collection#hashCode()
	 */
	public int hashCode() {
		return data.hashCode();
	}

}
