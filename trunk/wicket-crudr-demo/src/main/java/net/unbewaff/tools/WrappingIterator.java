/**
 * 
 */
package net.unbewaff.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

/**
 * @author DavidH
 *
 */
public class WrappingIterator<T extends IWrapper<E>, E> implements Iterator<T> {
	
	private Iterator<E> innerIterator;
	private IWrapperFactory<T, E> wrapperFactory;

	/**
	 * 
	 */
	public WrappingIterator(Iterator<E> innerIterator, IWrapperFactory<T, E> wrapperFactory) {
		this.innerIterator = innerIterator;
		this.wrapperFactory = wrapperFactory;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return innerIterator.hasNext();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		E target = innerIterator.next();
		return newWrapper(target);
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		innerIterator.remove();
	}

	/**
	 * @param target
	 * @return
	 */
	T newWrapper(E target) {
		return wrapperFactory.newWrapper(target);
	}
	
}
