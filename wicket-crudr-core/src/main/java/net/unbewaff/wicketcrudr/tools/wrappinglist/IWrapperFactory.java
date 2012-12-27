package net.unbewaff.wicketcrudr.tools.wrappinglist;

public interface IWrapperFactory<T extends IWrapper<E>, E> {
	
	abstract T newWrapper(E target);

}