package net.unbewaff.wicketcrudr.tools.wrappinglist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class AWrappingList<T extends IWrapper<E>, E> implements List<T>, Serializable {
	
	private static final long serialVersionUID = -9132692758202141765L;

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(T e) {
		return getBaseList().add(e.getObject());
	}

	/**
	 * @param index
	 * @param element
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	public void add(int index, T element) {
		getBaseList().add(index, element.getObject());
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends T> c) {
		List<E> temp = new ArrayList<E>();
		for (T o :c) {
			temp.add(o.getObject());
		}
		return getBaseList().addAll(temp);
	}

	/**
	 * @param index
	 * @param c
	 * @return
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	public boolean addAll(int index, Collection<? extends T> c) {
		List<E> temp = new ArrayList<E>();
		for (T o :c) {
			temp.add(o.getObject());
		}
		return getBaseList().addAll(index, temp);
	}

	/**
	 * 
	 * @see java.util.List#clear()
	 */
	public void clear() {
		getBaseList().clear();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		boolean retValue = false;
		if (o instanceof IWrapper) {
			retValue = getBaseList().contains(((IWrapper) o).getObject());
		}
		return retValue;
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		boolean retValue = true;
		for (Object o: c) {
			retValue = retValue && contains(o);
		}
		return retValue;
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return getBaseList().equals(o);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#get(int)
	 */
	public T get(int index) {
		return getWrapperFactory().newWrapper(getBaseList().get(index));
	}

	/**
	 * @return
	 * @see java.util.List#hashCode()
	 */
	public int hashCode() {
		return getBaseList().hashCode();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		int retValue = -1;
		if (o instanceof IWrapper) {
			retValue = getBaseList().indexOf(((IWrapper) o).getObject());
		}
		return retValue;
	}

	/**
	 * @return
	 * @see java.util.List#isEmpty()
	 */
	public boolean isEmpty() {
		return getBaseList().isEmpty();
	}

	/**
	 * @return
	 * @see java.util.List#iterator()
	 */
	public Iterator<T> iterator() {
		return new WrappingIterator<T, E>(getBaseList().iterator(), getWrapperFactory());
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf(Object o) {
		int retValue = -1;
		if (o instanceof IWrapper) {
			retValue = getBaseList().lastIndexOf(((IWrapper) o).getObject());
		}
		return retValue;
	}

	/**
	 * @return
	 * @see java.util.List#listIterator()
	 */
	public ListIterator<T> listIterator() {
		return new WrappingListIterator<T, E>(getBaseList().listIterator(), getWrapperFactory());
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#listIterator(int)
	 */
	public ListIterator<T> listIterator(int index) {
		return new WrappingListIterator<T, E>(getBaseList().listIterator(index), getWrapperFactory());
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.List#remove(int)
	 */
	public T remove(int index) {
		return getWrapperFactory().newWrapper(getBaseList().remove(index));
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		boolean retValue = false;
		if (o instanceof IWrapper) {
			retValue = getBaseList().remove(((IWrapper) o).getObject());
		}
		return retValue;
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {
		List<E> temp = new ArrayList<E>();
		boolean retValue = false;
		for (Object o :c) {
			if (o instanceof IWrapper) {
				retValue = getBaseList().remove(((IWrapper) o).getObject()) || retValue;
			}
		}
		return retValue;
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> c) {
		return getBaseList().retainAll(c);
	}

	/**
	 * @param index
	 * @param element
	 * @return
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	public T set(int index, T element) {
		return getWrapperFactory().newWrapper(getBaseList().set(index, element.getObject()));
	}

	/**
	 * @return
	 * @see java.util.List#size()
	 */
	public int size() {
		return getBaseList().size();
	}

	/**
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 * @see java.util.List#subList(int, int)
	 */
	public List<T> subList(final int fromIndex, final int toIndex) {
		return new AWrappingList<T, E>() {
			@Override
			protected List<E> getBaseList() {
				return getBaseList().subList(fromIndex, toIndex);
			}

			@Override
			protected IWrapperFactory<T, E> getWrapperFactory() {
				return AWrappingList.this.getWrapperFactory();
			}
		};
	}

	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Object[] toArray() {
		List<T> list = new ArrayList<T>();
		for (E o: getBaseList()) {
			list.add(getWrapperFactory().newWrapper(o));
		}
		return list.toArray();
	}

	/**
	 * @param a
	 * @return
	 * @see java.util.List#toArray(T[])
	 */
	public <U> U[] toArray(U[] a) {
		List<T> list = new ArrayList<T>();
		for (E o: getBaseList()) {
			list.add(getWrapperFactory().newWrapper(o));
		}
		return list.toArray(a);
	}

	protected abstract List<E> getBaseList();
	
	protected abstract IWrapperFactory<T, E> getWrapperFactory();
}