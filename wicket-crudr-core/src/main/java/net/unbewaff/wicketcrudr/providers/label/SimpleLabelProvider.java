package net.unbewaff.wicketcrudr.providers.label;

import net.unbewaff.wicketcrudr.components.ILabelFacade;
import net.unbewaff.wicketcrudr.providers.labelmodel.ILabelModelProvider;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

/**
 * Base Implementation to create a simple {@link org.apache.wicket.markup.html.basic.Label} to display the data.
 *
 * @author David Hendrix (Nicktarix)
 *
 * @param <T>
 */
class SimpleLabelProvider<T> implements ILabelProvider<T> {

    private static final long serialVersionUID = -7292107981087842284L;

    private ILabelModelProvider<T> labelModelProvider;

    /**
     * @param labelModelProvider
     */
    public SimpleLabelProvider(ILabelModelProvider<T> labelModelProvider) {
        this.labelModelProvider = labelModelProvider;
    }

    public Component newLabel(final ILabelFacade parent, String componentId, IModel<T> model) {
        Label label = new Label(componentId, newLabelModel(model)) {

            private static final long serialVersionUID = 7754015393510391867L;

            @Override
            public <C> IConverter<C> getConverter(final Class<C> type) {
                IConverter<C> c = parent.getConverter(type);
                return c != null ? c : super.getConverter(type);
            }

            @Override
            public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
                Object modelObject = getDefaultModelObject();
                if ((modelObject == null) || "".equals(modelObject)) {
                    replaceComponentTagBody(markupStream, openTag, parent.defaultNullLabel());
                } else {
                    super.onComponentTagBody(markupStream, openTag);
                }
            }
        };

        return label;
    }

    @Override
    public IModel<?> newLabelModel(IModel<T> model) {
        return labelModelProvider.newLabelModel(model);
    }

}
