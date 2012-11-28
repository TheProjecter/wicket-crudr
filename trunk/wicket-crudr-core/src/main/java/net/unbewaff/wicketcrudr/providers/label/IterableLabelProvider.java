/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import java.io.Serializable;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.InnerType;
import net.unbewaff.wicketcrudr.components.ILabelFacade;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class IterableLabelProvider<T extends List<V>, V extends Serializable> implements Serializable, ILabelProvider<T> {


    private ILabelModelProvider<T> labelModelProvider;
    private ILabelModelProvider<V> innerLabelModelProvider;
    private InnerType innerType;

    /**
     * @param labelModelProvider
     */
    public IterableLabelProvider(ILabelModelProvider<T> labelModelProvider, ILabelModelProvider<V> innerLabelModelProvider, InnerType innerType) {
        this.labelModelProvider = labelModelProvider;
        this.innerLabelModelProvider = innerLabelModelProvider;
        this.innerType = innerType;
    }

    /* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider#newLabelModel(org.apache.wicket.model.IModel)
	 */
	@Override
	public IModel<?> newLabelModel(IModel<T> model) {
		return labelModelProvider.newLabelModel(model);
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.providers.label.ILabelProvider#newLabel(net.unbewaff.wicketcrudr.components.ILabelFacade, java.lang.String, org.apache.wicket.model.IModel)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Component newLabel(ILabelFacade parent, String componentId, IModel<T> model) {
		return new IterableLabel<T,V>(componentId, (IModel<T>) newLabelModel(model), innerLabelModelProvider, innerType);
	}

}
