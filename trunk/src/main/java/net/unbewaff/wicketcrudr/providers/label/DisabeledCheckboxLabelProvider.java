/**
 *
 */
package net.unbewaff.wicketcrudr.providers.label;

import net.unbewaff.wicketcrudr.components.ILabelFacade;
import net.unbewaff.wicketcrudr.providers.editorpanel.CheckBoxPanelProvider;
import net.unbewaff.wicketcrudr.providers.editorpanel.ISurroundingContainerProvider;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;

/**
 * @author nicktar
 *
 */
public class DisabeledCheckboxLabelProvider implements ILabelProvider<Boolean> {

	private static final long serialVersionUID = -4633487166850598332L;
	private ILabelModelProvider<Boolean> labelModelProvider;
	private final ISurroundingContainerProvider cbpp;

	/**
	 * @param labelModelProvider
	 */
	public DisabeledCheckboxLabelProvider(ILabelModelProvider<Boolean> labelModelProvider) {
		this.labelModelProvider = labelModelProvider;
		cbpp = new CheckBoxPanelProvider();
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider#newLabelModel(org.apache.wicket.model.IModel)
	 */
	@Override
	public IModel<?> newLabelModel(IModel<Boolean> model) {
		return labelModelProvider.newLabelModel(model);
	}

	/* (non-Javadoc)
	 * @see net.unbewaff.wicketcrudr.providers.label.ILabelProvider#newLabel(net.unbewaff.wicketcrudr.components.ILabelFacade, java.lang.String, org.apache.wicket.model.IModel)
	 */
	@Override
	public Component newLabel(ILabelFacade parent, String componentId, IModel<Boolean> model) {
		WebMarkupContainer retValue = cbpp.newSurroundingContainer(componentId);
		retValue.setVisible(true);
		CheckBox checkBox = new CheckBox("editor", (IModel<Boolean>) newLabelModel(model));
		checkBox.setEnabled(false);
		retValue.add(checkBox);

		return retValue;
	}


}
