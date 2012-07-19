/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import java.io.Serializable;

import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;

import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
@SuppressWarnings("rawtypes")
public class ConcatenatedLabelprovider extends SimpleLabelProvider implements Serializable {

	private static final long serialVersionUID = -3607622113147310258L;
	private ILabelModelProvider<?> innerLabelModelProvider;

    /**
     * @param labelModelProvider
     */

	@SuppressWarnings("unchecked")
	public ConcatenatedLabelprovider(ILabelModelProvider labelModelProvider, ILabelModelProvider<?> innerLabelModelProvider) {
		super(labelModelProvider);
        this.innerLabelModelProvider = innerLabelModelProvider;
    }

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.providers.label.SimpleLabelProvider#newLabelModel(org.apache.wicket.model.IModel)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public IModel<?> newLabelModel(IModel model) {
		return innerLabelModelProvider.newLabelModel(super.newLabelModel(model));
	}

}
