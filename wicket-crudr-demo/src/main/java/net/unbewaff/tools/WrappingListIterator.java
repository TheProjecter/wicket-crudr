/**
 * 
 */
package net.unbewaff.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ListIterator;

/**
 * @author DavidH
 *
 */
public class WrappingListIterator<T extends IWrapper<E>, E> implements ListIterator<T> {
	
	private ListIterator<E> innerIterator;
	private IWrapperFactory<T, E> wrapperFactory;

	/**
	 * 
	 */
	public WrappingListIterator(ListIterator<E> innerIterator, IWrapperFactory<T, E> constructor) {
		this.innerIterator = innerIterator;
		this.wrapperFactory = constructor;
	}

	/**
	 * @param e
	 * @see java.util.ListIterator#add(java.lang.Object)
	 */
	public void add(T e) {
		innerIterator.add(e.getObject());
	}

	/**
	 * @return
	 * @see java.util.ListIterator#hasNext()
	 */
	public boolean hasNext() {
		return innerIterator.hasNext();
	}

	/**
	 * @return
	 * @see java.util.ListIterator#hasPrevious()
	 */
	public boolean hasPrevious() {
		return innerIterator.hasPrevious();
	}

	/**
	 * @return
	 * @see java.util.ListIterator#next()
	 */
	public T next() {
		return newWrapper(innerIterator.next());
	}

	/**
	 * @return
	 * @see java.util.ListIterator#nextIndex()
	 */
	public int nextIndex() {
		return innerIterator.nextIndex();
	}

	/**
	 * @return
	 * @see java.util.ListIterator#previous()
	 */
	public T previous() {
		return newWrapper(innerIterator.previous());
	}

	/**
	 * @return
	 * @see java.util.ListIterator#previousIndex()
	 */
	public int previousIndex() {
		return innerIterator.previousIndex();
	}

	/**
	 * 
	 * @see java.util.ListIterator#remove()
	 */
	public void remove() {
		innerIterator.remove();
	}

	/**
	 * @param e
	 * @see java.util.ListIterator#set(java.lang.Object)
	 */
	public void set(T e) {
		innerIterator.set(e.getObject());
	}

	/**
	 * @param target
	 * @return
	 */
	protected T newWrapper(E target) {
		return wrapperFactory.newWrapper(target);
	}
	
}
