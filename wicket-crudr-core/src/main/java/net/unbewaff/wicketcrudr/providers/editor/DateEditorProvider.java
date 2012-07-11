/**
 *
 */
package net.unbewaff.wicketcrudr.providers.editor;

import java.util.Date;

import net.unbewaff.wicketcrudr.components.ICrudrListProvider;
import net.unbewaff.wicketcrudr.components.IEditorFacade;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class DateEditorProvider implements IEditorProvider<Date>{

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.providers.editor.IEditorProvider#newEditor(net.unbewaff.wicketcrudr.components.IEditorFacade, java.lang.String, org.apache.wicket.model.IModel, net.unbewaff.wicketcrudr.components.ICrudrListProvider)
     */
    @Override
    public FormComponent<Date> newEditor(IEditorFacade parent, String componentId, IModel<Date> model,
            ICrudrListProvider<Date> listProvider) {
        DateTextField dateTextField = new DateTextField(componentId, model);
        return dateTextField;
    }

}
