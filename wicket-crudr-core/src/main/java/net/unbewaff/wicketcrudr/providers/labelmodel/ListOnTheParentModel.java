package net.unbewaff.wicketcrudr.providers.labelmodel;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

public class ListOnTheParentModel<T> implements IModel<T> {

	private static final long serialVersionUID = -1391270744968642168L;
	private Component parent;
	private int index;

	public ListOnTheParentModel(Component parent, int index) {
		Object model = parent.getDefaultModelObject();
		assert(model instanceof List);
		this.parent = parent;
		this.index = index;
	}

	@Override
	public void detach() {
		// Do nothing parent will detach
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getObject() {
		return ((List<T>) parent.getDefaultModelObject()).get(index);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setObject(T object) {
		((List<T>) parent.getDefaultModelObject()).set(index, object);
	}

}
