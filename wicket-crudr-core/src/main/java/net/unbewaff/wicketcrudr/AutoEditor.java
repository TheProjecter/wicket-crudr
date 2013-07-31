/**
 * 
 */
package net.unbewaff.wicketcrudr;

import java.io.Serializable;
import java.util.List;

import net.unbewaff.wicketcrudr.annotations.type.Css;
import net.unbewaff.wicketcrudr.borders.StyleableBorder;
import net.unbewaff.wicketcrudr.borders.TableBorder;
import net.unbewaff.wicketcrudr.datablocks.DataBlockFactory;
import net.unbewaff.wicketcrudr.datablocks.IDataBlock;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author Nicktar
 *
 */
public class AutoEditor<T extends Serializable> extends Panel implements Serializable {

    private static final long serialVersionUID = 1837476148440999521L;
    private String cssClass = "";
    private static final transient Logger logger = Logger.getLogger(AutoEditor.class);
    private final List<IDataBlock<T>> blocks;
    private RepeatingView view;
    private StyleableBorder border;

    /**
     * @param id
     * @param model
     */
    public AutoEditor(String id, IModel<T> model, Class<T> clazz) {
        super(id, model);
        Css css = clazz.getAnnotation(Css.class);
        if (css != null) {
            cssClass = " " + css.value();
        }
        blocks = DataBlockFactory.getColumns(clazz);
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.Component#onConfigure()
     */
    @Override
    protected void onConfigure() {
        setVisible(getDefaultModelObject() != null);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
        super.onConfigure();
    }

    @Override
    protected void onInitialize() {
        this.setVisible(getDefaultModelObject() != null);
        logger.debug("Model Object: " + getDefaultModelObject());
        WebMarkupContainer webMarkupContainer = new WebMarkupContainer("table");
        webMarkupContainer.add(new AttributeAppender("class", Model.of("autoDisplaytable" + cssClass)));
        getBorder().addToBorder(webMarkupContainer);

        view = new RepeatingView("view", getDefaultModel());
        getBorder().add(view);
        add(getBorder());
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
        super.onInitialize();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onBeforeRender() {
        view.removeAll();
        for (IDataBlock<T> block: blocks) {
            Fragment fragment = new DisplayFragment(view.newChildId(), getBorder().getFragmentId(), getBorder());
            WebMarkupContainer container = new WebMarkupContainer("fragmentContainer");
            fragment.add(container);
            view.add(fragment);
            Component label = block.getLabel("label", (IModel<T>) getDefaultModel()).add(new AttributeAppender("class", Model.of("ui-widget-header" + cssClass))).add(new AttributeAppender("class", Model.of(block.getName()), " "));
            Component value = block.getValue("value", (IModel<T>) getDefaultModel()).add(new AttributeAppender("class", Model.of("ui-widget-content" + cssClass))).add(new AttributeAppender("class", Model.of(block.getName()), " "));
            container.add(label);
            container.add(value);
        }

        super.onBeforeRender();
    }


    private StyleableBorder getBorder() {
        if (border == null) {
            border = newBorder();
        }
        return border;
    }

    /**
     * Hook method to define other ways to display than the table used here
     * 
     * @return a {@link StyleableBorder} instance
     */
    protected StyleableBorder newBorder() {
        return new TableBorder("border", Model.of("autoDisplaytable" + cssClass));
    }

    protected static class DisplayFragment extends Fragment implements Serializable {

        /**
         * @param id
         * @param markupId
         * @param markupProvider
         */
        public DisplayFragment(String id, String markupId, MarkupContainer markupProvider) {
            super(id, markupId, markupProvider);
        }
    }
}
