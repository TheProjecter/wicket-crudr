package net.unbewaff.wicketcrudr.columns;

import net.unbewaff.wicketcrudr.dataproviders.IManageableDataProvider;
import net.unbewaff.wicketcrudr.events.ListChangedEventPayLoad;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

class LinkPanel<T> extends Panel {

    private final IManageableDataProvider<T> provider;
    private final IModel<T> model;

    LinkPanel(String id, IModel<T> rowModel, IManageableDataProvider<T> provider) {
        super(id, rowModel);
        this.provider = provider;
        this.model = rowModel;
    }

    @Override
    protected void onInitialize() {
        add(new AjaxFallbackLink<T>("delete") {

            private static final long serialVersionUID = -4475637632376369508L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                provider.delete(model.getObject());
                send(getPage(), Broadcast.DEPTH, new ListChangedEventPayLoad<T>(target, provider));
            }
        });
        add(new AjaxFallbackLink<T>("first") {

            private static final long serialVersionUID = -149459019629333120L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                provider.first(model.getObject());
                send(getPage(), Broadcast.DEPTH, new ListChangedEventPayLoad<T>(target, provider));
            }
        });
        add(new AjaxFallbackLink<T>("up") {

            private static final long serialVersionUID = 4912129068865714404L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                provider.up(model.getObject());
                send(getPage(), Broadcast.DEPTH, new ListChangedEventPayLoad<T>(target, provider));
            }
        });
        add(new AjaxFallbackLink<T>("down") {

            private static final long serialVersionUID = -3419808446324812356L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                provider.down(model.getObject());
                send(getPage(), Broadcast.DEPTH, new ListChangedEventPayLoad<T>(target, provider));
            }
        });

        super.onInitialize();
    }


}