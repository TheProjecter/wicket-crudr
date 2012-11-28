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
public class WrappingIterator<T extends IWrapper, E> implements Iterator<T> {
	
	private Iterator<E> innerIterator;
	private Constructor<T> constructor;

	/**
	 * 
	 */
	public WrappingIterator(Iterator<E> innerIterator, Constructor<T> constructor) {
		this.innerIterator = innerIterator;
		this.constructor = constructor;
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
	protected T newWrapper(E target) {
		T newInstance = null;
		try {
			newInstance = constructor.newInstance(target);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newInstance;
	}
	
}
