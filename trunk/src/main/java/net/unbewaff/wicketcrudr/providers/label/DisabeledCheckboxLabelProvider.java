/**
 * 
 */
package net.unbewaff.wicketcrudr.providers.label;

import net.unbewaff.wicketcrudr.components.ILabelFacade;
import net.unbewaff.wicketcrudr.providers.editorpanel.CheckBoxPanelProvider;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;

import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;

/**
 * @author nicktar
 *
 */
public class DisabeledCheckboxLabelProvider implements ILabelProvider<Boolean> {
	
	private ILabelModelProvider<Boolean> labelModelProvider;
	private final CheckBoxPanelProvider cbpp;

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
	public WebComponent newLabel(ILabelFacade parent, String componentId, IModel<Boolean> model) {
		WebMarkupContainer retValue = cbpp.newSurroundingContainer(componentId);
		CheckBox checkBox = new CheckBox("editor", model);
		checkBox.setEnabled(false);
		retValue.add(checkBox);
		
		return null;
	}


}
