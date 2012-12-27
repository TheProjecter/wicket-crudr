package net.unbewaff.tools;

public interface IWrapperFactory<T extends IWrapper<E>, E> {
	
	abstract T newWrapper(E target);

}