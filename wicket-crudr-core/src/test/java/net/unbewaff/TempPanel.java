package net.unbewaff;

import net.unbewaff.wicketcrudr.components.IEditorFacade;
import net.unbewaff.wicketcrudr.components.ILabelFacade;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.convert.IConverter;

/**
 *  Simple DummyContainer to provide the Markup for a DataTable
 *
 * @author David Hendrix (Nicktarix)
 *
 */
public class TempPanel extends Panel implements IEditorFacade, ILabelFacade{

    private static final long serialVersionUID = -8797466715560140738L;

    public TempPanel(String id) {
        super(id);
    }

    /* (non-Javadoc)
     * @see net.unbewaff.wicketcrudr.components.ILabelFacade#defaultNullLabel()
     */
    @Override
    public String defaultNullLabel() {
        return "SpecialString";
    }

    @Override
    public void onModelChanged() {
        super.onModelChanged();
    }

    @Override
    public void onModelChanging() {
        super.onModelChanging();
    }

    @Override
    public <C> IConverter<C> getConverter(final Class<C> type) {
        return super.getConverter(type);
    }

    public String getComponentId() {
        return "table";
    }

}
